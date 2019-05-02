package com.br.savit0h.batchservicedemo.integracao.impl;

import com.br.savit0h.batchservicedemo.exception.ApiException;
import com.br.savit0h.batchservicedemo.integracao.ApiIntegracao;
import com.br.savit0h.batchservicedemo.integracao.headers.FactoryHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.br.savit0h.batchservicedemo.dto.User;
import com.br.savit0h.batchservicedemo.integracao.response.UserResponse;




@Component
public class ApiIntegracaoImpl implements ApiIntegracao {

    private final FactoryHeaders factoryHeaders;
    private final RestTemplate restTemplate;
    final private static String URL_API_EXTERNAL = "https://reqres.in/api/users";

    @Autowired
    public ApiIntegracaoImpl(FactoryHeaders factoryHeaders, RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
        this.factoryHeaders = factoryHeaders;
    }

    @Override
    public UserResponse enviaParaBaixa(User user) throws ApiException {
        final var headers = factoryHeaders.createHeaderWithoutAuth();
        final var request = new HttpEntity<>(user, headers);

        try {
            final var response = restTemplate.exchange(URL_API_EXTERNAL, HttpMethod.POST,
                    request, UserResponse.class);
            return response.getBody();
        } catch (HttpClientErrorException response) {
            throw  new ApiException(response.getLocalizedMessage(), response.getCause(),
                                    response.getResponseBodyAsString(), response.getRawStatusCode());
        }
    }
}
