package com.ll.exam.app__2022_10_05.member.repository;

import com.ll.exam.app__2022_10_05.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByUsername(String username);

}
