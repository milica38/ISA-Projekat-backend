package com.ISA.service.definition;

import com.ISA.domain.dto.AdventureComplaintsDTO;
import com.ISA.domain.model.AdventureComplaints;

public interface AdventureComplaintsService {
    boolean canUserSendComplaints(Long currentClientId, Long adventureReservationId);
    AdventureComplaints add(AdventureComplaintsDTO dto);
}
