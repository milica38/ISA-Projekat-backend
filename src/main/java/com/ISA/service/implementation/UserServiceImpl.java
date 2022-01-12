package com.ISA.service.implementation;

import com.ISA.config.SecurityUtils;
import com.ISA.domain.dto.PasswordDTO;
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

import java.util.List;
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
        user.setPassword(registrationDTO.getPassword());
        user.setRegistrationToken(generateRandomToken());
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
        user.setPassword(registrationDTO.getPassword());
        user.setRegistrationToken(generateRandomToken());
        return userRepository.save(user);
    }

    public User adminRegistration(RegistrationDTO registrationDTO) {

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
        user.setPassword(registrationDTO.getPassword());
        user.setRegistrationToken(generateRandomToken());
        user.setType("Admin");

        return userRepository.save(user);
    }

    public User fishingInstructorRegistration(RegistrationDTO registrationDTO) {

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
        user.setPassword(registrationDTO.getPassword());
        user.setRegistrationToken(generateRandomToken());
        user.setType("Fishing instructor");

        return userRepository.save(user);
    }

    @Override
    public User getCurrentUser() {

        String email = SecurityUtils.getCurrentUserLogin().get();
        return userRepository.findOneByEmail(email).get();
    }

    @Override
    public List<User> getNullStatusUsers()
    {
        return userRepository.findAllUsersByStatus(null);
    }


    public List<User> getAllUsers()
    {
        return userRepository.findAll();
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

    public Boolean registrationApproved(Long id){
        Optional<User> user = userRepository.findById(id);

        if(user.isEmpty()){
            return false;
        }
        user.get().setStatus("Active");
        userRepository.save(user.get());
        return true;
    }

    public Boolean registrationDeclined(Long id)
    {
        Optional<User> user = userRepository.findById(id);

        if(user.isEmpty()){
            return false;
        }
        user.get().setStatus("Declined");
        userRepository.save(user.get());
        return true;
    }


    public void changePassword(PasswordDTO passwordDTO)
    {

    }

    public Boolean delete(Long id)
    {
        Optional<User> user = userRepository.findById(id);

        if(user.get().getType().equals("Admin")){
            return false;
        }

        userRepository.delete(user.get());

       return true;
    }


}
