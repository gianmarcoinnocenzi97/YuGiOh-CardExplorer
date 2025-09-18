package com.ygo.integration.properties;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@Validated
@Getter
@RequiredArgsConstructor
@ConfigurationProperties(prefix = "app.config.ygoprodeck")
public class YgoProDeckProperties {

    @NotBlank
    private final String baseUrl;
    @NotBlank
    private final String cardInfo;
}