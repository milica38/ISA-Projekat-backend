package com.ISA.service.definition;

import com.ISA.domain.dto.HomeEvaluationsDTO;
import com.ISA.domain.model.HomeEvaluations;

public interface HomeEvaluationsService {
    boolean canUserSendEvaluations(Long currentClientId, Long houseReservationId);
    HomeEvaluations add(HomeEvaluationsDTO dto);
}
