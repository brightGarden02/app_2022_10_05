package com.ll.exam.app__2022_10_05.member.dto.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class LoginDto {

    @NotEmpty(message = "username 을(를) 입력해주세요.")
    private String username;

    @NotEmpty(message = "password 을(를) 입력해주세요.")
    private String password;

}
