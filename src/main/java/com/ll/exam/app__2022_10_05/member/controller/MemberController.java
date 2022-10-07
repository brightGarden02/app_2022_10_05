package com.ll.exam.app__2022_10_05.member.controller;

import lombok.Data;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/member")
public class MemberController {

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDto loginDto) {

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authentication", "JWT키");
        String body = "username : %s, password : %s".formatted(loginDto.getUsername(), loginDto.getPassword());

        return new ResponseEntity<>(body, headers, HttpStatus.OK);
    }

    @Data
    public static class LoginDto {
        private String username;
        private String password;
    }
}
