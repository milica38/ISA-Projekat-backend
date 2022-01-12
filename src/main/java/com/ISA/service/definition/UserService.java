package com.ISA.service.definition;

import com.ISA.domain.dto.RegistrationDTO;
import com.ISA.domain.dto.UserDTO;
import com.ISA.domain.model.User;

import java.util.List;

public interface UserService {

    User clientRegistration(RegistrationDTO registrationDTO);
    User houseOwnerRegistration(RegistrationDTO registrationDTO);
    User boatOwnerRegistration(RegistrationDTO registrationDTO);
    User fishingInstructorRegistration(RegistrationDTO registrationDTO);
    User adminRegistration(RegistrationDTO registrationDTO);
    User getCurrentUser();
    Boolean findUserByToken(String token);
    Boolean registrationApproved(Long id);
    Boolean registrationDeclined(Long id);
    User edit(UserDTO userDTO);
    List<User> getNullStatusUsers();
    List<User> getAllUsers();
    Boolean delete(Long id);

}
