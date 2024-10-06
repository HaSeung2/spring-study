package com.sparta.inflearn.service;

import com.sparta.inflearn.domain.Member;
import com.sparta.inflearn.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;


@SpringBootTest
@Transactional
class MemberServiceIntegrationTest {

    @Autowired  MemberService memberService;
    @Autowired  MemberRepository memberRepository;

    @Test
    void 회원가입() {
        //given
        Member member = new Member();
        member.setName("Johns");

        //when
        Long saveId = memberService.join(member);

        //then
        Member findMember = memberService.findOne(saveId).get();
        assertThat(findMember.getName()).isEqualTo(member.getName());
    }

    @Test
    void 중복_회원_체크() throws Exception {
        //given
        Member member1 = new Member();
        member1.setName("John23");

        Member member2 = new Member();
        member2.setName("John23");

        //when
        Long saveId = memberService.join(member1);
        IllegalAccessError e = assertThrows(IllegalAccessError.class, () -> memberService.join(member2));

        //then
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다");
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}