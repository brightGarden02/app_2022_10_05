package com.ll.exam.app__2022_10_05.member.controller;

import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import com.ll.exam.app__2022_10_05.member.entity.Member;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;


@Getter
@JsonIncludeProperties({"id", "createDate", "modifyDate", "username", "email", "authorities"})
public class MemberContext extends User {

    private final long id;
    private final LocalDateTime createDate;
    private final LocalDateTime modifyDate;
    private final String username;
    private final String email;
    private final Set<GrantedAuthority> authorities;


    public MemberContext(Member member) {

        super(member.getUsername(), "", member.getAuthorities());

        id = member.getId();
        createDate = member.getCreateDate();
        modifyDate = member.getModifyDate();
        username = member.getUsername();
        email = member.getEmail();
        authorities = member.getAuthorities().stream().collect(Collectors.toSet());
    }

}
