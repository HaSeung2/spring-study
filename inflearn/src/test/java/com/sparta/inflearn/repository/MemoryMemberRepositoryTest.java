package com.sparta.inflearn.repository;

import com.sparta.inflearn.domain.Member;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    public void afterEach(){
        repository.clearStore();
    }

    @Test
    void save() {
        Member member = new Member();
        member.setName("test");

        repository.save(member);

        Member result = repository.findById(member.getId()).get();
        System.out.println(result == member);
    }

    @Test
    void findByName() {
        Member member1 = new Member();
        member1.setName("test");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("test");
        repository.save(member2);

        Member result = repository.findByName("test").get();


    }

    @Test
    void findAll() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring1");
        repository.save(member2);

        List<Member> members = repository.findAll();
        System.out.println(members);
    }
}