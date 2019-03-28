package com.br.savit0h.batchservicedemo.writer;

import com.br.savit0h.batchservicedemo.dto.User;
import com.br.savit0h.batchservicedemo.web.ExternalApiIntegration;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.List;

@Component
public class CustomWriter implements ItemWriter<User>{

    private final DataSource dataSource;

    private final ExternalApiIntegration externalApiIntegration;

    private static final String QUERY_UPDATE_USERS =
            "UPDATE " +
                    "users " +
            "SET " +
                    "createdAt = ? " +
            "WHERE " +
                    "user_id = ?";

    @Autowired
    public CustomWriter(DataSource dataSource, ExternalApiIntegration externalApiIntegration) {
        this.dataSource = dataSource;
        this.externalApiIntegration = externalApiIntegration;
    }

    @Override
    public void write(List<? extends User> items) throws Exception {

        items.forEach(i -> {
            var response = this.externalApiIntegration.sendObjectForApi(i);
        });

    }
}
