package hello.hellospring.service;

import hello.hellospring.domain.Member;


import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    // 테스트 할때 다른 레퍼지토리 객체로 테스트를 하는 경우는 거의 없다. 따라서 생성자를 통해서 외부에 있는것을 가져와 사용하는데 이것을 DI 디펜던시 인잭션이라고 한다.
    @BeforeEach
    public void beforEach()
    {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }


    @AfterEach
    public void afterEach()
    {
        memberRepository.clearStore();
    }

    // 테스트 이름은 한글로 해도 된다.
    @Test
    void 회원가입() {
        // given
        Member member = new Member();
        member.setName("Hello");

        // when
        Long saveId = memberService.join(member);


        // then
        Member findmember =  memberService.findOne(saveId).get();

        Assertions.assertThat(member.getName()).isEqualTo(findmember.getName());
    }

    @Test
    public void 중복_회원_예외()
    {
        //given
        Member member1 = new Member();
        member1.setName("Spring");

        Member member2 = new Member();
        member2.setName("Spring");

        //when
        memberService.join(member1);
        // 방법 1 애매하다.
/*        try {
            memberService.join(member2);
            fail();
        }
        catch (IllegalStateException e)
        {
            Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        }*/

        // 방법2 변수 꿀팁 컨트롤 알트 v
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

        //then

    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }

}