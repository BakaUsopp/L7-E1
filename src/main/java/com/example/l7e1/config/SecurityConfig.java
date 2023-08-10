package com.example.l7e1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
       return http
                .httpBasic(Customizer.withDefaults())
                .authorizeHttpRequests((authz) -> authz.anyRequest().authenticated()
//                        .requestMatchers(HttpMethod.GET,"/demo/**").hasAuthority("read")
                        )
//                .csrf(Customizer.withDefaults())
                .build();
    }

    @Bean
    public UserDetailsService userDetailsService(){
        var user = new InMemoryUserDetailsManager();


        UserDetails u1 = User.withUsername("Usopp")
                .password(passwordEncoder().encode("1234"))
                .authorities("read").build();

        UserDetails u2 = User.withUsername("Zoro")
                .password(passwordEncoder().encode("1234"))
                .authorities("write").build();


        user.createUser(u1);
        user.createUser(u2);
        return user;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
