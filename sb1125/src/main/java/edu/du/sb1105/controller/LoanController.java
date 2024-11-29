package edu.du.sb1105.controller;
import edu.du.sb1105.service.LoanService;
import edu.du.sb1105.spring.Loan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class LoanController {

    @Autowired
    private LoanService loanService;



    // 세션에서 username, memberId를 가져와 해당 회원의 대출 기록을 조회하는 메서드
    @GetMapping("/loans")
    public String getLoansBySession(Model model,
                                    @SessionAttribute(name = "username", required = false) String username,
                                    @SessionAttribute(name = "userId", required = false) String memberId) {

        // 세션에 정보가 있는지 확인하고, 없으면 로그인 페이지로 리디렉션
        if (username == null || memberId == null) {
            return "redirect:/login"; // 로그인 페이지로 리디렉션 (로그인 페이지를 "/login"으로 설정)
        }

        // 세션에서 받은 정보로 대출 기록을 조회
        List<Loan> loanList = loanService.getLoansByMemberId(memberId);

        System.out.println("대출 기록 리스트: " + loanList);

        // 대출 기록을 모델에 추가
        model.addAttribute("loanList", loanList);
        model.addAttribute("username", username);
        model.addAttribute("memberId", memberId);

        // 대출 기록이 포함된 메인 페이지 반환
        return "loan/LoanHistory"; // loan/loanList는 대출 기록을 보여주는 페이지로 가정
    }

    @PostMapping("/LoanExtend")
    public String extendLoan(@RequestParam Integer loanId) {
        loanService.extendLoan(loanId);  // 연장 처리
        return "redirect:/loans";  // 연장 후 대출 기록 페이지로 리디렉션
    }
}
