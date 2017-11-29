package com.elearningbackend.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class UserDto {
    private String username;
    private String passwordDigest;
    private String activationDigest;
    private Boolean activated;
    private Date activatedAt;
    private String rememberDigest;
    private String resetDigest;
    private Date resetSentAt;
    private Date createdAt;
    private Date updatedAt;
    private String displayName;
    private String email;
    private String phone;
    private String address;
    private String avatar;
    private String role;

    public UserDto(String username) {
        this.username = username;
    }
}
