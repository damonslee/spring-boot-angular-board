package com.tram.springbootangularboard.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "account_user_id")
    private String userId;

    @Column(name = "account_username")
    private String username;

    @Column(name = "account_password")
    private String password;

    @Column(name = "account_role")
    @Enumerated(value = EnumType.STRING)
    private List<UserRole> userRole;

    @Column(name = "account_social_id")
    private String socialId;

    @Column(name = "account_social_profile_link")
    private String socialProfileLink;
}
