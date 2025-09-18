package com.ygo.integration.enums;

import lombok.Getter;

@Getter
public enum ERole {

    ROLE_USER("USER");

    private final String code;

    ERole(String code) {
        this.code = code;
    }
}
