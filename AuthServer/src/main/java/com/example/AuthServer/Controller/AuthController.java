package com.example.AuthServer.Controller;

import com.example.AuthServer.Entity.User;
import com.example.AuthServer.Repository.UserRepository;
import com.example.AuthServer.RequestDtO.LoginRequest;
import com.example.AuthServer.Service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user)
    {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);

        return new ResponseEntity<>("User registered", HttpStatus.CREATED);

    }


    @PostMapping("/login")
    public String login(@RequestBody LoginRequest loginRequest)
    {
        Authentication authentication = new UsernamePasswordAuthenticationToken(loginRequest.name(),loginRequest.password());

       Authentication authentication1= authenticationManager.authenticate(authentication);
       if(authentication1.isAuthenticated())
       {
           return jwtService.generateToken(loginRequest.name(),authentication1.getAuthorities().iterator().next().getAuthority());


       }

        return "Invalid Credentials";

    }


}
