package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 초록색 주석 띄우기
 * option + command +  L -> 코드 자동 정렬
 * command + option + V -> 리턴형과 변수 자동 입력
 * control + T -> 리팩토링 관련 -> method
 * command + shift + T -> 테스트 클래스 자동완성
 * command + B -> 들어가기
 */
@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("data", "hello!!");
        //hello.html 이 열린다는 뜻
        return "hello";
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model) {
        model.addAttribute("name", name);
        return "hello-template";
    }


    @GetMapping("hello-string")
    //HTML의 BODY를 얘기하는 것이 아니라, HTTP 응답의 body 부분에 데이터를 직접 넣어주겠다는 뜻이다.
    //소스보기를 하면 html 이 안나옴 -> 이 방법을 쓸일은 거의 없음
    @ResponseBody
    public String helloString(@RequestParam("name") String name) {
        return "hello" + name;
    }

    //중요!
    //{"name":"spring"} 이렇게 JSON 형식으로 나옴
    @GetMapping("hello-api")
    //웹브라우저에서 URL을 던지면 톰캣내장 서버에서 Spring에 hello-api를 던져서 찾음
    //있는데 @ResponseBody 가 있으면 HTTP 응답에 그대로 데이터를 넘겨야 겠구나 한다
    //ViewResolver 대신에 HttpMessageConverter 가 응담해야할 데이터가 String 인지 객체인지 확인을 한다
    // 객체 -> default 로 JsonConverter(MappingJackson2HttpMessageConvert) 가 동작해서 JSON 으로 바꿈
    // String -> StringConverter
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }

    public static class Hello{
        private String name;
        //JAVA BEAN 규약 프로퍼티 방식
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }



}
