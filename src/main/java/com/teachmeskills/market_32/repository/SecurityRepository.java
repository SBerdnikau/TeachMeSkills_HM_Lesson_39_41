package com.teachmeskills.market_32.repository;

import com.teachmeskills.market_32.config.DatabaseService;
import com.teachmeskills.market_32.model.Role;
import com.teachmeskills.market_32.model.dto.RegistrationRequestDto;
import com.teachmeskills.market_32.utils.SQLQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;

@Repository
public class SecurityRepository {

    private final DatabaseService databaseService;

    @Autowired
    public SecurityRepository(DatabaseService databaseService) {
        this.databaseService = databaseService;
    }

    public Boolean registration(RegistrationRequestDto registrationRequestDto) throws SQLException {
        Connection connection = databaseService.getConnection();

        try {
            connection.setAutoCommit(false);
            PreparedStatement createUserStatement = connection.prepareStatement(SQLQuery.CREATE_USER, Statement.RETURN_GENERATED_KEYS);
            createUserStatement.setString(1, registrationRequestDto.getFirstName());
            createUserStatement.setString(2, registrationRequestDto.getSecondName());
            createUserStatement.setInt(3, registrationRequestDto.getAge());
            createUserStatement.setString(4, registrationRequestDto.getEmail());
            createUserStatement.setString(5, registrationRequestDto.getGender());
            createUserStatement.setString(6, registrationRequestDto.getTelephoneNumber());
            createUserStatement.setTimestamp(7, new Timestamp(System.currentTimeMillis()));
            createUserStatement.setBoolean(8, false);
            createUserStatement.executeUpdate();

            ResultSet generatedKeys = createUserStatement.getGeneratedKeys();
            long userId = -1;
            if (generatedKeys.next()) {
                userId = generatedKeys.getLong(1);
            }

            PreparedStatement createSecurityStatement = connection.prepareStatement(SQLQuery.CREATE_SECURITY);
            createSecurityStatement.setString(1, registrationRequestDto.getLogin());
            createSecurityStatement.setString(2, registrationRequestDto.getPassword());
            createSecurityStatement.setString(3, Role.USER.toString());
            createSecurityStatement.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
            createSecurityStatement.setLong(5, userId);
            createSecurityStatement.executeUpdate();

            connection.commit();
            return true;
        }catch (Exception e) {
            System.out.println(e.getMessage());
            connection.rollback();
        }

        return false;
    }
}
