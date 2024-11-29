package edu.du.sb1105.repository;

import edu.du.sb1105.spring.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface LoanRepository extends JpaRepository<Loan, Integer> {
    // memberId로 대출 기록 조회
    List<Loan> findByMemberMemberId(String memberId);
}