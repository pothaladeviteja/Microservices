package com.example.AuthServer.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class Password {

    @Bean
    public org.springframework.security.crypto.password.PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }

}
