package com.teachmeskills.market_32.servise;

import com.teachmeskills.market_32.model.dto.RegistrationRequestDto;
import com.teachmeskills.market_32.repository.SecurityRepository;
import com.teachmeskills.market_32.utils.SQLQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.*;

@Service
public class SecurityService {

    public final SecurityRepository securityRepository;

    @Autowired
    public SecurityService(SecurityRepository securityRepository) {
        this.securityRepository = securityRepository;
    }

   public Boolean registration(RegistrationRequestDto registrationRequestDto)  {
       try {
           return securityRepository.registration(registrationRequestDto);
       } catch (SQLException e) {
           System.out.println(e.getMessage());
           return false;
       }
   }


}
