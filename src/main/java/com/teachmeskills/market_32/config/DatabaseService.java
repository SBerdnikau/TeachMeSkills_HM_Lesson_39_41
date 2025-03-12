package com.teachmeskills.market_32.config;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@NoArgsConstructor
@Component
public class DatabaseService {
    final String URI = "jdbc:postgresql://localhost:5432/tmc_c32_hm_market";
    final String USER = "postgres";
    final String PASSWORD = "root";

    {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public Connection getConnection() {
        try {
            return DriverManager.getConnection(URI, USER, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
