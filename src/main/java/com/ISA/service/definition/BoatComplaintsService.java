package com.ISA.service.definition;

import com.ISA.domain.dto.BoatComplaintsDTO;
import com.ISA.domain.model.AdventureComplaints;
import com.ISA.domain.model.BoatComplaints;

import java.util.List;

public interface BoatComplaintsService {
    boolean canUserSendComplaints(Long currentClientId, Long boatReservationId);
    BoatComplaints add(BoatComplaintsDTO dto);
    List<BoatComplaints> getAllBoatComplaints();
    List<BoatComplaints>  getAllComplaintsByComplaintResponse();
}
