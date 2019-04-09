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

import javax.sql.DataSource;

@Component
public class CustomWriter implements ItemWriter<User>{

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private final DataSource dataSource;

    private static final String QUERY_UPDATE_USERS =
            "UPDATE " +
                    "user " +
            "SET " +
                    "send_at = :send_at " +
            "WHERE " +
                    "user_id = :user_id";

    public CustomWriter(DataSource dataSource) {
        this.dataSource = dataSource;
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    @Transactional
    public void write(List<? extends User> items) throws Exception {
        items.forEach(user -> {
            final SqlParameterSource namedParameters = buildMapSqlParameterSource(user);
            this.namedParameterJdbcTemplate.update(QUERY_UPDATE_USERS, namedParameters);
        });
    }

    private MapSqlParameterSource buildMapSqlParameterSource(User user) {
        return new MapSqlParameterSource()
                    .addValue("send_at", user.getCreatedAt())
                    .addValue("user_id", user.getId());
    }
}
