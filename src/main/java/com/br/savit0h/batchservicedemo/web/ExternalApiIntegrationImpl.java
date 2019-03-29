package com.br.savit0h.batchservicedemo.web;

import com.br.savit0h.batchservicedemo.dto.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ExternalApiIntegrationImpl implements  ExternalApiIntegration<User>{

    private final FactoryHeaders factoryHeaders;
    private final RestTemplate restTemplate;
    final private static String URL_API_EXTERNAL = "https://reqres.in/api/users";

    @Autowired
    public ExternalApiIntegrationImpl(FactoryHeaders factoryHeaders, RestTemplateBuilder restTemplateBuilder) {
        this.factoryHeaders = factoryHeaders;
        this.restTemplate = restTemplateBuilder.build();
    }


    @Override
    public ResponseEntity<User> sendObjectForApi(User object) {
        final var headers = factoryHeaders.createHeaderWithoutAuth();
        final var request = new HttpEntity<>(object, headers);

        final var response = restTemplate.exchange(URL_API_EXTERNAL, HttpMethod.POST, request, User.class);

        return response;
    }
}
