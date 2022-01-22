package com.ISA.service.implementation;

import com.ISA.domain.dto.BoatHistoryReservationDTO;
import com.ISA.domain.dto.BoatReservationDTO;
import com.ISA.domain.model.*;
import com.ISA.repository.BoatFreeTermsRepository;
import com.ISA.repository.BoatProfileRepository;
import com.ISA.repository.BoatReservationRepository;
import com.ISA.repository.UserRepository;
import com.ISA.service.definition.BoatReservationService;
import com.ISA.service.definition.EmailService;
import com.ISA.service.definition.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.PessimisticLockingFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
public class BoatReservationServiceImpl implements BoatReservationService {

    @Autowired
    private BoatReservationRepository reservationRepository;

    @Autowired
    private  BoatProfileRepository profileRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BoatFreeTermsRepository freeTermsRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private UserService userService;

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public BoatReservation add(BoatReservationDTO dto) throws Exception {

        BoatProfile boatProfile = profileRepository.getLockId(dto.getBoatId());
        User currentUser = userService.getCurrentUser();

        if(isOverlapping(boatProfile.getId(), dto.getStartDate(), dto.getEndDate())){
            return null;
        }

        if(!canClientBook(currentUser.getId(), dto.getBoatId(), dto.getStartDate(), dto.getEndDate() )){
            return null;
        }

        if(currentUser.getPenalty() >= 3){
            return null;
        }

        long diffInMillies = Math.abs(dto.getEndDate().getTime() - dto.getStartDate().getTime());
        long days = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);

        BoatReservation reservation = new BoatReservation();
        reservation.setCancelled(false);
        reservation.setEndDate(dto.getEndDate());
        reservation.setStartDate(dto.getStartDate());
        reservation.setBoatProfile(boatProfile);
        reservation.setClientId(currentUser.getId());
        reservation.setNumberOfPeople(boatProfile.getCapacity());
        reservation.setExtraServices(dto.getExtraServices());
        reservation.setNumberOfPeople(boatProfile.getCapacity());
        reservation.setNumberOfDays((int) days);
        BoatFreeTerms freeTerm = getDates(reservation.getBoatProfile().getId(), reservation.getStartDate(), reservation.getEndDate());

        if(freeTerm != null && freeTerm.isAction()){
            reservation.setPrice(freeTerm.getActionPrice() * days );
        }
        else {
            reservation.setPrice(boatProfile.getPricelist() * days);
        }

        if(reservation.getExtraServices() != null && !reservation.getExtraServices().equals("No extra service")) {
            reservation.setPrice(reservation.getPrice() +  boatProfile.getExtraPrice() * days);
        }
        emailService.sendEmailForBoatReservation(currentUser, reservation);

        try{
            return reservationRepository.save(reservation);
        }catch(PessimisticLockingFailureException ex){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Try again later!");
        }
    }

    public BoatFreeTerms getDates(Long boatId, Date startDate, Date endDate) {
        List<BoatFreeTerms> freeTerms = freeTermsRepository.findAllByBoatProfileId(boatId);
        BoatFreeTerms result = null;

        for (BoatFreeTerms term : freeTerms) {
            if (term.getBoatProfile().getId().equals(boatId) && (isDateEqual(term.getStartDate(), startDate) || term.getStartDate().before(startDate)) && (isDateEqual(term.getEndDate(), endDate) || term.getEndDate().after(endDate)))
                result = term;

        }
        return result;
    }

    private List<BoatFreeTerms> getFree(List<BoatFreeTerms> allTerms, Date startDate, Date endDate) {
        List<BoatFreeTerms> terms = new ArrayList<>();

        for(BoatFreeTerms term: allTerms) {
            if((startDate.after(term.getStartDate()) || isDateEqual(startDate, term.getStartDate())) && (endDate.before(term.getEndDate()) || isDateEqual(endDate, term.getEndDate()))) {
                terms.add(term);
            }
        }
        return terms;
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public BoatReservation addByOwner(BoatReservationDTO dto, Long clientId) throws Exception {
        BoatProfile boatProfile = profileRepository.getLockId(dto.getBoatId());
        List<BoatFreeTerms> freeTerms = freeTermsRepository.findAllByBoatProfileId(dto.getBoatId());
        freeTerms = getFree(freeTerms, dto.getStartDate(), dto.getEndDate());

        if(isOverlapping(boatProfile.getId(), dto.getStartDate(), dto.getEndDate())){
            return null;
        }

        if(freeTerms.isEmpty()){
            return null;
        }

        long diffInMillies = Math.abs(dto.getEndDate().getTime() - dto.getStartDate().getTime());
        long days = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);

        BoatReservation reservation = new BoatReservation();
        reservation.setCancelled(false);
        reservation.setEndDate(dto.getEndDate());
        reservation.setStartDate(dto.getStartDate());
        reservation.setBoatProfile(boatProfile);
        reservation.setClientId(dto.getClientId());

        BoatFreeTerms freeTerm = getDates(reservation.getBoatProfile().getId(), reservation.getStartDate(), reservation.getEndDate());
        if(freeTerm != null && freeTerm.isAction()){
            reservation.setPrice(freeTerm.getActionPrice() * days );
        }
        else {
            reservation.setPrice(boatProfile.getPricelist() * days);
        }

        if(reservation.getExtraServices() != null && !reservation.getExtraServices().equals("No extra service")) {
            reservation.setPrice(reservation.getPrice() +  boatProfile.getExtraPrice() * days);
        }

        List<User> clients = userRepository.findAllByType("Client");
        for(User client: clients){
            if(reservation.getClientId().equals(client.getId())){
                emailService.sendEmailForBoatReservation(client, reservation);
            }
        }

        try{
            return reservationRepository.save(reservation);
        }catch(PessimisticLockingFailureException ex){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Try again later!");
        }
    }

    @Override
    public List<BoatProfile> findAll() {
        return profileRepository.findAll();
    }

    @Override
    public boolean isOverlapping(long boatId, Date startDate, Date endDate) {

        List<BoatReservation> reservations = reservationRepository.findAll();

        for(BoatReservation reservation: reservations){

            if(reservation.getCancelled()) {
                continue;
            }

            if(((isDateEqual(startDate, reservation.getStartDate()) || isDateEqual(endDate, reservation.getEndDate())) || (isDateEqual(startDate, reservation.getEndDate()) ||  isDateEqual(endDate, reservation.getStartDate()))) && reservation.getBoatProfile().getId().equals(boatId)) {
                return true;
            }

            if(startDate.after(reservation.getStartDate()) && startDate.before(reservation.getEndDate()) && reservation.getBoatProfile().getId().equals(boatId)){
                return true;
            }

            if(endDate.after(reservation.getStartDate()) && endDate.before(reservation.getEndDate()) && reservation.getBoatProfile().getId().equals(boatId)){
                return true;
            }
        }
        return false;
    }

    @Override
    public List<BoatReservation> getMyReservations() {
        User user = userService.getCurrentUser();

        return reservationRepository.getAllByClientIdAndCancelled(user.getId(), false);
    }

    @Override
    public boolean cancel(Long id) {

        Optional<BoatReservation> reservation = reservationRepository.findById(id);

        Date today = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(reservation.get().getStartDate());
        calendar.add(Calendar.DATE, -3);

        Date lastDayToCancel = calendar.getTime();

        if(lastDayToCancel.before(today) || reservation.get().getEndDate().before(today) || reservation.get().getCancelled())
            return false;

        reservation.get().setCancelled(true);

        reservationRepository.save(reservation.get());
        return true;
    }

    @Override
    public List<BoatFreeTerms> getAllBoatsOnAction() {
        return freeTermsRepository.findAllByIsAction(true);
    }

    @Override
    public boolean canClientBook(Long currentClientId, Long boatId, Date startDate, Date endDate) {

        List<BoatReservation> reservations = reservationRepository.findAll();

        for(BoatReservation reservation: reservations) {
            if (reservation.getCancelled() && reservation.getBoatProfile().getId().equals(boatId) && reservation.getClientId().equals(currentClientId) && isDateEqual(reservation.getStartDate(), startDate) && isDateEqual(reservation.getEndDate(), endDate)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public BoatReservation get(Long id) {
        return reservationRepository.findById(id).get();
    }


    @Override
    public List<BoatReservation> getAllReservationsForMyBoats(BoatHistoryReservationDTO dto) {
        List<BoatReservation> all = reservationRepository.findAll();
        List<BoatReservation> results = new ArrayList<>();
        User currentUser = userService.getCurrentUser();
        Date today = new Date();

        for(BoatReservation br : all) {
            if(br.getBoatProfile().getownerId().equals(currentUser.getId()) && br.getStartDate().after(today)) {
                results.add(br);
            }
        }
        return results;
    }

    @Override
    public List<BoatReservation> getAllTodayReservationsForMyBoats(BoatHistoryReservationDTO dto) {
        List<BoatReservation> all = reservationRepository.findAll();
        List<BoatReservation> results = new ArrayList<>();
        User currentUser = userService.getCurrentUser();
        Date today = new Date();

        for(BoatReservation br : all) {
            if(br.getBoatProfile().getownerId().equals(currentUser.getId()) && (
                    (br.getStartDate().before(today) && br.getEndDate().after(today)) || (br.getStartDate().before(today) && br.getEndDate().equals(today))
                            || (br.getStartDate().equals(today) && br.getEndDate().after(today)) || (br.getStartDate().equals(today) && br.getEndDate().equals(today))
            )
            ) {
                results.add(br);
            }
        }
        return results;
    }

    @Override
    public List<BoatReservation> getAllHistoryReservationsForMyBoats(BoatHistoryReservationDTO dto) {
        List<BoatReservation> all = reservationRepository.findAll();
        List<BoatReservation> results = new ArrayList<>();
        User currentUser = userService.getCurrentUser();
        Date today = new Date();

        for(BoatReservation br : all) {
            if(br.getBoatProfile().getownerId().equals(currentUser.getId()) && br.getEndDate().before(today)) {
                results.add(br);
            }
        }
        return results;
    }

    @Override
    public List<BoatReservation> getMyFinishedReservations() {
        User user = userService.getCurrentUser();
        List<BoatReservation> reservations = reservationRepository.getAllByClientIdAndCancelled(user.getId(), false);
        List<BoatReservation> results = new ArrayList<>();
        Date today = new Date();

        for(BoatReservation reservation: reservations){
            if(reservation.getEndDate().before(today)){
                results.add(reservation);
            }
        }
        return results;
    }

    @Override
    public List<BoatReservation> getMyUpcomingReservations() {
        User user = userService.getCurrentUser();
        List<BoatReservation> reservations = reservationRepository.getAllByClientIdAndCancelled(user.getId(), false);
        List<BoatReservation> results = new ArrayList<>();
        Date today = new Date();

        for(BoatReservation reservation: reservations){
            if(reservation.getStartDate().after(today)){
                results.add(reservation);
            }
        }
        return results;
    }

    @Override
    public List<BoatReservation> getMyInProgressReservations() {
        User user = userService.getCurrentUser();
        List<BoatReservation> reservations = reservationRepository.getAllByClientIdAndCancelled(user.getId(), false);
        List<BoatReservation> results = new ArrayList<>();
        Date today = new Date();

        for(BoatReservation reservation: reservations){
            if((reservation.getStartDate().before(today) || isDateEqual(reservation.getStartDate(), today)) && (reservation.getEndDate().after(today) || isDateEqual(reservation.getEndDate(), today))){
                  results.add(reservation);
            }
        }
        return results;
    } 
  
   @Override
    public List<BoatReservation> getAllReservations(Long ownerId, Long boatId) {

        List<BoatReservation> reservations = reservationRepository.getAllByBoatProfileId(boatId);
        User currentUser = userService.getCurrentUser();
        List<BoatReservation> results = new ArrayList<>();

        for (BoatReservation reservation : reservations) {
            if (reservation.getBoatProfile().getId().equals(boatId) && reservation.getBoatProfile().getownerId().equals(currentUser.getId())) {
                results.add(reservation);
            }
        }
        return results;
    }

    @Override
    public List<BoatReservation> getAllReservationsForCharts(BoatHistoryReservationDTO dto) {
        List<BoatReservation> all = reservationRepository.findAll();
        List<BoatReservation> results = new ArrayList<>();
        User currentUser = userService.getCurrentUser();
        Date today = new Date();

        for(BoatReservation br : all) {
            if(br.getBoatProfile().getownerId().equals(currentUser.getId())) {
                results.add(br);
            }
        }
        return results;
    }

    public boolean isDateEqual(Date date1, Date date2) {

        return date1.getDay() == date2.getDay() && date1.getYear() == date2.getYear() && date1.getMonth() == date2.getMonth();

    }

}
