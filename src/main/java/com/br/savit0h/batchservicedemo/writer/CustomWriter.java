package com.br.savit0h.batchservicedemo.writer;

import com.br.savit0h.batchservicedemo.dto.User;
import com.br.savit0h.batchservicedemo.web.ExternalApiIntegration;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class CustomWriter implements ItemWriter<User>{

    private final ExternalApiIntegration externalApiIntegration;

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private static final String QUERY_UPDATE_USERS =
            "UPDATE " +
                    "user " +
            "SET " +
                    "created_at = :createdAt " +
            "WHERE " +
                    "user_id = :userId";

    @Autowired
    public CustomWriter(ExternalApiIntegration externalApiIntegration,
                        NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.externalApiIntegration = externalApiIntegration;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    @Transactional
    public void write(List<? extends User> items) throws Exception {
        items.forEach(i -> {
            final var response = this.externalApiIntegration.sendObjectForApi(i);
            if(response.getStatusCodeValue() == 201) {
                final var user = (User) response.getBody();
                final SqlParameterSource namedParameters = buildMapSqlParameterSource(user);
                this.namedParameterJdbcTemplate.update(QUERY_UPDATE_USERS, namedParameters);
            }
        });
    }

    private MapSqlParameterSource buildMapSqlParameterSource(User user) {
        return new MapSqlParameterSource()
                    .addValue("createdAt", user.getCreatedAt())
                    .addValue("userId", user.getId());
    }
}
