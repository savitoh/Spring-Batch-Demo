package com.br.savit0h.batchservicedemo.reader;

import com.br.savit0h.batchservicedemo.dto.User;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.database.JdbcCursorItemReader;

import javax.sql.DataSource;

public class CustomReader{

    private final DataSource dataSource;

    private static final String QUERY_FIND_BY_USERS =
            "SELECT " +
                    "name, " +
                    "job " +
            "FROM user";

    public CustomReader(DataSource dataSource){
        this.dataSource = dataSource;
    }

    public ItemReader<User> readerUsers() {
        var databaseReader = new JdbcCursorItemReader<User>();
        databaseReader.setDataSource(this.dataSource);
        databaseReader.setSql(QUERY_FIND_BY_USERS);
        databaseReader.setRowMapper(new UserMapper());
        return databaseReader;
    }

}
