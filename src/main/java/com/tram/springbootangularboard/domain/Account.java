package com.tram.springbootangularboard.domain;

import com.tram.springbootangularboard.dto.AccountDto;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.util.Arrays;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "account_user_id", unique = true, nullable = false)
    private String userId;

    @Column(name = "account_password", nullable = false)
    private String password;

    @Column(name = "account_username", nullable = false)
    private String username;

    @Column(name = "account_role")
    @ElementCollection
    @Enumerated(value = EnumType.STRING)
    private List<UserRole> userRole = Arrays.asList(UserRole.ROLE_USER);

    @Column(name = "account_social_id")
    private String socialId;

    @Column(name = "account_social_profile_link")
    private String socialProfileLink;

    public Account(String userId, String password, String username) {
        this.userId = userId;
        this.password = password;
        this.username = username;
    }

    public void encodePassword(PasswordEncoder passwordEncoder) {
        this.password = passwordEncoder.encode(this.password);
    }

    public static Account fromDto(AccountDto accountDto, PasswordEncoder passwordEncoder) {
        Account account = accountDto.toEntity();
        account.encodePassword(passwordEncoder);
        return account;
    }
}
