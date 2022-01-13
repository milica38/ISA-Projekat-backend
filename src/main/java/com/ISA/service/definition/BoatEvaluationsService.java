package com.ISA.service.definition;

import com.ISA.domain.dto.BoatEvaluationsDTO;
import com.ISA.domain.model.BoatEvaluations;

public interface BoatEvaluationsService {
    boolean canUserSendEvaluations(Long currentClientId, Long boatReservationId);
    BoatEvaluations add(BoatEvaluationsDTO dto);
}
