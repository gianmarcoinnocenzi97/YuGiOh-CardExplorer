package com.ygo.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO  {

    private Long id;
    private String name;
    private String surname;
    private String email;
    private Boolean isActive;
    private AuthDTO auth;
    private RoleDTO role;

}
