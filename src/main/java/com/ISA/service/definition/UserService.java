package com.ISA.service.definition;

import com.ISA.domain.dto.ChangePasswordDTO;
import com.ISA.domain.dto.HomeFreeTermsDTO;
import com.ISA.domain.dto.RegistrationDTO;
import com.ISA.domain.dto.UserDTO;
import com.ISA.domain.model.HomeFreeTerms;
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
    User registrationDeclined(UserDTO userDTO) throws Exception;
    User requestForDeletingAccount(UserDTO userDTO);
    User deleteUserAccount(UserDTO userDTO) throws Exception;
    User edit(UserDTO userDTO);
    List<User> getNullStatusUsers();
    List<User> getPendingStatusUsers();
    List<User> getAllUsers();
    Boolean deleteUser(Long id);
    User changePassword(ChangePasswordDTO changePasswordDTO);
    boolean delete(Long id);
    User findById(Long id);
    List<User> findAllByType();
    List<User> findAllByInstructor();
    List<User> findAllByHomeOwner();
    List<User> findAllByBoatOwner();
    List<User> filterUsers(UserDTO dto);
    List<User> getActiveStatusUsers();
    boolean deleteThisUser(Long id);
    User getUserById(Long id);

}
