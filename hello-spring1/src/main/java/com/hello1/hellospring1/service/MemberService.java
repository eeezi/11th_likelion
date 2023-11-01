package com.hello1.hellospring1.service;

import com.hello1.hellospring1.domain.Member;
import com.hello1.hellospring1.repository.MemberRepository;

import java.util.List;
import java.util.Optional;

// cmd + shift + t => test 생성
public class MemberService {
    //private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Long join(Member member) {
        // 중복회원 검증
        // cmd + option + v => 반환값 생성
        // (mac)ctrl + t + extract method => 메소드로 뽑기
        // (win)ctrl + alt + shift + t
        validateDuplicateMember(member);

        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
            .ifPresent(m -> {
                throw new IllegalStateException("이미 존재하는 회원입니다");
            });
    }

    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
