package com.euce.dessert.service.Impl;

import com.euce.dessert.dto.UserDto;
import com.euce.dessert.exception.RecordAlreadyExistsException;
import com.euce.dessert.model.account.Group;
import com.euce.dessert.model.account.Role;
import com.euce.dessert.model.account.User;
import com.euce.dessert.repository.GroupRepository;
import com.euce.dessert.repository.RoleRepository;
import com.euce.dessert.repository.UserRepository;
import com.euce.dessert.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private GroupRepository groupRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, GroupRepository groupRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.groupRepository = groupRepository;
    }
    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUser(Long id) {
        return userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("No Such User Exists")
        );
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User saveUser(UserDto userDto) {
        Role role = roleRepository.findByName(userDto.getRole());
        Group group =  groupRepository.findByName(userDto.getGroup());
        if (!role.equals(null) || !group.equals(null)) {
            throw new ResourceNotFoundException("No Such Role or Group Exists");
        }
        if (!userRepository.findByUsername(userDto.getUsername()).equals(null)) {
            throw new RecordAlreadyExistsException("There is already a user with this name");
        }

        User user = User.builder()
                .socialNumber(userDto.getSocialNumber())
                .firstname(userDto.getFirstname())
                .lastname(userDto.getLastname())
                .username(userDto.getUsername())
                .password(userDto.getPassword())
                .email(userDto.getEmail())
                .phone(userDto.getPhone())
                .gender(userDto.getGender())
                .birthday(userDto.getBirthday())
                .role(role)
                .group(group)
                .build();

        return userRepository.save(user);
    }

    @Override
    public User updateUser(Long id, UserDto userDto) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("No Such User Exists")
        );

        Role role = roleRepository.findByName(userDto.getRole());
        Group group = groupRepository.findByName(userDto.getGroup());

        user.setSocialNumber(userDto.getSocialNumber());
        user.setFirstname(userDto.getFirstname());
        user.setLastname(userDto.getLastname());
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        user.setEmail(userDto.getEmail());
        user.setPhone(userDto.getPhone());
        user.setGender(userDto.getGender());
        user.setBirthday(userDto.getBirthday());
        user.setRole(role);
        user.setGroup(group);

        return userRepository.save(user);
    }

    @Override
    public void delete(Long id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("No Such User Exists")
        );
        userRepository.delete(user);
    }
}
