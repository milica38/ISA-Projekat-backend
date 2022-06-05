package com.ISA.service.implementation;

import com.ISA.config.SecurityUtils;
import com.ISA.domain.dto.AdventureHistoryReservationsDTO;
import com.ISA.domain.dto.AdventureProfileDTO;
import com.ISA.domain.dto.AdventureReservationDTO;
import com.ISA.domain.dto.HomeHistoryReservationDTO;
import com.ISA.domain.model.*;
import com.ISA.repository.AdventureFreeTermsRepository;
import com.ISA.repository.AdventureProfileRepository;
import com.ISA.repository.AdventureReservationRepository;
import com.ISA.repository.LoyalProgrammeRepository;
import com.ISA.service.definition.AdventureReservationService;
import com.ISA.service.definition.EmailService;
import com.ISA.service.definition.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.concurrent.TimeUnit;

@Service
public class AdventureReservationServiceImpl implements AdventureReservationService {

    @Autowired
    private AdventureReservationRepository adventureReservationRepository;

    @Autowired
    private AdventureProfileRepository profileRepository;

    @Autowired
    private LoyalProgrammeRepository loyalProgrammeRepository;

    @Autowired
    private AdventureFreeTermsRepository freeTermsRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private UserService userService;

    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    @Override
    public AdventureReservation add(AdventureReservationDTO dto) {

        AdventureProfile adventureProfile = profileRepository.findById(dto.getAdventureId()).get();
        Optional<LoyalProgramme> lp = loyalProgrammeRepository.findById(1);
        User currentUser = userService.getCurrentUser();
        User instructorUser = userService.getUserById(adventureProfile.getInstructorId());

        if(isOverlapping(adventureProfile.getId(), dto.getStartDate(), dto.getEndDate())){
            return null;
        }

        if(!canClientBook(currentUser.getId(), dto.getAdventureId(), dto.getStartDate(), dto.getEndDate() )){
            return null;
        }

        if(currentUser.getPenalty() >= 3){
            return null;
        }

        long diffInMillies = Math.abs(dto.getEndDate().getTime() - dto.getStartDate().getTime());
        long days = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);

        AdventureReservation reservation = new AdventureReservation();
        reservation.setCancelled(false);
        reservation.setEndDate(dto.getEndDate());
        reservation.setStartDate(dto.getStartDate());
        reservation.setClientId(currentUser.getId());
        reservation.setAdventureProfile(adventureProfile);
        reservation.setExtraServices(dto.getExtraServices());
        reservation.setNumberOfPeople(adventureProfile.getMaxNumberOfPeople());
        AdventureFreeTerms freeTerm = getDates(reservation.getAdventureProfile().getId(), reservation.getStartDate(), reservation.getEndDate());
     if(currentUser.getNumberOfPoints()<lp.get().getSilverPoints()){
        if(freeTerm != null && freeTerm.isAction() ){
            reservation.setPrice(freeTerm.getActionPrice() * days );
        }
        else {
            reservation.setPrice(adventureProfile.getPriceList() * days);
        }

        if(reservation.getExtraServices() != null && !reservation.getExtraServices().equals("No extra service")) {
            reservation.setPrice(reservation.getPrice() +  adventureProfile.getExtraPrice() * days);
        }
        currentUser.setCategory("Regular");
     }

        if(currentUser.getNumberOfPoints()>=lp.get().getSilverPoints() && currentUser.getNumberOfPoints()<lp.get().getGoldPoints()){
            if(freeTerm != null && freeTerm.isAction() ){
                reservation.setPrice((freeTerm.getActionPrice()-(freeTerm.getActionPrice()*(lp.get().getSilverAction())/100)) * days );
            }
            else {
                reservation.setPrice((adventureProfile.getPriceList()-(adventureProfile.getPriceList()*(lp.get().getSilverAction())/100)) * days);
            }

            if(reservation.getExtraServices() != null && !reservation.getExtraServices().equals("No extra service")) {
                reservation.setPrice(reservation.getPrice() +  (adventureProfile.getExtraPrice()-(adventureProfile.getExtraPrice()*(lp.get().getSilverAction())/100)) * days);
            }
            currentUser.setCategory("Silver");
        }

        if(currentUser.getNumberOfPoints()>=lp.get().getGoldPoints()){
            if(freeTerm != null && freeTerm.isAction() ){
                reservation.setPrice((freeTerm.getActionPrice()-(freeTerm.getActionPrice()*(lp.get().getGoldAction())/100)) * days );
            }
            else {
                reservation.setPrice((adventureProfile.getPriceList()-(adventureProfile.getPriceList()*(lp.get().getGoldAction())/100)) * days);
            }

            if(reservation.getExtraServices() != null && !reservation.getExtraServices().equals("No extra service")) {
                reservation.setPrice(reservation.getPrice() +  (adventureProfile.getExtraPrice()-(adventureProfile.getExtraPrice()*(lp.get().getGoldAction())/100)) * days);
            }
            currentUser.setCategory("Gold");
        }

        if(instructorUser.getNumberOfPoints()<lp.get().getSilverPoints()){
            if(freeTerm != null && freeTerm.isAction() ){
                reservation.setPrice(freeTerm.getActionPrice() * days );
            }
            else {
                reservation.setPrice(adventureProfile.getPriceList() * days);
            }

            if(reservation.getExtraServices() != null && !reservation.getExtraServices().equals("No extra service")) {
                reservation.setPrice(reservation.getPrice() +  adventureProfile.getExtraPrice() * days);
            }
            instructorUser.setCategory("Regular");
        }

        if(instructorUser.getNumberOfPoints()>=lp.get().getSilverPoints() && instructorUser.getNumberOfPoints()<lp.get().getGoldPoints()){
            if(freeTerm != null && freeTerm.isAction() ){
                reservation.setPrice((freeTerm.getActionPrice()+(freeTerm.getActionPrice()*(lp.get().getSilverAction())/100)) * days );
            }
            else {
                reservation.setPrice((adventureProfile.getPriceList()+(adventureProfile.getPriceList()*(lp.get().getSilverAction())/100)) * days);
            }

            if(reservation.getExtraServices() != null && !reservation.getExtraServices().equals("No extra service")) {
                reservation.setPrice(reservation.getPrice() +   (adventureProfile.getExtraPrice()+(adventureProfile.getExtraPrice()*(lp.get().getSilverAction())/100)) * days);
            }
            instructorUser.setCategory("Silver");
        }

        if(instructorUser.getNumberOfPoints()>=lp.get().getGoldPoints()){
            if(freeTerm != null && freeTerm.isAction() ){
                reservation.setPrice((freeTerm.getActionPrice()+(freeTerm.getActionPrice()*(lp.get().getGoldAction())/100)) * days );
            }
            else {
                reservation.setPrice((adventureProfile.getPriceList()+(adventureProfile.getPriceList()*(lp.get().getGoldAction())/100)) * days);
            }

            if(reservation.getExtraServices() != null && !reservation.getExtraServices().equals("No extra service")) {
                reservation.setPrice(reservation.getPrice() +   (adventureProfile.getExtraPrice()+(adventureProfile.getExtraPrice()*(lp.get().getGoldAction())/100)) * days);
            }
            instructorUser.setCategory("Gold");
        }


        emailService.sendEmailForAdventureReservation(currentUser, reservation);
        currentUser.setNumberOfPoints(currentUser.getNumberOfPoints()+ lp.get().getReservationPoints());
        instructorUser.setNumberOfPoints(instructorUser.getNumberOfPoints()+ lp.get().getReservedPoints());
        return adventureReservationRepository.save(reservation);
    }

    public AdventureFreeTerms getDates(Long adventureId, Date startDate, Date endDate) {
        List<AdventureFreeTerms> freeTerms = freeTermsRepository.findAllByAdventureProfileId(adventureId);
        AdventureFreeTerms result = null;

        for (AdventureFreeTerms term : freeTerms) {
            if (term.getAdventureProfile().getId().equals(adventureId) && (isDateEqual(term.getStartDate(), startDate) || term.getStartDate().before(startDate)) && (isDateEqual(term.getEndDate(), endDate) || term.getEndDate().after(endDate)))
                result = term;

        }
        return result;
    }



    @Override
    public List<AdventureProfile> findAll() {
        return profileRepository.findAll();
    }

    @Override
    public boolean isOverlapping(long adventureId, Date startDate, Date endDate) {

        List<AdventureReservation> reservations = adventureReservationRepository.findAll();

        for(AdventureReservation reservation: reservations){

            if(reservation.getCancelled()) {
                continue;
            }

            if((isDateEqual(startDate, reservation.getStartDate()) || isDateEqual(endDate, reservation.getEndDate()))|| (isDateEqual(startDate, reservation.getEndDate()) || isDateEqual(endDate, reservation.getStartDate())) && reservation.getAdventureProfile().getId().equals(adventureId)) {
                return true;
            }

            if(startDate.after(reservation.getStartDate()) && startDate.before(reservation.getEndDate()) && reservation.getAdventureProfile().getId().equals(adventureId)){
                return true;
            }

            if(endDate.after(reservation.getStartDate()) && endDate.before(reservation.getEndDate()) && reservation.getAdventureProfile().getId().equals(adventureId)){
                return true;
            }
        }
        return false;
    }

    @Override
    public List<AdventureReservation> getMyReservations() {
        User user = userService.getCurrentUser();

        return adventureReservationRepository.getAllByClientIdAndCancelled(user.getId(), false);
    }

    @Override
    public boolean cancel(Long id) {

        Optional<AdventureReservation> reservation = adventureReservationRepository.findById(id);

        Date today = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(reservation.get().getStartDate());
        calendar.add(Calendar.DATE, -3);
        Date lastDayToCancel = calendar.getTime();

        if(lastDayToCancel.before(today) || reservation.get().getEndDate().before(today))
            return false;

        reservation.get().setCancelled(true);

        adventureReservationRepository.save(reservation.get());
        return true;
    }

    @Override
    public List<AdventureFreeTerms> getAllAdventuresOnAction() {

        return freeTermsRepository.findAllByIsAction(true);
    }

    @Override
    public boolean canClientBook(Long currentClientId, Long adventureId, Date startDate, Date endDate) {
        List<AdventureReservation> reservations = adventureReservationRepository.findAll();

        for(AdventureReservation reservation: reservations){
            if(reservation.getCancelled() && reservation.getAdventureProfile().getId().equals(adventureId) && reservation.getClientId().equals(currentClientId) && isDateEqual(reservation.getStartDate(), startDate) && isDateEqual(reservation.getEndDate(), endDate)){
                return false;
            }
        }
        return true;
    }

    @Override
    public AdventureReservation get(Long id) {
        return adventureReservationRepository.findById(id).get();
    }

    @Override
    public List<AdventureReservation> getMyFinishedReservations() {
        User user = userService.getCurrentUser();
        List<AdventureReservation> reservations = adventureReservationRepository.getAllByClientIdAndCancelled(user.getId(), false);
        List<AdventureReservation> results = new ArrayList<>();
        Date today = new Date();


        for(AdventureReservation reservation: reservations){
            if(reservation.getEndDate().before(today)){
                results.add(reservation);
            }
        }
        return results;
    }

    @Override
    public List<AdventureReservation> getMyUpcomingReservations() {
        User user = userService.getCurrentUser();
        List<AdventureReservation> reservations = adventureReservationRepository.getAllByClientIdAndCancelled(user.getId(), false);
        List<AdventureReservation> results = new ArrayList<>();
        Date today = new Date();

        for(AdventureReservation reservation: reservations){
            if(reservation.getStartDate().after(today)){
                results.add(reservation);
            }
        }
        return results;
    }

    @Override
    public List<AdventureReservation> getMyInProgressReservations() {
        User user = userService.getCurrentUser();
        List<AdventureReservation> reservations = adventureReservationRepository.getAllByClientIdAndCancelled(user.getId(), false);
        List<AdventureReservation> results = new ArrayList<>();
        Date today = new Date();

        for(AdventureReservation reservation: reservations){
            if((reservation.getStartDate().before(today) || isDateEqual(reservation.getStartDate(), today)) && (reservation.getEndDate().after(today) || isDateEqual(reservation.getEndDate(), today))){
                results.add(reservation);
            }
        }
        return results;
    }

    public boolean isDateEqual(Date date1, Date date2) {

        return date1.getDay() == date2.getDay() && date1.getYear() == date2.getYear() && date1.getMonth() == date2.getMonth();

    }


    public List<AdventureReservation> getAllAdventureReservations(Long instructorId, Long adventureId) {

        List<AdventureReservation> reservations = adventureReservationRepository.getAllByAdventureProfileId(adventureId);
        User currentUser = userService.getCurrentUser();
        List<AdventureReservation> results = new ArrayList<>();

        for (AdventureReservation reservation : reservations) {
            if (reservation.getAdventureProfile().getId().equals(adventureId) && reservation.getAdventureProfile().getInstructorId().equals(currentUser.getId())) {
                results.add(reservation);
            }
        }
        return results;
    }


    public List<AdventureReservation> getAllReservationsForMyAdventures(AdventureHistoryReservationsDTO dto) {

        List<AdventureReservation> all = adventureReservationRepository.findAll();
        List<AdventureReservation> results = new ArrayList<>();
        User currentUser = userService.getCurrentUser();
        Date today = new Date();

        for(AdventureReservation ar : all) {
            if(ar.getAdventureProfile().getInstructorId().equals(currentUser.getId()) && ar.getStartDate().after(today)) {
                results.add(ar);
            }
        }

        return results;
    }


    public List<AdventureReservation> getAllHistoryReservationsForMyAdventures(AdventureHistoryReservationsDTO dto) {

        List<AdventureReservation> all = adventureReservationRepository.findAll();
        List<AdventureReservation> results = new ArrayList<>();
        User currentUser = userService.getCurrentUser();
        Date today = new Date();

        for(AdventureReservation ar : all) {
            if(ar.getAdventureProfile().getInstructorId().equals(currentUser.getId()) && ar.getEndDate().before(today)) {
                results.add(ar);
            }
        }
        return results;
    }


    public List<AdventureReservation> getAllTodayReservationsForMyAdventures(AdventureHistoryReservationsDTO dto) {

        List<AdventureReservation> all = adventureReservationRepository.findAll();
        List<AdventureReservation> results = new ArrayList<>();
        User currentUser = userService.getCurrentUser();
        Date today = new Date();

        for(AdventureReservation ar : all) {
            if(ar.getAdventureProfile().getInstructorId().equals(currentUser.getId()) && (
                    (ar.getStartDate().before(today) && ar.getEndDate().after(today)) || (ar.getStartDate().before(today) && ar.getEndDate().equals(today))
                            || (ar.getStartDate().equals(today) && ar.getEndDate().after(today)) || (ar.getStartDate().equals(today) && ar.getEndDate().equals(today))
            )
            ) {
                results.add(ar);
            }
        }
        return results;
    }


    public List<AdventureReservation> getAllReservationsForCharts(AdventureHistoryReservationsDTO dto) {

        List<AdventureReservation> all = adventureReservationRepository.findAll();
        List<AdventureReservation> results = new ArrayList<>();
        User currentUser = userService.getCurrentUser();
        Date today = new Date();

        for(AdventureReservation ar : all) {
            if(ar.getAdventureProfile().getInstructorId().equals(currentUser.getId())) {
                results.add(ar);
            }
        }
        return results;
    }

    public List<AdventureReservation> getAllAdventureReservationsForCharts(AdventureHistoryReservationsDTO dto) {

        List<AdventureReservation> all = adventureReservationRepository.findAll();
        List<AdventureReservation> results = new ArrayList<>();
        User currentUser = userService.getCurrentUser();
        Date today = new Date();

        for(AdventureReservation ar : all) {

                results.add(ar);

        }
        return results;
    }



}
