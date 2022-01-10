package com.ISA.service.definition;

import com.ISA.domain.dto.HomeComplaintsDTO;
import com.ISA.domain.model.HomeComplaints;

import java.util.Date;

public interface HomeComplaintsService {
    boolean canUserSendComplaints(Long currentClientId, Long houseReservationId);
    HomeComplaints add(HomeComplaintsDTO dto);
}
