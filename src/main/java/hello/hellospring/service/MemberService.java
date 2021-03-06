package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    // 회원가입
    public Long join(Member member)
    {
        // 같은 이름이 있는 중복 회원은 안됨
        // 방법1
        /*
        Optional<Member> result =  memberRepository.findByName(member.getName());
        result.ifPresent(member1 -> {
            throw  new IllegalStateException("이미 존재하는 회원입니다.");
        });
        */
        validateDuplicateMember(member); // 컨트롤+알트+M 으로 메소드로 따로 빼준다.

        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
            .ifPresent(member1 -> {
            throw  new IllegalStateException("이미 존재하는 회원입니다.");
        });
    }

    public List<Member> findMembers()
    {
      return  memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId)
    {
        return memberRepository.findById(memberId);
    }

}
