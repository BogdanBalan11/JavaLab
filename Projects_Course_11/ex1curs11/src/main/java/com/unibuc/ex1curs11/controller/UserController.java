package com.unibuc.ex1curs11.controller;

import com.unibuc.ex1curs11.dto.UserDTO;
import com.unibuc.ex1curs11.dto.UserLoginDTO;
import com.unibuc.ex1curs11.dto.UserRegistrationDTO;
import com.unibuc.ex1curs11.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    @ApiOperation("Register a new user")
    public ResponseEntity<UserDTO> register(@Validated @RequestBody UserRegistrationDTO registrationDTO) {
        UserDTO userDTO = userService.registerUser(registrationDTO);
        return new ResponseEntity<>(userDTO, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    @ApiOperation("Login a user")
    public ResponseEntity<UserDTO> login(@RequestBody UserLoginDTO loginDTO) {
        UserDTO userDTO = userService.loginUser(loginDTO);
        if (userDTO != null) {
            return ResponseEntity.ok(userDTO);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}