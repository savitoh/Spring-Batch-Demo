package com.br.savit0h.batchservicedemo.reader;

import com.br.savit0h.batchservicedemo.dto.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class UserMapper implements RowMapper<User> {
    
    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        var user = new User();
        user.setName(rs.getString("name"));
        user.setJob(rs.getString("job"));
        return user;
    }
}
