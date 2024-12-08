package ru.mirea.Pegov.pkmn.Rest;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class RestClientImpl implements RestClient {

    private final RestTemplate restTemplate;

    public RestClientImpl(RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
    }

    @Override
    public String get(String url) {
        return restTemplate.getForObject(url, String.class);
    }
}

