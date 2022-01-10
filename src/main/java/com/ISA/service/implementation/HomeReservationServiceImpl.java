package com.ISA.service.implementation;

import com.ISA.domain.dto.HomeReservationDTO;
import com.ISA.domain.model.HomeFreeTerms;
import com.ISA.domain.model.HomeProfile;
import com.ISA.domain.model.HomeReservation;
import com.ISA.domain.model.User;
import com.ISA.repository.HomeFreeTermsRepository;
import com.ISA.repository.HomeProfileRepository;
import com.ISA.repository.HomeReservationRepository;
import com.ISA.service.definition.EmailService;
import com.ISA.service.definition.HomeReservationService;
import com.ISA.service.definition.UserService;
import org.hibernate.annotations.NotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.*;

@Service
public class HomeReservationServiceImpl implements HomeReservationService {

    @Autowired
    private HomeReservationRepository homeReservationRepository;

    @Autowired
    private HomeProfileRepository homeProfileRepository;

    @Autowired
    private HomeFreeTermsRepository homeFreeTermsRepository;


    @Autowired
    private EmailService emailService;

    @Autowired
    private UserService userService;

    @Override
    public HomeReservation add(HomeReservationDTO dto) {

        HomeProfile homeProfile = homeProfileRepository.findById(dto.getHouseId()).get();

        User currentUser = userService.getCurrentUser();

        if(isOverlapping(homeProfile.getId(), dto.getStartDate(), dto.getEndDate())){
            return null;
        }

        if(!canClientBook(currentUser.getId(), dto.getHouseId(), dto.getStartDate(), dto.getEndDate() )){
            return null;
        }

        HomeReservation reservation = new HomeReservation();
        reservation.setExtraServices(homeProfile.getExtraService());
        reservation.setCancelled(false);
        reservation.setEndDate(dto.getEndDate());
        reservation.setStartDate(dto.getStartDate());
        reservation.setHomeProfile(homeProfile);
        reservation.setPrice(homeProfile.getPricelist());
        reservation.setClientId(currentUser.getId());
        reservation.setNumberOfPeople(homeProfile.getNumberOfBeds());


        emailService.sendEmailForHouseReservation(userService.getCurrentUser(), reservation);

        return homeReservationRepository.save(reservation);
    }



    @Override
    public List<HomeProfile> findAll() {

        return homeProfileRepository.findAll();
    }

    @Override
    public boolean isOverlapping(long houseId, Date startDate, Date endDate){

        List<HomeReservation> reservations = homeReservationRepository.findAll();

        for(HomeReservation reservation: reservations){

            if(reservation.getCancelled()) {
                continue;
            }


           if((startDate.equals(reservation.getStartDate()) || endDate.equals(reservation.getEndDate()) || (startDate.equals(reservation.getEndDate())) ||  (endDate.equals(reservation.getStartDate()))) && reservation.getHomeProfile().getId().equals(houseId)) {
                return true;
            }

            if(startDate.after(reservation.getStartDate()) && startDate.before(reservation.getEndDate()) && reservation.getHomeProfile().getId().equals(houseId)){
                return true;
            }

            if(endDate.after(reservation.getStartDate()) && endDate.before(reservation.getEndDate()) && reservation.getHomeProfile().getId().equals(houseId)){
                return true;
            }
        }
        return false;
    }

    @Override
    public List<HomeReservation> getMyReservations() {
        User user = userService.getCurrentUser();

        return homeReservationRepository.getAllByClientIdAndCancelled(user.getId(), false);
    }

    @Override
    public boolean cancel(Long id) {
        Optional<HomeReservation> reservation = homeReservationRepository.findById(id);

        Date today = new Date();

       if(reservation.get().getStartDate().before(today))
           return false;

        reservation.get().setCancelled(true);
        homeReservationRepository.save(reservation.get());
        return true;
    }

    @Override
    public List<HomeProfile> getAllActions() {
        List<HomeProfile> homes = new ArrayList<>();
        List<HomeFreeTerms> actions = homeFreeTermsRepository.findAllByIsAction(true);

        for (HomeFreeTerms term: actions) {
            if(term.isAction() == true ){
                if(!homeExists(term.getHomeProfile(), homes)){
                    homes.add(term.getHomeProfile());
                }

            }
        }
        return homes;
    }

    @Override
    public boolean homeExists(HomeProfile home, List<HomeProfile> homes) {

        for(HomeProfile profile: homes){
            if(profile.getId().equals(home.getId())){
                return true;
            }
        }
        return false;
    }

    @Override
    public List<HomeFreeTerms> getAllActionDates(){

        return homeFreeTermsRepository.findAllByIsAction(true);
    }

    @Override
    public boolean canClientBook(Long currentClientId, Long houseId, Date startDate, Date endDate) {
        List<HomeReservation> reservations = homeReservationRepository.findAll();

        for(HomeReservation reservation: reservations){
            System.out.println("==============================");
            System.out.println(reservation.getId());
            System.out.println(reservation.getCancelled());
            System.out.println(reservation.getHomeProfile().getId().equals(houseId));
            System.out.println(reservation.getClientId().equals(currentClientId));
            System.out.println(isDateEqual(reservation.getStartDate(), startDate));
            System.out.println(isDateEqual(reservation.getEndDate(), endDate));

            if(reservation.getCancelled() && reservation.getHomeProfile().getId().equals(houseId) && reservation.getClientId().equals(currentClientId) && isDateEqual(reservation.getStartDate(), startDate) && isDateEqual(reservation.getEndDate(), endDate)){
                return false;
            }

        }
        return true;
    }

    public boolean isDateEqual(Date date1, Date date2) {

        return date1.getDay() == date2.getDay() && date1.getYear() == date2.getYear() && date1.getMonth() == date2.getMonth();

    }
}
