package com.ISA.service.definition;

import com.ISA.domain.dto.HomeComplaintsDTO;
import com.ISA.domain.model.AdventureComplaints;
import com.ISA.domain.model.HomeComplaints;

import java.util.List;


public interface HomeComplaintsService {
    boolean canUserSendComplaints(Long currentClientId, Long houseReservationId);
    HomeComplaints add(HomeComplaintsDTO dto);
    List<HomeComplaints> getAllHomeComplaints();
    List<HomeComplaints>  getAllComplaintsByComplaintResponse();
}
