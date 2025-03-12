package com.teachmeskills.market_32.repository;

import com.teachmeskills.market_32.config.DatabaseService;
import com.teachmeskills.market_32.model.User;
import com.teachmeskills.market_32.utils.SQLQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.Optional;


@Repository
public class UserRepository {

    private final DatabaseService databaseService;

    @Autowired
    public UserRepository(DatabaseService databaseService) {
        this.databaseService = databaseService;
    }

    public Optional<User> getUserById(Long id) {
        Connection connection = databaseService.getConnection();

        try {
            PreparedStatement getUserStatement = connection.prepareStatement(SQLQuery.GET_USER_BY_ID);
            getUserStatement.setLong(1, id);
            ResultSet resultSet = getUserStatement.executeQuery();
            return parseUser(resultSet);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return Optional.empty();
        }
    }

    public Boolean deleteUser(Long id){
        Connection connection = databaseService.getConnection();
        try {
            PreparedStatement getUserStatement = connection.prepareStatement(SQLQuery.DELETE_USER);
            getUserStatement.setLong(1, id);
            return getUserStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public Optional<Long> createUser(User user){
        Connection connection = databaseService.getConnection();
        Long userId = null;

        try {
            PreparedStatement createUserStatement = connection.prepareStatement(SQLQuery.CREATE_USER, Statement.RETURN_GENERATED_KEYS);
            createUserStatement.setString(1, user.getFirstName());
            createUserStatement.setString(2, user.getSecondName());
            createUserStatement.setInt(3, user.getAge());
            createUserStatement.setString(4, user.getEmail());
            createUserStatement.setString(5, user.getGender());
            createUserStatement.setString(6, user.getTelephoneNumber());
            createUserStatement.setTimestamp(7, new Timestamp(System.currentTimeMillis()));
            createUserStatement.setBoolean(8, false);
            createUserStatement.executeUpdate();

            ResultSet generatedKeys = createUserStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                userId = generatedKeys.getLong(1);
            }
            return Optional.of(userId);
        } catch (SQLException e){
            System.out.println(e.getMessage());
            return Optional.empty();
        }
    }

    public Boolean updateUser(User user) {
        Connection connection = databaseService.getConnection();

        try {
            PreparedStatement getUserStatement = connection.prepareStatement(SQLQuery.UPDATE_USER);
            getUserStatement.setString(1, user.getFirstName());
            getUserStatement.setString(2, user.getSecondName());
            getUserStatement.setInt(3, user.getAge());
            getUserStatement.setString(4, user.getEmail());
            getUserStatement.setString(5, user.getGender());
            getUserStatement.setString(6, user.getTelephoneNumber());
            getUserStatement.setLong(7, user.getId());
            return getUserStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    private Optional<User> parseUser(ResultSet resultSet) throws SQLException {
        if (resultSet.next()) {
            User user = new User();
            user.setId(resultSet.getLong("id"));
            user.setFirstName(resultSet.getString("first_name"));
            user.setSecondName(resultSet.getString("second_name"));
            user.setAge(resultSet.getInt("age"));
            user.setEmail(resultSet.getString("email"));
            user.setGender(resultSet.getString("gender"));
            user.setTelephoneNumber(resultSet.getString("telephone_number"));
            user.setCreatedAt(resultSet.getTimestamp("created_at"));
            user.setUpdatedAt(resultSet.getTimestamp("updated_at"));
            user.setIsDeleted(resultSet.getBoolean("is_deleted"));
            return Optional.of(user);
        }
        return Optional.empty();
    }
}
