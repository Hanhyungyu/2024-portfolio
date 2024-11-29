package edu.du.sb1105.service;

import edu.du.sb1105.repository.LoanRepository;
import edu.du.sb1105.spring.Loan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class LoanService {

    @Autowired
    private LoanRepository loanRepository;

    private static final Logger logger = LoggerFactory.getLogger(LoanService.class);

    // memberId로 대출 기록 조회
    public List<Loan> getLoansByMemberId(String memberId) {
        return loanRepository.findByMemberMemberId(memberId);
    }

    @Transactional
    public String extendLoan(Integer loanId) {
        Loan loan = loanRepository.findById(loanId)
                .orElseThrow(() -> new RuntimeException("대출 기록을 찾을 수 없습니다."));

        // 연장이 이미 완료된 경우
        if (loan.isExtended() != null && loan.isExtended()) {
            logger.warn("이미 연장이 완료된 대출 기록입니다. 대출 ID: {}", loanId);
            return "이미 연장이 완료된 대출 기록입니다."; // 연장이 완료된 상태일 경우 처리

        }

        // 연장이 가능한 경우 (연장되지 않은 상태)
        if (loan.getReturnDate() != null) {
            loan.setReturnDate(loan.getReturnDate().plusDays(7));  // 연장 기간 추가
            loan.setExtended(true);  // 연장이 완료된 상태로 설정
            loanRepository.save(loan);
            logger.info("대출 기록 연장됨: {}", loan);
            return "대출 연장이 완료되었습니다.";
        } else {
            loan.setExtended(false);
            loanRepository.save(loan);
            logger.warn("대출 기록 연장 불가: {}", loan);
            return "연장 불가 대출 기록입니다.";
        }
    }


}
