package edu.du.sb1105.controller;

import edu.du.sb1105.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class MainController {


    @Autowired
    private MemberService memberService;


    @GetMapping({"/"})
    public String main() {
        return "main/main";  // templates/main/main.html
    }


    @GetMapping("/main")
    public String showMainPage(Model model,
                               @SessionAttribute(name = "username", required = false) String username,
                               @SessionAttribute(name = "userId", required = false) String memberId) {


        if (username != null) {
            System.out.println("Session contains username: " + username);
        } else {
            System.out.println("No username in session");
        }

        if (memberId != null) {
            System.out.println("Session contains memberId: " + memberId);
        } else {
            System.out.println("No memberId in session");
        }

        model.addAttribute("username", username);
        model.addAttribute("memberId", memberId);
        return "main/main";
    }

    // 커뮤니티 페이지로 이동
    @GetMapping("/community")
    public String community(Model model) {
        model.addAttribute("community", "도서관 커뮤니티");
        return "community/community";  // templates/community/community.html
    }

    // 마이 라이브러리 화면
    @GetMapping("/mylibrary")
    public String myLibrary() {
        return "mylibrary/mylibrary";  // templates/mylibrary/mylibrary.html
    }

    // 검색 화면
    @GetMapping("/sortsearch")
    public String search() {
        return "search/SortSearch";  // templates/search/search.html
    }

    // 심화 검색 화면
    @GetMapping("/deepSearch")
    public String deepSearch() {
        return "search/deepSearch";  // templates/search/deepSearch.html
    }


    // 도서관 정보 화면
    @GetMapping("/libraryInformation")
    public String libraryInformation() {
        return "Usage/libraryInformation";  // templates/Usage/libraryInformation.html
    }

    // 도서관 소개 화면
    @GetMapping("/libraryIntro")
    public String libraryIntro() {
        return "Usage/libraryIntro";  // templates/Usage/libraryIntro.html
    }

    // 이용 시간 화면
    @GetMapping("/UsingTime")
    public String UsingTime() {
        return "Usage/UsingTime";  // templates/Usage/UsingTime.html
    }
}
