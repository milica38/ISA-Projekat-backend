package com.ISA.service.definition;

import com.ISA.domain.dto.AdventureEvaluationsDTO;
import com.ISA.domain.dto.BoatEvaluationsDTO;
import com.ISA.domain.model.AdventureComplaints;
import com.ISA.domain.model.AdventureEvaluations;
import com.ISA.domain.model.BoatEvaluations;

import java.util.List;

public interface AdventureEvaluationsService {
    boolean canUserSendEvaluations(Long currentClientId, Long adventureReservationId);
    AdventureEvaluations add(AdventureEvaluationsDTO dto);
    List<AdventureEvaluations> getAllAdventureEvaluations();
    List<AdventureEvaluations> getAllEvaluationsByApprovedAndDeclined();
    Boolean evaluationApproved(Long id);
    Boolean evaluationDeclined(Long id);

}
