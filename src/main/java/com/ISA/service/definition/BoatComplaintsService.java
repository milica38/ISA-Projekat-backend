package com.ISA.service.definition;

import com.ISA.domain.dto.BoatComplaintsDTO;
import com.ISA.domain.model.BoatComplaints;

public interface BoatComplaintsService {
    boolean canUserSendComplaints(Long currentClientId, Long boatReservationId);
    BoatComplaints add(BoatComplaintsDTO dto);
}
