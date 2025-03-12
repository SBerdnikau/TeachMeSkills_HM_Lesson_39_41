package com.teachmeskills.market_32.model;

import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
public class Security{
    private Long id;
    private String login;
    private String password;
    private Role role;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private Long userId;
}
