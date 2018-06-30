package com.tram.springbootangularboard.domain;

import lombok.Getter;

import javax.persistence.GeneratedValue;

@Getter
public enum UserRole {
    ADMIN("ROLE_ADMIN"), USER("ROLE_USER");
    private String roleName;
    UserRole(String roleName) {
        this.roleName = roleName;
    }
}
