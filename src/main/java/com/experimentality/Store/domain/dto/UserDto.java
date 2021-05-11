package com.experimentality.Store.domain.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@NoArgsConstructor
@Data
public class UserDto {

    private int usId;

    @NotNull
    private String name;

    @NotNull
    private LocalDateTime regDate;

    @NotNull
    private String address;

    @Min(value = 1)
    @NotNull
    private long phoneNumber;

    @NotNull
    @Email
    private String email;

    @NotNull
    private String password;

}
