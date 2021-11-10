package com.ISA.service.definition;

import com.ISA.domain.dto.RegistrationDTO;
import com.ISA.domain.model.User;

public interface UserService {

    User clientRegistration(RegistrationDTO registrationDTO);
}
