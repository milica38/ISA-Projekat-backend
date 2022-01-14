package com.ISA.service.definition;

import com.ISA.domain.dto.AdventureEvaluationsDTO;
import com.ISA.domain.dto.BoatEvaluationsDTO;
import com.ISA.domain.model.AdventureEvaluations;
import com.ISA.domain.model.BoatEvaluations;

public interface AdventureEvaluationsService {
    boolean canUserSendEvaluations(Long currentClientId, Long adventureReservationId);
    AdventureEvaluations add(AdventureEvaluationsDTO dto);

}
