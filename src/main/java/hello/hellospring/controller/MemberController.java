package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {

    private MemberService memberService;

//    @Autowired private MemberService memberService -> 이 방식은 필드 주입. 별로안씀
//    @Autowired // -> 세터메소드를 이용한 주입. public으로 무조건 열려 있어야 쓸 수 있다.
//    public void setMemberService(MemberService memberService) {
//        this.memberService = memberService; }


    // -> 이 방식은 생성자 주입.
    // 생성자를 통해서 memberService가 memberController에 주입됨
    // 가장 추천되는 방식
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String createForm(){
        return "members/createMemberForm"; // -> template 폴더에서 먼저 찾는다. 없으면 static 폴더도 찾음
    }

    @PostMapping("/members/new")
    public String create(MemberForm form){
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model){
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }

}
