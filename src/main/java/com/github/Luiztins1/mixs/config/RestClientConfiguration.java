package com.github.Luiztins1.mixs.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
@RequiredArgsConstructor
public class RestClientConfiguration {
    
    private final String url;

    @Bean
    public RestClient restClientProvider(){
        return null;
    }
}
