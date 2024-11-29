package edu.du.sb1105.service;


import edu.du.sb1105.repository.MemberRepository;
import edu.du.sb1105.spring.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    // 아이디와 비밀번호로 회원 인증
    public Member authenticate(String memberid, String password) {
        // DB에서 아이디와 비밀번호로 회원 조회
        return memberRepository.findByMemberIdAndPassword(memberid, password);
    }
}
