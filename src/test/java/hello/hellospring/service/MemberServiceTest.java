package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository = new MemoryMemberRepository();

    @BeforeEach
    public void beforeEach(){ // 각 테스트를 실행하기 전에 실행.
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);

        // memberService입장에서 보면, 내가 직접 new 하지 않고 외부에서 memberRepository를 넣어주고 있다
        // 이것을 DI 라고 부름
    }

    @AfterEach
    public void afterEach(){
        memberRepository.clearStore();
    }

    @Test
    @DisplayName("회원가입이 정상적으로 작동된다")
    void join() {
        // given when then 문법을 뼈대로 테스트 코드를 작성해보자

        // given -> 주어진 상황
        Member member = new Member();
        member.setName("hello");

        // when -> 검증할 것
        Long saveId = memberService.join(member);

        // then -> 검증된 결과는?
//     Optional<Member> findMember = memberService.findOne(saveId); -> .get()을 써서 Optional을 없앰
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    @DisplayName("중복된 회원의 처리도 잘 된다.")
    public void dupException(){
        // given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        // when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class,
                () -> memberService.join(member2));

        // then
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다");
    }
    @Test
    @DisplayName("회원 검색이 정상적으로 작동된다.")
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}