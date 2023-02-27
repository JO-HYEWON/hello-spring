package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("/hello")
    public String hello(Model model) {
        model.addAttribute("data", "hello!");
        return "hello"; // templates의 hello.html을 실행시킴
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model){
        model.addAttribute("name", name);
        return "hello-template";
    }

    @GetMapping("hello-string")
    @ResponseBody
    public String helloString(@RequestParam("name") String name){
        return "hello" + name;
    } // 이 방식은 화면 없이 그냥 그대로 내려준다.

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi (@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello; // 아무런 정보 없이 그냥 객체를 반환하면 JSON 형태로 반환된다.
    }

    static class Hello  {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

}
