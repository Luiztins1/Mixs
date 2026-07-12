package com.github.Luiztins1.mixs.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class RestClientConfiguration {

    @Value("${app.discogs.base-url}")
    private String baseUrl;

    @Value("${app.discogs.token}")
    private String token;

    @Bean
    public RestClient restClientProvider(){
        return RestClient
                .builder()
                .baseUrl(baseUrl)
                .defaultHeader(token)
                .defaultHeader("User-Agent", "MixsApp/1.0.0-LuizGabriel")
                .build();
    }
}
