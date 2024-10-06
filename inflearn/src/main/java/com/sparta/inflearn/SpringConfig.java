package com.sparta.inflearn;

import com.sparta.inflearn.repository.JdbcMemberRepository;
import com.sparta.inflearn.repository.JpaMemberRepository;
import com.sparta.inflearn.repository.MemberRepository;
import com.sparta.inflearn.repository.MemoryMemberRepository;
import com.sparta.inflearn.service.MemberService;
import jakarta.persistence.EntityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    private final MemberRepository memberRepository;

    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository);
    }

//    @Bean
//    public MemberRepository memberRepository() {
////        return new MemoryMemberRepository();
////        return new JdbcMemberRepository(dataSource);
//        //return new JdbcMemberRepository(dataSource);
//       //return new JpaMemberRepository(em);
//    }
}
