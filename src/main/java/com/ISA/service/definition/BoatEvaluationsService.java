package com.ISA.service.definition;

import com.ISA.domain.dto.BoatEvaluationsDTO;
import com.ISA.domain.model.AdventureEvaluations;
import com.ISA.domain.model.BoatEvaluations;

import java.util.List;

public interface BoatEvaluationsService {
    boolean canUserSendEvaluations(Long currentClientId, Long boatReservationId);
    BoatEvaluations add(BoatEvaluationsDTO dto);
    List<BoatEvaluations> getAllBoatEvaluations();
    List<BoatEvaluations> getAllEvaluationsByApprovedAndDeclined();
    Boolean evaluationApproved(Long id);
    Boolean evaluationDeclined(Long id);
}
