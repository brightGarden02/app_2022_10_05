package com.ll.exam.app__2022_10_05.member.service;

import com.ll.exam.app__2022_10_05.member.entity.Member;
import com.ll.exam.app__2022_10_05.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final JwtProvider jwtProvider;

    public Member join(String username, String password, String email) {

        Member member = Member.builder()
                .username(username)
                .password(password)
                .email(email)
                .build();

        memberRepository.save(member);

        return member;
    }

    public Optional<Member> findByUsername(String username) {
        return memberRepository.findByUsername(username);
    }

    public String getAccessToken(Member member) {
        return jwtProvider.generateAccessToken(member.getAccessTokenClaims(), 60 * 60 * 24 * 90);
    }
}
