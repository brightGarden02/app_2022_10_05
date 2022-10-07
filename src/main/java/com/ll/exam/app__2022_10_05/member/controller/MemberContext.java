package com.ll.exam.app__2022_10_05.member.controller;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class MemberContext extends User {

    public MemberContext(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    public MemberContext(String username, String password, boolean enabled, boolean accountNonExpired,
                         boolean credentialNonExpired, boolean accountNonlocked,
                         Collection<? extends GrantedAuthority> authorities) {

        super(username, password, enabled, accountNonExpired, credentialNonExpired, accountNonlocked, authorities);
    }

}
