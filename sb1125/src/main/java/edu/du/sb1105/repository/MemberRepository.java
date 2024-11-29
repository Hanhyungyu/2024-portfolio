package edu.du.sb1105.repository;

import edu.du.sb1105.spring.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

    // member_id와 password로 회원 조회
    Member findByMemberIdAndPassword(String memberId, String password);
}