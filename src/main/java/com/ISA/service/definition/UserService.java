package com.ISA.service.definition;

import com.ISA.domain.dto.ChangePasswordDTO;
import com.ISA.domain.dto.HomeFreeTermsDTO;
import com.ISA.domain.dto.RegistrationDTO;
import com.ISA.domain.dto.UserDTO;
import com.ISA.domain.model.HomeFreeTerms;
import com.ISA.domain.model.User;

public interface UserService {

    User clientRegistration(RegistrationDTO registrationDTO);
    User houseOwnerRegistration(RegistrationDTO registrationDTO);
    User boatOwnerRegistration(RegistrationDTO registrationDTO);
    User getCurrentUser();
    Boolean findUserByToken(String token);
    User edit(UserDTO userDTO);
    User changePassword(ChangePasswordDTO changePasswordDTO);
    boolean delete(Long id);


}
