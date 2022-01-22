package com.ISA.service.definition;

import com.ISA.domain.dto.AdventureComplaintsDTO;
import com.ISA.domain.dto.AdventureProfileDTO;
import com.ISA.domain.model.AdventureComplaints;
import com.ISA.domain.model.AdventureProfile;

import java.util.List;

public interface AdventureComplaintsService {
    boolean canUserSendComplaints(Long currentClientId, Long adventureReservationId);
    AdventureComplaints add(AdventureComplaintsDTO dto);
    List<AdventureComplaints>  getAllAdventureComplaints();
    List<AdventureComplaints>  getAllComplaintsByComplaintResponse();
    AdventureComplaints responseToComplaint(AdventureComplaintsDTO dto);
}
