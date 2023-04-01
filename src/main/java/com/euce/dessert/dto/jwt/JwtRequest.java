package com.euce.dessert.dto.jwt;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class JwtRequest implements Serializable {
    private static final long serialVersionUID = 5926468583005150707L;
    @NotNull
    private String username;
    @NotNull
    private String password;
}
