package com.unibuc.ex1curs11.service;


import com.unibuc.ex1curs11.dto.UserDTO;
import com.unibuc.ex1curs11.dto.UserLoginDTO;
import com.unibuc.ex1curs11.dto.UserRegistrationDTO;
import com.unibuc.ex1curs11.exception.UserWithSameEmailExists;
import com.unibuc.ex1curs11.model.User;
import com.unibuc.ex1curs11.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserDTO registerUser(UserRegistrationDTO registrationDTO) {

        boolean userWithSameEmailExists = userRepository.existsByEmail(registrationDTO.getEmail());

        if (userWithSameEmailExists) {
            throw new UserWithSameEmailExists();
        }

        User user = new User();
        user.setEmail(registrationDTO.getEmail());
        user.setPassword(registrationDTO.getPassword());
        user.setName(registrationDTO.getName());
        user.setBalance(BigDecimal.ZERO);

        userRepository.save(user);
        return convertToDTO(user);
    }

    public UserDTO loginUser(UserLoginDTO loginDTO) {
        Optional<User> userOpt = userRepository.findByEmail(loginDTO.getEmail());
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            if (loginDTO.getPassword().equals(user.getPassword())) {
                return convertToDTO(user);
            }
        }
        return null;
    }

    private UserDTO convertToDTO(User user) {
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setEmail(user.getEmail());
        dto.setName(user.getName());
        dto.setBalance(user.getBalance());
        return dto;
    }
}

