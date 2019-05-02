package com.br.savit0h.batchservicedemo.integracao.headers;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import java.nio.charset.Charset;
import java.util.Base64;

@Component
public class FactoryHeadersImpl implements  FactoryHeaders {

    @Override
    public HttpHeaders createHeaderWithAuthBasic(String userName, String password) {
        return new HttpHeaders() {{
            String auth = userName + ":" + password;
            byte[] encodedAuth = Base64.getEncoder().encode(auth.getBytes(Charset.forName("US-ASCII")) );
            String authHeader = "Basic " + new String( encodedAuth );
            set("Authorization", authHeader  );

            setContentType(MediaType.APPLICATION_JSON);
            add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) " +
                                                      "AppleWebKit/537.36 (KHTML, like Gecko) " +
                                                      "Chrome/54.0.2840.99 Safari/537.36");
        }};
    }

    @Override
    public HttpHeaders createHeaderWithoutAuth() {
        return new HttpHeaders (){{
            setContentType(MediaType.APPLICATION_JSON);
            add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) " +
                                                      "AppleWebKit/537.36 (KHTML, like Gecko) " +
                                                      "Chrome/54.0.2840.99 Safari/537.36");
        }};
    }
}
