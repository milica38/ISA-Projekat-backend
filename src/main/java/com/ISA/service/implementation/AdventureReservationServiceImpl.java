package com.ISA.service.implementation;

import com.ISA.domain.dto.AdventureProfileDTO;
import com.ISA.domain.dto.AdventureReservationDTO;
import com.ISA.domain.model.*;
import com.ISA.repository.AdventureFreeTermsRepository;
import com.ISA.repository.AdventureProfileRepository;
import com.ISA.repository.AdventureReservationRepository;
import com.ISA.service.definition.AdventureReservationService;
import com.ISA.service.definition.EmailService;
import com.ISA.service.definition.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class AdventureReservationServiceImpl implements AdventureReservationService {

    @Autowired
    private AdventureReservationRepository adventureReservationRepository;

    @Autowired
    private AdventureProfileRepository profileRepository;

    @Autowired
    private AdventureFreeTermsRepository freeTermsRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private UserService userService;


    @Override
    public AdventureReservation add(AdventureReservationDTO dto) {

        AdventureProfile adventureProfile = profileRepository.findById(dto.getAdventureId()).get();
        User currentUser = userService.getCurrentUser();

        if(isOverlapping(adventureProfile.getId(), dto.getStartDate(), dto.getEndDate())){
            return null;
        }

        if(!canClientBook(currentUser.getId(), dto.getAdventureId(), dto.getStartDate(), dto.getEndDate() )){
            return null;
        }

        AdventureReservation reservation = new AdventureReservation();
        reservation.setExtraServices(dto.getExtraServices());
        reservation.setCancelled(false);
        reservation.setEndDate(dto.getEndDate());
        reservation.setStartDate(dto.getStartDate());
        reservation.setClientId(currentUser.getId());
        reservation.setAdventureProfile(adventureProfile);
        adventureProfile.setPriceList(dto.getPrice());

        emailService.sendEmailForAdventureReservation(currentUser, reservation);

        return adventureReservationRepository.save(reservation);
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

            if((startDate.equals(reservation.getStartDate()) || endDate.equals(reservation.getEndDate()) || (startDate.equals(reservation.getEndDate())) ||  (endDate.equals(reservation.getStartDate()))) && reservation.getAdventureProfile().getId().equals(adventureId)) {
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

        if(lastDayToCancel.before(today))
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
            System.out.println("==============================");
            System.out.println(reservation.getId());
            System.out.println(reservation.getCancelled());
            System.out.println(reservation.getAdventureProfile().getId().equals(adventureId));
            System.out.println(reservation.getClientId().equals(currentClientId));
            System.out.println(isDateEqual(reservation.getStartDate(), startDate));
            System.out.println(isDateEqual(reservation.getEndDate(), endDate));

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

    public boolean isDateEqual(Date date1, Date date2) {

        return date1.getDay() == date2.getDay() && date1.getYear() == date2.getYear() && date1.getMonth() == date2.getMonth();

    }
}
