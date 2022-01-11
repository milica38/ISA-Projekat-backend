package com.ISA.service.definition;

import com.ISA.domain.dto.HomeComplaintsDTO;
import com.ISA.domain.model.HomeComplaints;



public interface HomeComplaintsService {
    boolean canUserSendComplaints(Long currentClientId, Long houseReservationId);
    HomeComplaints add(HomeComplaintsDTO dto);
}
