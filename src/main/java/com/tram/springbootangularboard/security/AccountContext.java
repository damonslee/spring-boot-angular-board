package com.tram.springbootangularboard.security;

import com.tram.springbootangularboard.domain.Account;
import com.tram.springbootangularboard.domain.UserRole;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class AccountContext extends User {
    private Account account;
    public AccountContext(Account account, String userId, String password, Collection<? extends GrantedAuthority> authorities) {
        super(userId, password, authorities);
        this.account = account;
    }

    public AccountContext(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    public static AccountContext fromAccount(Account account) {
        return new AccountContext(account, account.getUserId(), account.getPassword(), convertSimpleGrantedAuthorities(account.getUserRole()));
    }

    private static List<SimpleGrantedAuthority> convertSimpleGrantedAuthorities(List<UserRole> userRole) {
        return userRole.stream().map(r -> new SimpleGrantedAuthority(r.getRoleName())).collect(Collectors.toList());
    }

    public Account getAccount() {
        return account;
    }
}
