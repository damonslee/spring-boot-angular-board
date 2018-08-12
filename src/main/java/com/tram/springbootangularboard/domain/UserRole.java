package com.tram.springbootangularboard.domain;

import lombok.Getter;

import javax.persistence.GeneratedValue;
import java.util.Arrays;

//TODO GrantedAuthority 인터페이스를 구현하도록 수정하는게 좋을까??
@Getter
public enum UserRole {
    ROLE_ADMIN("ROLE_ADMIN"), ROLE_USER("ROLE_USER");
    private String roleName;
    UserRole(String roleName) {
        this.roleName = roleName;
    }

    public boolean isCorrectName(String roleName) {
        return this.roleName.equalsIgnoreCase(roleName);
    }
}
