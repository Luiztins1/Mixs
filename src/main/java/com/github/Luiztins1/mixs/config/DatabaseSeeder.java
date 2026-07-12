package com.github.Luiztins1.mixs.config;

import com.github.Luiztins1.mixs.model.entity.UserAuth;
import com.github.Luiztins1.mixs.repository.UserAuthRepository;
import com.github.Luiztins1.mixs.service.UserAuthService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@Configuration
public class DatabaseSeeder {

    @Bean
    public CommandLineRunner commandLineRunner(UserAuthRepository userAuthRepository, PasswordEncoder passwordEncoder){
        return args ->{
            if(userAuthRepository.count() == 0){
                var userAuth = new UserAuth();
                userAuth.setLogin("manager");
                userAuth.setPassword(passwordEncoder.encode("manager123"));
                userAuth.setRoles(List.of("MANAGER"));

                userAuthRepository.save(userAuth);
            }
        };
    }
}
