package com.github.Luiztins1.mixs.config;

import com.github.Luiztins1.mixs.security.LoginSocialSuccessHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final LoginSocialSuccessHandler loginSocialSuccessHandler;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .formLogin(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults())
                .authorizeHttpRequests(authorize -> {
                    authorize.requestMatchers("/login").permitAll();
                    authorize.anyRequest().permitAll();
                })
                .oauth2Login(oauth2 ->{
                    oauth2.successHandler(loginSocialSuccessHandler);
                })
                .build();
    }

    @Bean
    public GrantedAuthorityDefaults grantedAuthorityDefaults(){
        return new GrantedAuthorityDefaults("");
    }
}
