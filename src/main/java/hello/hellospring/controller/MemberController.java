package hello.hellospring.controller;

import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MemberController {

    private MemberService memberService;

    // MemberController가 생성 될 때 스프링 빈에 등록이 되어있는 MemberService를 넣어준다 -> DI

    /**
     * 자동으로 스프링빈을 등록하는 두가지 방법
     * 1. 컴포넌트 스캔과 자동의존관계 설정 (내부에 @Component 가 있어서 자동으로 객체 생성 @Autowired는 연결해주는 역학)
     * 2. 자바코드로 직접 스프링빈 등록하기
     */
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/member/new")
    public String createForm() {
        return "members/createMemberForm";
    }
}
