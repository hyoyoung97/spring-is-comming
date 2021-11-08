package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

//Optional 로 감싸면 Optional안에 멤버 객체가 있는데 Optional 의 메소드를 쓸 수 있음
//이전에는 if(xx==null) 이런 식으로 했지만 지금은 null 일 가능성이 있으면 Optional로 한번 감싸준다
//감싸기 때문에 ifPresent 이러한 메소드를 사용할 수 있다
//result.get() <- 이러한 방식 보다는 result.orElseGet() <- 값이 있으면 꺼내고 없으면 메소드를 실행해 default 값을 꺼내 라는 식으로 설계한다

public class MemberService {
    private final MemberRepository memberRepository = new MemoryMemberRepository();

    /**
     * 회원 가입
     */
    public Long join(Member member) {
        //같은 이름이 있는 중복 회원X
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                //ifPresent -> 값이 있으면
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
