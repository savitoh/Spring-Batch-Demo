package com.br.savit0h.batchservicedemo.web;

import org.springframework.http.ResponseEntity;

import java.io.Serializable;

public interface ExternalApiIntegration<T extends  Serializable> {

    ResponseEntity<T> sendObjectForApi(T object);

}
