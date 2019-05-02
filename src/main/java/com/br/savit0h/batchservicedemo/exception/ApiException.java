package com.br.savit0h.batchservicedemo.exception;

import lombok.Getter;

@Getter
public class ApiException extends Exception {

    final private String body;

    final private int code;

    public ApiException(String message,  Throwable cause, String body, int code) {
        super(message, cause);
        this.body = body;
        this.code = code;
    }
}
