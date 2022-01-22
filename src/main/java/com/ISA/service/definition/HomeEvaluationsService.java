package com.ISA.service.definition;

import com.ISA.domain.dto.HomeEvaluationsDTO;
import com.ISA.domain.model.AdventureEvaluations;
import com.ISA.domain.model.HomeEvaluations;

import java.util.List;

public interface HomeEvaluationsService {
    boolean canUserSendEvaluations(Long currentClientId, Long houseReservationId);
    HomeEvaluations add(HomeEvaluationsDTO dto);
    List<HomeEvaluations> getAllHomeEvaluations();
    List<HomeEvaluations> getAllEvaluationsByApprovedAndDeclined();
    Boolean evaluationApproved(Long id);
    Boolean evaluationDeclined(Long id);
}
