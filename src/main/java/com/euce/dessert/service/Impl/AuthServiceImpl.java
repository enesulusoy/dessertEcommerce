package com.euce.dessert.service.Impl;

import com.euce.dessert.dto.jwt.JwtRequest;
import com.euce.dessert.model.account.User;
import com.euce.dessert.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(rollbackFor = Exception.class)
public class AuthServiceImpl {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public AuthServiceImpl(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional(readOnly = true)
    public User login(JwtRequest jwtRequest) {
        if (!this.isUsernameExists(jwtRequest.getUsername())) {
            User user = userRepository.findByUsername(jwtRequest.getUsername());
            if(passwordEncoder.matches(user.getPassword(), jwtRequest.getPassword())) {
                return user;
            }
        }
        return null;
    }

    @Transactional
    public boolean isUsernameExists(String username) {
        User user = userRepository.findByUsername(username);
        return user.equals(null);
    }
}
