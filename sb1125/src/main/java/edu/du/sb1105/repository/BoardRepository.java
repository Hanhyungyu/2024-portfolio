package edu.du.sb1105.repository;

import edu.du.sb1105.spring.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {
    // memberId로 게시글 조회
    List<Board> findByMemberId(String memberId);

    // 최신 게시글 조회
    List<Board> findAllByOrderByCreatedDateDesc();
}
