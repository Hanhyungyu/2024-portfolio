package edu.du.sb1105.service;

import edu.du.sb1105.repository.BoardRepository;
import edu.du.sb1105.spring.Board;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    // 모든 게시글 조회
    public List<Board> getAllBoards() {
        return boardRepository.findAllByOrderByCreatedDateDesc();

    }

    // 특정 회원의 게시글 조회
    public List<Board> getBoardsByMemberId(String memberId) {
        return boardRepository.findByMemberId(memberId);
    }

    // 게시글 작성
    public Board createBoard(Board board) {
        return boardRepository.save(board);
    }

    // 게시글 수정
    public Board updateBoard(Long id, Board board) {
        Board existingBoard = boardRepository.findById(id).orElseThrow(() -> new RuntimeException("게시글을 찾을 수 없습니다."));
        existingBoard.setTitle(board.getTitle());
        existingBoard.setContent(board.getContent());
        existingBoard.setUpdatedDate(board.getUpdatedDate());
        return boardRepository.save(existingBoard);
    }

    // 게시글 삭제
    public void deleteBoard(Long id) {
        boardRepository.deleteById(id);
    }

    public Board getBoardById(Long id) {
        return boardRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("게시글을 찾을 수 없습니다."));
    }

}
