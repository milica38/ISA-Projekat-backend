package com.ISA.controller;

import com.ISA.config.CustomUserDetailsService;
import com.ISA.domain.dto.*;
import com.ISA.domain.model.HomeProfile;
import com.ISA.domain.model.User;
import com.ISA.security.TokenUtil;
import com.ISA.service.definition.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("api/users")
public class UserController {

    @Autowired
    private UserService userService;

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

        if (user == null || !passwordEncoder.matches(loginDTO.getPassword(), user.getPassword())) {
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

    @GetMapping(path = "/allUsers")
    public ResponseEntity<?> getAllUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

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
   public ResponseEntity<?> delete(@PathVariable Long id) {
        userService.delete(id);

       return new ResponseEntity<>(HttpStatus.OK);
   }
}
