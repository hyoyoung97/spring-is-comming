package hello.hellospring.repository;

import hello.hellospring.domain.Member;
//import org.junit.jupiter.api.Assertions;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

//Assertions 를 선언하지 않고도 메소드를 사용할 수 있다
import static org.assertj.core.api.Assertions.*;

//다른 곳에 가져다 쓸 것이 아니기 떄문에 public을 지워도 된다
public class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    //Test는 순서와 상관없이 따로 동작하게 만들어야 한다.
    //메소드가 하나동작할 때마다 실행되는 메소드
    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }

    @Test
    public void save() {
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        //Optional 에서 값을 꺼낼 때는 get() 으로 꺼낸다. 
        //이러한 방식이 좋지는 않지만 테스트에서는 상관없음
        Member result = repository.findById(member.getId()).get();

        //저장을 한 것과 DB에서 꺼낸것이 같으면 참
//        System.out.println("result = " + (member == result));

        //jupiter가 제공하는,, (expected,  )
//        Assertions.assertEquals(member, result);

        //assertj 가 제공하는,,
//        Assertions.
        assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        //shift + F6 -> 이름 전체 변환 단축키
        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

//        Member result = repository.findByName(member1.getName()).get();
        Member result = repository.findByName("spring1").get();
        assertThat(member1).isEqualTo(result);
    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();
        assertThat(result.size()).isEqualTo(2);
    }

}
