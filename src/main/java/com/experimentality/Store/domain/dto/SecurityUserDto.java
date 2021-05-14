package com.experimentality.Store.domain.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SecurityUserDto {
    private int usId;
    private int roId;
    private String name;
    private String address;
    private long phoneNumber;
    private String email;
    private String password;
}
