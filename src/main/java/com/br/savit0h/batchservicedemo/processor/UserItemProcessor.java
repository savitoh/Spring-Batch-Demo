package com.br.savit0h.batchservicedemo.processor;

import com.br.savit0h.batchservicedemo.dto.User;
import com.br.savit0h.batchservicedemo.exception.ApiException;
import com.br.savit0h.batchservicedemo.integracao.ApiIntegracao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserItemProcessor implements ItemProcessor<User, User> {

    private final ApiIntegracao apiIntegracao;

    private static final Logger log = LoggerFactory.getLogger(UserItemProcessor.class);


    @Autowired
    public UserItemProcessor(ApiIntegracao apiIntegracao) {
        this.apiIntegracao = apiIntegracao;
    }

    @Override
    public User process(User user) throws Exception, ApiException {
        final var userResponse = apiIntegracao.enviaParaBaixa(user);
        user.setCreatedAt(userResponse.getCreatedAt());
        return  user;
    }
}
