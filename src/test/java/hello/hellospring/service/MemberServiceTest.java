package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;


class MemberServiceTest {
    //테스트 코드는 한글로도 많이 적음, 빌드될 때 포함되지 않음

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    //MemberService 입장에서 MemberRepository 를 직접만들지 않고 직접 넣어준다 -> DI(dependency injection) 의존성 주입
    //의존성 주입의 의도는 객체의 생성과 사용의 관심을 분리하는 것이다. 이는 가독성과 코드 재사용을 높혀준다.
    @BeforeEach
    public void beforeEach () {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);

    }
    @AfterEach
    public void afterEach() {
        memberRepository.clearStore();
    }

    @Test
    void 회원가입() {   //given -> when -> then 순으로 이 패턴으로 변형해나가기
        //given - 무언가가 주어졌는데
        Member member = new Member();
        member.setName("spring");

        //when - 이걸로 실행했을 때
        Long saveId = memberService.join(member);

        //then - 이것이 나와야 한다
        Member findMember = memberService.findOne(saveId).get();
//        assertThat(member).isEqualTo(findMember);
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외() {
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when
        memberService.join(member1);

        //then
        //jupiter
        //member2 가 join되면(이름이 같은 회원이 가입하면) memberService 에서 IllegalStateException 가 일어나게끔 설정되었음
        // IllegalStateException가 일어나는지 확인해주고 에러메시지가 동일한지 확인해주는 코드
        IllegalStateException e = Assertions.assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
/*

        try {
            memberService.join(member2);
            fail("예외가 발생해야 합니다.");
        } catch (IllegalStateException e) {
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        }
*/
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}