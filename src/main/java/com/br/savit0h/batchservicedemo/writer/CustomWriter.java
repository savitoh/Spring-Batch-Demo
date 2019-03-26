package com.br.savit0h.batchservicedemo.writer;

import com.br.savit0h.batchservicedemo.dto.User;
import org.springframework.batch.item.ItemWriter;


import javax.sql.DataSource;
import java.util.List;

public class CustomWriter implements ItemWriter<User>{

    private final DataSource dataSource;


    private static final String QUERY_UPDATE_USERS =
            "UPDATE " +
                    "users " +
            "SET " +
                    "createdAt = ? " +
            "WHERE " +
                    "user_id = ?";

    public CustomWriter(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    @Override
    public void write(List<? extends User> items) throws Exception {

    }
}
