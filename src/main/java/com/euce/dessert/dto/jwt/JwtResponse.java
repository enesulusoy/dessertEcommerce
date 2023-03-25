package com.euce.dessert.dto.jwt;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
public class JwtResponse implements Serializable {
    private static final long serialVersionUID = -8091879091924046844L;
    private final String jwtToken;
    public JwtResponse(String jwtToken) {
        this.jwtToken = jwtToken;
    }
}
