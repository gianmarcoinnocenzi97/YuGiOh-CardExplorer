package com.ygo.integration;

import com.ygo.integration.properties.YgoProDeckProperties;
import com.ygo.model.pojo.CardContainer;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class YgoProDeckClient {

    private final YgoProDeckProperties properties;
    private final WebClient webClient;

    public YgoProDeckClient(WebClient.Builder webClientBuilder, YgoProDeckProperties properties) {
        this.properties = properties;

        // Configurazione max buffer per JSON grandi (es. 50 MB)
        ExchangeStrategies strategies = ExchangeStrategies.builder()
                .codecs(configurer -> configurer.defaultCodecs().maxInMemorySize(50 * 1024 * 1024))
                .build();

        this.webClient = webClientBuilder
                .baseUrl(properties.getBaseUrl())
                .exchangeStrategies(strategies)
                .build();
    }

    public Mono<CardContainer> fetchAllCardsMono() {
        return webClient.get()
                .uri(properties.getCardInfo())
                .retrieve()
                .bodyToMono(CardContainer.class)
                .onErrorResume(e -> Mono.error(new RuntimeException("Errore nel parsing delle carte", e)));
    }
}