package com.ll.exam.app__2022_10_05.member.controller;

import com.ll.exam.app__2022_10_05.member.entity.Member;
import com.ll.exam.app__2022_10_05.member.service.MemberService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDto loginDto) {

        if(!loginDto.isNotValid()) {
            return new ResponseEntity<>(null, null, HttpStatus.BAD_REQUEST);
        }

        Member member = memberService.findByUsername(loginDto.getUsername()).orElse(null);

        if(member == null) {
            return new ResponseEntity<>(null, null, HttpStatus.BAD_REQUEST);
        }

        if(passwordEncoder.matches(loginDto.getPassword(), member.getPassword()) == false) {
            return new ResponseEntity<>(null, null, HttpStatus.BAD_REQUEST);
        }


        HttpHeaders headers = new HttpHeaders();
        headers.set("Authentication", "JWT키");
        String body = "username : %s, password : %s".formatted(loginDto.getUsername(), loginDto.getPassword());

        return new ResponseEntity<>(body, headers, HttpStatus.OK);
    }

    @Data
    public static class LoginDto {
        private String username;
        private String password;

        public boolean isNotValid() {
            return username == null || password == null || username.trim().length() == 0 || password.trim().length() == 0;
        }
    }
}
