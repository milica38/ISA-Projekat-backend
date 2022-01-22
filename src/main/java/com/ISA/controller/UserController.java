package com.ISA.controller;

import com.ISA.config.CustomUserDetailsService;
import com.ISA.domain.dto.*;
import com.ISA.domain.dto.converters.HomeProfileConverters;
import com.ISA.domain.model.HomeProfile;
import com.ISA.domain.model.User;
import com.ISA.security.TokenUtil;
import com.ISA.service.definition.UserService;
import com.ISA.service.implementation.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserServiceImpl userServiceImpl;

    @Autowired
    private TokenUtil tokenUtils;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CustomUserDetailsService customUserService;

    @RequestMapping(path = "/client/register", method = RequestMethod.POST)
    public ResponseEntity<?> registerClient(@RequestBody RegistrationDTO registrationDTO) {

        User user = userService.clientRegistration(registrationDTO);

        if(user == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(path = "/house-owner/register", method = RequestMethod.POST)
    public ResponseEntity<?> registerHouseOwner(@RequestBody RegistrationDTO registrationDTO) {

        User user = userService.houseOwnerRegistration(registrationDTO);

        if(user == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    @RequestMapping(path = "/boat-owner/register", method = RequestMethod.POST)
    public ResponseEntity<?> registerBoatOwner(@RequestBody RegistrationDTO registrationDTO) {

        User user = userService.boatOwnerRegistration(registrationDTO);

        if(user == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(path = "/fishing-instructor/register", method = RequestMethod.POST)
    public ResponseEntity<?> registerFishingInstructor(@RequestBody RegistrationDTO registrationDTO) {

        User user = userService.fishingInstructorRegistration(registrationDTO);

        if(user == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(path = "/admin/register", method = RequestMethod.POST)
    public ResponseEntity<?> registerAdmin(@RequestBody RegistrationDTO registrationDTO) {

        User user = userService.adminRegistration(registrationDTO);

        if(user == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping(path = "/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO) {

        User user = customUserService.findUserByEmail(loginDTO.getEmail());
        userServiceImpl.resetPenalty();

        if (user == null || !passwordEncoder.matches(loginDTO.getPassword(), user.getPassword()) || !loginDTO.getEmail().equals(user.getEmail())) {
            return  ResponseEntity.ok(HttpStatus.UNAUTHORIZED);
        }

        String token = tokenUtils.generateToken(user.getEmail(), user.getRole().toString());
        LoginResponseDTO responseDTO = new LoginResponseDTO();
        responseDTO.setToken(token);
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping(path = "/current")
    public ResponseEntity<?> getCurrentUser() {
        return new ResponseEntity<>(userService.getCurrentUser(), HttpStatus.OK);
    }



    @GetMapping(path = "/userStatus")
    public ResponseEntity<?> getNullStatusUsers() {
        return new ResponseEntity<>(userService.getNullStatusUsers(), HttpStatus.OK);
    }

    @GetMapping(path = "/userStatusActive")
    public ResponseEntity<?> getActiveStatusUsers() {
        return new ResponseEntity<>(userService.getActiveStatusUsers(), HttpStatus.OK);
    }

    @GetMapping(path = "/allUsers")
    public ResponseEntity<?> getAllUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping(path = "/usersByType")
    public ResponseEntity<?> findAllByType() {
        return new ResponseEntity<>(userService.findAllByType(), HttpStatus.OK);

    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getUserInfo(@PathVariable Long id){
        User user = userService.findById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);

    }

    @PreAuthorize("hasAuthority('House owner') or hasAuthority('Client') or hasAuthority('Boat owner')")
    @PutMapping()
    public ResponseEntity<?> edit(@RequestBody UserDTO dto) {
        User user = userService.edit(dto);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(path = "/activate/{token}")
    public RedirectView activateClient(@PathVariable String token){
        userService.findUserByToken(token);
        return new RedirectView("http://localhost:4200");
    }

    @PostMapping(path = "/deleteThisUser/{id}")
    public ResponseEntity<?> deleteThisUser(@PathVariable Long id){
        userService.deleteThisUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(path = "/approve/{id}")
    public ResponseEntity<?> approveUser(@PathVariable Long id){
        userService.registrationApproved(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(path = "/decline/{id}")
    public ResponseEntity<?> declineUser(@PathVariable Long id){
        userService.registrationDeclined(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

   @DeleteMapping(path = "/deleteUser/{id}")
   public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        userService.delete(id);

       return new ResponseEntity<>(HttpStatus.OK);
   }



    @PreAuthorize("hasAuthority('House owner') or hasAuthority('Client') or hasAuthority('Boat owner')")

    @PostMapping(path = "/password")
    public ResponseEntity<?> changePassword(@RequestBody ChangePasswordDTO changePasswordDTO) {
        User user = userService.changePassword(changePasswordDTO);

        if(user == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('House owner') or hasAuthority('Client') or hasAuthority('Boat owner')")
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        boolean delete = userService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('House owner') or hasAuthority('Boat owner')")
    @PostMapping(path = "/filterUsers")
    public ResponseEntity<?> filterUsers(@RequestBody UserDTO dto){
        List<User> users = userService.filterUsers(dto);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

}
