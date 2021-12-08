package com.ISA.service.implementation;

import com.ISA.config.SecurityUtils;
import com.ISA.domain.dto.RegistrationDTO;
import com.ISA.domain.model.User;
import com.ISA.repository.UserRepository;
import com.ISA.service.definition.UserService;
import org.apache.catalina.security.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private PasswordEncoder encoder;

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
        user.setType("Client");
        
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
        user.setType("House owner");

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

        return userRepository.save(user);
    }

    @Override
    public User getCurrentUser() {

        String email = SecurityUtils.getCurrentUserLogin().get();

        return userRepository.getByEmail(email);
    }
}
