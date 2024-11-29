package edu.du.sb1105.controller;

import edu.du.sb1105.service.MemberService;
import edu.du.sb1105.spring.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model; // Model import 추가
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Autowired
    private MemberService memberService; // MemberService는 DB 조회를 위한 서비스 클래스

    // 로그인 페이지 표시
    @GetMapping("/login")
    public String loginPage() {
        return "login/login"; // login.html 페이지를 반환
    }

    // 로그인 처리
    @PostMapping("/login")
    public String login(@RequestParam("memberid") String memberid,
                        @RequestParam("password") String password,
                        HttpSession session,  // HttpSession 추가
                        Model model) { // Model 추가

        // 사용자가 입력한 아이디와 비밀번호로 회원 정보 확인
        Member member = memberService.authenticate(memberid, password);

        if (member != null) {
            // 로그인 성공 시, 회원 정보를 세션에 저장
            session.setAttribute("userId", member.getMemberId()); //세션에 사용자 id 저장
            System.out.println("userId: " + member.getMemberId());
            session.setAttribute("username", member.getName()); // 세션에 사용자 이름 저장
            System.out.println("username: " + member.getName());  // 콘솔에 출력
            return "redirect:/main"; // 로그인 성공 후, 메인 페이지로 리디렉션

        } else {
            // 로그인 실패 시, 에러 메시지와 함께 로그인 페이지로 되돌아감
            model.addAttribute("error", "아이디 또는 비밀번호가 일치하지 않습니다.");
            return "login"; // 로그인 페이지로 돌아가면서 에러 메시지 표시
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        // 세션에서 사용자 정보 제거
        session.removeAttribute("userId");
        session.removeAttribute("username");
        return "redirect:/login";  // 로그아웃 후 로그인 페이지로 리디렉션
    }


}

