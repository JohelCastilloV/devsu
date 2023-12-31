package com.devsu.account.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class AccountConfiguration {

    @Bean
    WebClient webClient(@Value("${client.url}") String url){
        return WebClient.builder()
                .baseUrl(url)
                .build();
    }
}
