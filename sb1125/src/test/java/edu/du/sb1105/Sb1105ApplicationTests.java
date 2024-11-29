package edu.du.sb1105;


import edu.du.sb1105.repository.LoanRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class Sb1105ApplicationTests {

    @Autowired
    private LoanRepository loanRepository;



    @Test
    void contextLoads() {
        System.out.println(loanRepository.findByMemberMemberId("m001"));

    }



}
