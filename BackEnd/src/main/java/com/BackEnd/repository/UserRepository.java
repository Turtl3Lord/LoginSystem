package com.BackEnd.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {

    private final JdbcTemplate jdbc;

    public UserRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public void saveUser(String login, String password) {
        String sql = "INSERT INTO users (login, password) VALUES (?, ?)";
        jdbc.update(sql, login, password);
    }
}
