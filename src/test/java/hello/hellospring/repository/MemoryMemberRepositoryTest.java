package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    //중요한 내용임
    @AfterEach
    public void afterEach()
    {
        repository.clearStore();
    }

    @Test
    public void save()
    {
        Member member = new Member();
        member.setName("Spring");
        repository.save(member);

        Member result= repository.findById(member.getId()).get();
        //System.out.println("result = "+ (result==member));
        // 매번 글자로 보긴 어렵다 따라서 이걸쓴다.,
        //Assertions.assertEquals(member ,null);
        // 알아보기..
        assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName()
    {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();

        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll()
    {
        Member member1 = new Member();
        member1.setName("Spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("Spring2");
        repository.save(member2);

        Member member3 = new Member();
        member3.setName("Spring3");
        repository.save(member3);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(3);
    }


}
