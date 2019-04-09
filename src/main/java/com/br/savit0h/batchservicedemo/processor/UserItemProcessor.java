package com.br.savit0h.batchservicedemo.processor;

import com.br.savit0h.batchservicedemo.dto.User;

import com.br.savit0h.batchservicedemo.dto.UserResponse;
import com.br.savit0h.batchservicedemo.web.ExternalApiIntegration;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserItemProcessor implements ItemProcessor<User, User> {

    private final ExternalApiIntegration externalApiIntegration;

    @Autowired
    public UserItemProcessor(ExternalApiIntegration externalApiIntegration) {
        this.externalApiIntegration = externalApiIntegration;
    }

    @Override
    public User process(User user) throws Exception {
        final var response = externalApiIntegration.sendObjectForApi(user);
        if(response.getStatusCodeValue() == 201) {
            final var userResponse = (UserResponse) response.getBody();
            user.setCreatedAt(userResponse.getCreatedAt());
        }
        return user;
    }
}
