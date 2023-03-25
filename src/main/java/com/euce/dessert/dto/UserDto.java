package com.euce.dessert.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Collection;

@Data
@Builder
public class UserDto {
    @NotNull
    private String socialNumber;
    @NotNull
    private String firstname;
    @NotNull
    private String lastname;
    @NotNull
    private String username;
    @NotNull
    private String password;
    @NotNull
    private String email;
    @NotNull
    private String phone;
    @NotNull
    private String gender;
    @NotNull
    private String birthday;
    @NotNull
    private String role;
    @NotNull
    private String group;
}
