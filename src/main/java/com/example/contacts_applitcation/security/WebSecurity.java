package com.example.contacts_applitcation.security;

import com.example.contacts_applitcation.service.UserService;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurity {
    private final UserService userDetailsService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    public WebSecurity(UserService userServiceDetails, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userDetailsService = userServiceDetails;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Bean
    protected SecurityFilterChain configure(HttpSecurity http) throws Exception {
        // configure AuthenticationManagerBuilder
        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);

        authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
        AuthenticationManager authenticationManager = authenticationManagerBuilder.build();

        http.csrf(csrf -> csrf.disable()).authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.POST, SecurityConstants.SIGN_UP_URL)
                        .permitAll()
                        .anyRequest()
                        .authenticated())
                        .authenticationManager(authenticationManager)
                        .addFilter(new AuthenticationFilter(authenticationManager));

        return http.build();
    }


}
