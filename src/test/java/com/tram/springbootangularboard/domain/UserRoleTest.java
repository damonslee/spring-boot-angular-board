package com.tram.springbootangularboard.domain;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class UserRoleTest {
    private UserRole role;

    @Before
    public void setUp() throws Exception {
        role = UserRole.ROLE_ADMIN;
    }

    @Test
    public void isCorrectName() {
        assertThat(role.isCorrectName("ROLE_ADMIN")).isTrue();
    }

    @Test
    public void getRoleName() {
        assertThat(role.getRoleName()).isEqualToIgnoringCase("ROLE_ADMIN");
    }
}