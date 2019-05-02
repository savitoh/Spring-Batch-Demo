package com.br.savit0h.batchservicedemo.integracao;

import com.br.savit0h.batchservicedemo.dto.User;
import com.br.savit0h.batchservicedemo.exception.ApiException;
import com.br.savit0h.batchservicedemo.integracao.response.UserResponse;

public interface ApiIntegracao {

    UserResponse enviaParaBaixa(User user) throws ApiException;
}
