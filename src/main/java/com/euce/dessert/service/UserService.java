package com.euce.dessert.service;

import com.euce.dessert.dto.UserDto;
import com.euce.dessert.model.account.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserService {

    List<User> getUsers();

    User getUser(Long id);

    User getUserByUsername(String username);

    User saveUser(UserDto userDto);

    User updateUser(Long id, UserDto userDto);

    void delete(Long id);
}
