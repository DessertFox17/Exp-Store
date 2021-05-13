package com.experimentality.Store.domain.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class AuthenticationRequest {

    @Email
    @NotNull
    private String username;
    @NotNull
    private String password;
}
