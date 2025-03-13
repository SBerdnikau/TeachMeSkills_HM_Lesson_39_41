package com.tms.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Scope("prototype")
@AllArgsConstructor
@NoArgsConstructor
@Component
public class Security {
    private Long id;
    private String login;
    private String password;
    private Role role;
    private Timestamp created;
    private Timestamp updated;
    private Long userId;
}
