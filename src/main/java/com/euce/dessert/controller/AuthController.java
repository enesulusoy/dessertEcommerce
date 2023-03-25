package com.euce.dessert.controller;

import com.euce.dessert.dto.jwt.JwtRequest;
import com.euce.dessert.dto.jwt.JwtResponse;
import com.euce.dessert.model.account.User;
import com.euce.dessert.security.jwt.JwtManager;
import com.euce.dessert.security.jwt.JwtProvider;
import com.euce.dessert.security.service.UserDetailsServiceImpl;
import com.euce.dessert.service.Impl.AuthServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/login")
public class AuthController {
    private final JwtManager jwtManager;
    private final JwtProvider jwtProvider;
    private final UserDetailsServiceImpl userDetailsService;
    private final AuthenticationManager authenticationManager;
    private final AuthServiceImpl authService;

    @Autowired
    public AuthController(JwtManager jwtManager,
                          JwtProvider jwtProvider,
                          UserDetailsServiceImpl userDetailsService,
                          AuthenticationManager authenticationManager,
                          AuthServiceImpl authService) {
        this.jwtManager = jwtManager;
        this.jwtProvider = jwtProvider;
        this.userDetailsService = userDetailsService;
        this.authenticationManager = authenticationManager;
        this.authService = authService;
    }

    @PostMapping
    public ResponseEntity<?> login(@Valid @RequestBody JwtRequest jwtRequest) throws Exception {
        try {
            User user = authService.login(jwtRequest);
            if (!user.equals(null)) {
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                        jwtRequest.getUsername(),
                        jwtRequest.getPassword()
                );
                authenticationManager.authenticate(authenticationToken);

                final UserDetails userDetails = userDetailsService.loadUserByUsername(jwtRequest.getUsername());
                final String jwtToken = jwtManager.generateToken(userDetails);

                return ResponseEntity.ok(new JwtResponse(jwtProvider.getPrefix() + jwtToken));
            }
        } catch (BadCredentialsException e) {
            throw new Exception("Credential information entered incorrectly or incompletely");
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }
}
