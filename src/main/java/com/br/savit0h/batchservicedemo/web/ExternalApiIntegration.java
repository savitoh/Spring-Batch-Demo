package com.br.savit0h.batchservicedemo.web;

import com.br.savit0h.batchservicedemo.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class ExternalApiIntegration {

    final private FactoryHeadersImpl factoryHeadersImpl;
    final private RestTemplate restTemplate;
    final private static String URL_API_EXTERNAL = "https://reqres.in/api/users";


    @Autowired
    public ExternalApiIntegration(FactoryHeadersImpl factoryHeadersImpl, RestTemplate restTemplate) {
        this.factoryHeadersImpl = factoryHeadersImpl;
        this.restTemplate = restTemplate;
    }

    public ResponseEntity<User> enviaUsuarioParaApiExterna(User user) {

        final var headers = factoryHeadersImpl.createHeaderWithoutAuth();
        final var request = new HttpEntity<>(user, headers);

        final var response = restTemplate.exchange(URL_API_EXTERNAL, HttpMethod.POST, request, User.class);

        return response;
    }
}
