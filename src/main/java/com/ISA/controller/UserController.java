package com.ISA.controller;

import com.ISA.config.CustomUserDetailsService;
import com.ISA.domain.dto.LoginDTO;
import com.ISA.domain.dto.LoginResponseDTO;
import com.ISA.domain.dto.RegistrationDTO;
import com.ISA.domain.model.User;
import com.ISA.security.TokenUtil;
import com.ISA.service.definition.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(path = "/owner/register", method = RequestMethod.POST)
    public ResponseEntity<?> registerOwner(@RequestBody RegistrationDTO registrationDTO) {

        User user = userService.ownerRegistration(registrationDTO);

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
}
