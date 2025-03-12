package com.teachmeskills.market_32.utils;

public interface SQLQuery {
    String CREATE_USER = "INSERT INTO users(id, first_name, second_name, age, email, gender, telephone_number, created_at, updated_at, is_deleted) VALUES (DEFAULT, ?, ?, ?, ?, ?, ?, DEFAULT, ?, ?)";
    String CREATE_SECURITY = "INSERT INTO security(id, login, password, role, created_at, updated_at, user_id) VALUES (DEFAULT, ?, ?, ?, DEFAULT, ?, ?)";
    String GET_USER_BY_ID = "SELECT * FROM users WHERE id = ?";
    String UPDATE_USER = "UPDATE users SET firstname=?,second_name=?,age=?,email=?,gender=?,telephone_number=?,updated_at=DEFAULT WHERE id=?";
    String DELETE_USER = "UPDATE users SET is_deleted=true, updated_at=DEFAULT WHERE id=?";
}
