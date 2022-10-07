package com.ll.exam.app__2022_10_05.member.controller;

import com.ll.exam.app__2022_10_05.base.dto.RsData;
import com.ll.exam.app__2022_10_05.member.entity.Member;
import com.ll.exam.app__2022_10_05.member.service.MemberService;
import com.ll.exam.app__2022_10_05.util.Util;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/test")
    public String test(@AuthenticationPrincipal MemberContext memberContext) {
        return "안녕" + memberContext;
    }


    @PostMapping("/login")
    public ResponseEntity<RsData> login(@RequestBody LoginDto loginDto) {

        if(!loginDto.isNotValid()) {
            return Util.spring.responseEntityOf(RsData.of("F-1", "로그인 정보가 올바르지 않습니다."));
        }

        Member member = memberService.findByUsername(loginDto.getUsername()).orElse(null);

        if(member == null) {
            return Util.spring.responseEntityOf(RsData.of("F-2", "일치하는 회원이 존재하지 않습니다."));
        }

        if(passwordEncoder.matches(loginDto.getPassword(), member.getPassword()) == false) {
            return Util.spring.responseEntityOf(RsData.of("F-3", "비밀번호가 일치하지 않습니다."));
        }


        log.debug("Util.json.toStr(member.getAccessTokenClaims()) : " +
                Util.json.toStr(member.getAccessTokenClaims()));
        String accessToken = memberService.getAccessToken(member);


        return Util.spring.responseEntityOf(
                RsData.of(
                        "S-1",
                        "로그인 성공, Access Token을 발급합니다."),
                Util.spring.httpHeadersOf("Authentication", accessToken)
        );

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
