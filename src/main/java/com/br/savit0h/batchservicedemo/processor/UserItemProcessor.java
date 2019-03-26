package com.br.savit0h.batchservicedemo.processor;

import com.br.savit0h.batchservicedemo.dto.User;

import org.springframework.batch.item.ItemProcessor;


public class UserItemProcessor implements ItemProcessor<User, User> {

    @Override
    public User process(User user) throws Exception {
        return user;
    }
}
