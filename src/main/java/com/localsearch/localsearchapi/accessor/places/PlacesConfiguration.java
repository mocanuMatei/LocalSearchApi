package com.localsearch.localsearchapi.accessor.places;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.validation.annotation.Validated;

@ConstructorBinding
@ConfigurationProperties(prefix = "places")
@Validated
public record PlacesConfiguration(String host, String codingSessionPath) {
}
