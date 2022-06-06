package com.ISA.service.implementation;

import com.ISA.domain.dto.BoatComplaintsDTO;
import com.ISA.domain.dto.HomeComplaintsDTO;
import com.ISA.domain.model.*;
import com.ISA.repository.HomeComplaintsRepository;
import com.ISA.repository.HomeReservationRepository;
import com.ISA.service.definition.EmailService;
import com.ISA.service.definition.HomeComplaintsService;
import com.ISA.service.definition.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class HomeComplaintsServiceImpl implements HomeComplaintsService {

    @Autowired
    private HomeReservationRepository homeReservationRepository;

    @Autowired
    private HomeComplaintsRepository homeComplaintsRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private EmailService emailService;


    @Override
    public boolean canUserSendComplaints(Long currentClientId, Long houseReservationId) {
        List<HomeReservation> reservations = homeReservationRepository.findAll();

        Date currentDate = new Date();
        for(HomeReservation reservation: reservations){
            if(reservation.getCancelled() == false && reservation.getId().equals(houseReservationId) && reservation.getClientId().equals(currentClientId) && reservation.getEndDate().before(currentDate)){
                return false;
            }
        }
        return true;
    }

    @Override
    public HomeComplaints add(HomeComplaintsDTO dto) {
        HomeReservation reservation = homeReservationRepository.findById(dto.getHomeReservationId()).get();
        User currentUser = userService.getCurrentUser();

        if(canUserSendComplaints(currentUser.getId(), dto.getHomeReservationId())){
            return null;
        }

        HomeComplaints complaints = new HomeComplaints();
        complaints.setContent(dto.getContent());
        complaints.setClientId(currentUser.getId());
        complaints.setHomeReservation(reservation);

        return homeComplaintsRepository.save(complaints);
    }

    public List<HomeComplaints> getAllHomeComplaints() {

        return homeComplaintsRepository.findAll();

    }

    public List<HomeComplaints> getAllComplaintsByComplaintResponse()
    {
        return homeComplaintsRepository.findAllByComplaintResponse("");
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public HomeComplaints responseToComplaint(HomeComplaintsDTO dto) throws Exception{

        Optional<HomeComplaints> optionalHomeComplaints = homeComplaintsRepository.findById(dto.getId());
        User user = userService.getUserById(optionalHomeComplaints.get().getClientId());

        optionalHomeComplaints.get().setComplaintResponse(dto.getComplaintResponse());
        emailService.sendEmailForHomeComplaintResponse(user,optionalHomeComplaints );
        return homeComplaintsRepository.save(optionalHomeComplaints.get());

    }
}
