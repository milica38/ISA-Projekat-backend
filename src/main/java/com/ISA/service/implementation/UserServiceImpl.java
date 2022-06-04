package com.ISA.service.implementation;

import com.ISA.config.SecurityUtils;
import com.ISA.domain.dto.PasswordDTO;
import com.ISA.domain.dto.ChangePasswordDTO;
import com.ISA.domain.dto.RegistrationDTO;
import com.ISA.domain.dto.UserDTO;
import com.ISA.domain.model.HomeProfile;
import com.ISA.domain.model.User;
import com.ISA.repository.HomeFreeTermsRepository;
import com.ISA.repository.UserRepository;
import com.ISA.service.definition.EmailService;
import com.ISA.service.definition.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.UUID;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private EmailService emailService;

    @Autowired
    private HomeFreeTermsRepository homeFreeTermsRepository;

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
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
        user.setCategory("Regular");
        user.setPenalty(0L);
        user.setNumberOfPoints(0);
        user.setType("Client");
        user.setStatus(null);


        emailService.sendEmailForRegistration(user);

        return userRepository.save(user);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
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
        user.setCategory("Regular");
        user.setType("House owner");
        user.setNumberOfPoints(0);
        user.setStatus(null);


        return userRepository.save(user);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
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
        user.setCategory("Regular");
        user.setStatus(null);
        user.setNumberOfPoints(0);

        user.setRegistrationToken(generateRandomToken());
        return userRepository.save(user);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
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
        user.setType("NewAdmin");


        return userRepository.save(user);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
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
        user.setType("Fishing instructor");
        user.setCategory("Regular");
        user.setStatus(null);
        user.setNumberOfPoints(0);

        user.setRegistrationToken(generateRandomToken());
        return userRepository.save(user);
    }

    @Override
    public User getCurrentUser() {

        String email = SecurityUtils.getCurrentUserLogin().get();
        return userRepository.findOneByEmail(email).get();
    }

    public User getUserById(Long id)
    {
        return userRepository.getById(id);
    }

    @Override

    public List<User> getNullStatusUsers()
    {
        return userRepository.findAllUsersByStatus(null);
    }

    public List<User> getActiveStatusUsers()
    {
        return userRepository.findAllUsersByStatus("Active");
    }
    public List<User> getPendingStatusUsers()
    {
        return userRepository.findAllUsersByStatus("Pending");
    }

    public List<User> getAllUsers()
    {
        return userRepository.findAll();
    }


    @Override

    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)

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
        if(user.getType().equals("NewAdmin")){
            user.setType("Admin");
        }
        return userRepository.save(user);
    }

    public static String generateRandomToken(){
        String uuid = UUID.randomUUID().toString();
        return uuid;
    }

    public Boolean findUserByToken(String token) {
        Optional<User> user = userRepository.findUserByRegistrationToken(token);

        if (user == null) {
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
        emailService.sendEmailForRegistrationApproved(user.get());
        userRepository.save(user.get());
        return true;
    }

    public User registrationDeclined(UserDTO userDTO)
    {
        Optional<User> user = userRepository.findById(userDTO.getId());


        user.get().setStatus("Declined");
        user.get().setCancelReason(userDTO.getCancelReason());


        emailService.sendEmailForRegistrationDeclined(user.get());
        return userRepository.save(user.get());

    }

    public User requestForDeletingAccount(UserDTO userDTO)
    {
        Optional<User> user = userRepository.findById(userDTO.getId());


        user.get().setStatus("Pending");
        user.get().setReasonForDelete(userDTO.getReasonForDelete());


        return userRepository.save(user.get());

    }

    public User deleteUserAccount(UserDTO userDTO)
    {
        Optional<User> user = userRepository.findById(userDTO.getId());


        user.get().setStatus("Deleted");
        user.get().setCancelReason(userDTO.getCancelReason());
        emailService.sendEmailForRegistrationDeclined(user.get());
        return userRepository.save(user.get());

    }

    public boolean deleteThisUser(Long id)
    {
        Optional<User> user = userRepository.findById(id);

        if(user.isEmpty()){
            return false;
        }
        user.get().setStatus("Deleted");
        emailService.sendEmailForRegistrationDeclined(user.get());
        userRepository.save(user.get());
        return true;
    }


    public void changePassword(PasswordDTO passwordDTO)
    {

    }

    public Boolean deleteUser(Long id)
    {
        Optional<User> user = userRepository.findById(id);

        if(user.get().getType().equals("Admin")){
            return false;
        }

        userRepository.delete(user.get());

       return true;
    }




    public  String getUserInfo(Long id){
        Optional<User> user = userRepository.findById(id);
        String name = user.get().getName();
        String surname = user.get().getSurname();

        return name + surname;
    }



    @Override
    public boolean delete(Long id) {
        Optional<User> user = userRepository.findById(getCurrentUser().getId());
        user.get().setStatus(null);
        userRepository.save(user.get());
        return true;
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).get();
    }

    @Override
    public List<User> findAllByType() {
        return userRepository.findAllByType("Client");
    }


    public List<User> findAllByInstructor() {
        return userRepository.findAllByType("Fishing instructor");
    }


    public List<User> findAllByHomeOwner() {
        return userRepository.findAllByType("Home owner");
    }

    public List<User> findAllByBoatOwner() {
        return userRepository.findAllByType("Boat owner");
    }


    @Override
    public List<User> filterUsers(UserDTO dto) {
        List<User> clients = userRepository.findAllByType("Client");
        List<User> results = new ArrayList<>();

        for(User user: clients){
            if(user.getName().toLowerCase().contains(dto.getSearchTerm().toLowerCase()) || user.getSurname().toLowerCase().contains(dto.getSearchTerm().toLowerCase())){
                if(!userExists(user, results)){
                    results.add(user);
                }
            }
        }
        return results;
    }

    public boolean userExists(User user, List<User> users) {

        for(User client: users){
            if(client.getId().equals(user.getId())){
                return true;
            }
        }
        return false;
    }


    @Scheduled(cron = "0 1 0 0 * ?")
    public List<User> resetPenalty(){
        List<User> users = userRepository.findAllByType("Client");
        Date today = new Date();
        for(User user: users){
            if(today.getDate() == 1){
                user.setPenalty(0L);
                userRepository.save(user);
            }
        }
        return users;
    }





}
