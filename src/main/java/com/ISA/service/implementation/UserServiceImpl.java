package com.ISA.service.implementation;

import com.ISA.config.SecurityUtils;
import com.ISA.domain.dto.ChangePasswordDTO;
import com.ISA.domain.dto.RegistrationDTO;
import com.ISA.domain.dto.UserDTO;
import com.ISA.domain.model.HomeProfile;
import com.ISA.domain.model.User;
import com.ISA.repository.UserRepository;
import com.ISA.service.definition.EmailService;
import com.ISA.service.definition.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.UUID;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private EmailService emailService;

    @Override
    public User clientRegistration(RegistrationDTO registrationDTO) {

        Optional<User> optionalUser = userRepository.findByEmail(registrationDTO.getEmail());

        if(!optionalUser.isEmpty()) {
            return null;
        }

        User user = new User();
        user.setPassword(encoder.encode(registrationDTO.getPassword()));
        user.setEmail(registrationDTO.getEmail());
        user.setAddress(registrationDTO.getAddress());
        user.setCity(registrationDTO.getCity());
        user.setCountry(registrationDTO.getCountry());
        user.setDescription(registrationDTO.getDescription());
        user.setName(registrationDTO.getName());
        user.setSurname(registrationDTO.getSurname());
        user.setPhoneNumber(registrationDTO.getPhoneNumber());
        user.setRegistrationToken(generateRandomToken());
        user.setType("Client");
        user.setStatus("Waiting");

        emailService.sendEmailForRegistration(user);

        return userRepository.save(user);
    }

    public User houseOwnerRegistration(RegistrationDTO registrationDTO) {

        Optional<User> optionalUser = userRepository.findByEmail(registrationDTO.getEmail());

        if(!optionalUser.isEmpty()) {
            return null;
        }

        User user = new User();
        user.setPassword(encoder.encode(registrationDTO.getPassword()));
        user.setEmail(registrationDTO.getEmail());
        user.setAddress(registrationDTO.getAddress());
        user.setCity(registrationDTO.getCity());
        user.setCountry(registrationDTO.getCountry());
        user.setDescription(registrationDTO.getDescription());
        user.setName(registrationDTO.getName());
        user.setSurname(registrationDTO.getSurname());
        user.setPhoneNumber(registrationDTO.getPhoneNumber());
        user.setDescription(registrationDTO.getDescription());
        user.setRegistrationToken(generateRandomToken());
        user.setType("House owner");
        user.setStatus("Waiting");

        return userRepository.save(user);
    }

    public User boatOwnerRegistration(RegistrationDTO registrationDTO) {

        Optional<User> optionalUser = userRepository.findByEmail(registrationDTO.getEmail());

        if(!optionalUser.isEmpty()) {
            return null;
        }

        User user = new User();
        user.setPassword(encoder.encode(registrationDTO.getPassword()));
        user.setEmail(registrationDTO.getEmail());
        user.setAddress(registrationDTO.getAddress());
        user.setCity(registrationDTO.getCity());
        user.setCountry(registrationDTO.getCountry());
        user.setDescription(registrationDTO.getDescription());
        user.setName(registrationDTO.getName());
        user.setSurname(registrationDTO.getSurname());
        user.setPhoneNumber(registrationDTO.getPhoneNumber());
        user.setDescription(registrationDTO.getDescription());
        user.setType("Boat owner");
        user.setStatus("Waiting");
        user.setRegistrationToken(generateRandomToken());
        return userRepository.save(user);
    }

    @Override
    public User getCurrentUser() {

        String email = SecurityUtils.getCurrentUserLogin().get();
        return userRepository.findOneByEmail(email).get();
    }

    @Override
    public User edit(UserDTO userDTO) {

        Optional<User> optionalUser = userRepository.findById(getCurrentUser().getId());

        if (userDTO.getName() != null && !userDTO.getName().equals("")){
            optionalUser.get().setName(userDTO.getName());
        }
        if (userDTO.getAddress() != null && !userDTO.getAddress().equals("")){
            optionalUser.get().setAddress(userDTO.getAddress());
        }
        if (userDTO.getCity() != null && !userDTO.getCity().equals("")){
            optionalUser.get().setCity(userDTO.getCity());
        }
        if (userDTO.getCountry() != null && !userDTO.getCountry().equals("")){
            optionalUser.get().setCountry(userDTO.getCountry());
        }
        if (userDTO.getDescription() != null && !userDTO.getDescription().equals("")){
            optionalUser.get().setDescription(userDTO.getDescription());
        }
        if (userDTO.getEmail() != null && !userDTO.getEmail().equals("")){
            optionalUser.get().setEmail(userDTO.getEmail());
        }
        if (userDTO.getPhoneNumber() != null && !userDTO.getPhoneNumber().equals("")){
            optionalUser.get().setPhoneNumber(userDTO.getPhoneNumber());
        }
        if (userDTO.getSurname() != null && !userDTO.getSurname().equals("")){
            optionalUser.get().setSurname(userDTO.getSurname());
        }
        if (userDTO.getPassword() != null && !userDTO.getPassword().equals("")){
            optionalUser.get().setPassword(userDTO.getPassword());
        }

        return userRepository.save(optionalUser.get());
    }

    @Override
    public User changePassword(ChangePasswordDTO changePasswordDTO) {

        User user = getCurrentUser();

        if(!encoder.matches(changePasswordDTO.getOldPassword(), user.getPassword())) {
            return null;
        }

        user.setPassword(encoder.encode(changePasswordDTO.getNewPassword()));

        return userRepository.save(user);
    }

    public static String generateRandomToken(){
        String uuid = UUID.randomUUID().toString();
        return uuid;
    }

    public Boolean findUserByToken(String token){
        Optional<User> user = userRepository.findUserByRegistrationToken(token);

        if(user == null){
           return false;
        }
        user.get().setStatus("Active");
        userRepository.save(user.get());
        return true;
    }

    @Override
    public boolean delete(Long id) {
        Optional<User> user = userRepository.findById(getCurrentUser().getId());
        user.get().setStatus("Pending");
        userRepository.save(user.get());
        return true;
    }
}
