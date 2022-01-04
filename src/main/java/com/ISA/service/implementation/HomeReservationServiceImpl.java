package com.ISA.service.implementation;

import com.ISA.domain.dto.HomeReservationDTO;
import com.ISA.domain.model.HomeProfile;
import com.ISA.domain.model.HomeReservation;
import com.ISA.domain.model.User;
import com.ISA.repository.HomeProfileRepository;
import com.ISA.repository.HomeReservationRepository;
import com.ISA.service.definition.EmailService;
import com.ISA.service.definition.HomeReservationService;
import com.ISA.service.definition.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;

@Service
public class HomeReservationServiceImpl implements HomeReservationService {

    @Autowired
    private HomeReservationRepository homeReservationRepository;


    @Autowired
    private HomeProfileRepository homeProfileRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private UserService userService;

    @Override
    public HomeReservation add(HomeReservationDTO dto) {

        HomeProfile homeProfile = homeProfileRepository.findById(dto.getHouseId()).get();

        if(isOverlapping(homeProfile.getId(), dto.getStartDate(), dto.getEndDate())){
            return null;
        }
        HomeReservation reservation = new HomeReservation();
        reservation.setExtraServices(dto.getExtraServices());
        reservation.setPrice(dto.getPrice());
        reservation.setCancelled(dto.getCancelled());
        reservation.setEndDate(dto.getEndDate());
        reservation.setStartDate(dto.getStartDate());
        reservation.setNumberOfDays(dto.getNumberOfDays());
        reservation.setNumberOfPeople(dto.getNumberOfPeople());
        reservation.setHomeProfile(homeProfile);
        homeProfile.setPricelist(dto.getPrice());

        emailService.sendEmailForHouseReservation(userService.getCurrentUser());

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

        return homeReservationRepository.getAllByClientId(user.getId());
    }


}
