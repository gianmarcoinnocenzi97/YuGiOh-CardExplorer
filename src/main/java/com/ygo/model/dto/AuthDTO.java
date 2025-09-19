package com.ygo.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AuthDTO {


    private String id;
    private String password;
    private String codePassword;
    private LocalDateTime registrationDate;
    private UserDTO user;
    private String jwt;

}
