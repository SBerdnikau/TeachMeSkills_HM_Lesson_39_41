package com.tms.service;

import com.tms.model.User;
import com.tms.model.dto.UserRequestDto;
import com.tms.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> getUserById(Long id){
        return userRepository.getUserById(id);
    }

    public Optional<User> updateUser(UserRequestDto userRequestDto){
        Boolean result = userRepository.updateUser(userRequestDto);
        if(result){
            return getUserById(userRequestDto.getId());
        }
        return Optional.empty();
    }

    public Optional<User> deleteUser(Long id){
        Boolean result = userRepository.deleteUser(id);
        if(result){
            return getUserById(id);
        }
        return Optional.empty();
    }
    
    public Optional<User> createUser(UserRequestDto userRequestDto){
        Optional<Long> userId = userRepository.createUser(userRequestDto);
        if(userId.isPresent()){
            return getUserById(userId.get());
        }
        return Optional.empty();
    }

    public List<User> getAllUsers(){
        List<User> users = userRepository.getAllUsers();
        if(users.isEmpty()){
            return null;
        }
        return users;
    }
}
