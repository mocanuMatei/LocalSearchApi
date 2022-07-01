package com.localsearch.localsearchapi.accessor;

import com.localsearch.localsearchapi.accessor.places.PlacesConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.web.reactive.function.client.WebClientAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ClientHttpConnector;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

@Configuration
@ConditionalOnClass(WebClientAutoConfiguration.class)
public class WebClientConfig {

    @Bean
    public WebClient placesWebClient(WebClient.Builder webClientBuilder, PlacesConfiguration placesConfiguration) {
        return webClientBuilder
                .clientConnector(createClientHttpConnector())
                .baseUrl(placesConfiguration.host())
                .build();
    }

    private ClientHttpConnector createClientHttpConnector() {
        return new ReactorClientHttpConnector(HttpClient.create());
    }
}
