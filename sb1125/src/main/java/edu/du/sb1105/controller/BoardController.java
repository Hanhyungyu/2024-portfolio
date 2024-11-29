package edu.du.sb1105.controller;

import edu.du.sb1105.service.BoardService;
import edu.du.sb1105.spring.Board;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;

@Controller
@RequestMapping("/boards")
public class BoardController {

    @Autowired
    private BoardService boardService;

@GetMapping
public String getBoards(Model model) {
    // 로그인 여부와 관계없이 게시글 목록을 조회
    model.addAttribute("boards", boardService.getAllBoards());
    return "board/BoardList"; // 게시글 목록을 보여주는 뷰 반환
}

    @GetMapping("/{id}")
    public String getBoardDetail(@PathVariable("id") Long boardId, Model model, HttpSession session) {
        Board board = boardService.getBoardById(boardId);
        if (board == null) {
            return "redirect:/boards"; // 게시글이 없으면 게시글 목록으로 리디렉션
        }

        model.addAttribute("board", board);

        return "board/BoardDetail"; // 상세 페이지로 이동
    }

    @GetMapping("/write")
    public String getBoardWritePage(HttpSession session) {
        String memberId = (String) session.getAttribute("userId");

        // 로그인하지 않은 경우 로그인 페이지로 리디렉션
        if (memberId == null) {
            return "redirect:/login";
        }

        return "board/BoardWrite"; // 로그인한 사용자만 게시글 작성 페이지로 이동
    }

    // 게시글 작성 처리
    @PostMapping("/write")
    public String createBoard(@RequestParam String title, @RequestParam String content, HttpSession session) {
        String memberId = (String) session.getAttribute("userId");

        // 로그인하지 않은 경우 로그인 페이지로 리디렉션
        if (memberId == null) {
            return "redirect:/login";
        }

        // 게시글 작성
        String username = (String) session.getAttribute("username");  // 세션에서 username 가져오기
        Board board = new Board(title, content, memberId,  LocalDate.now());
        boardService.createBoard(board);

        return "redirect:/boards"; // 게시글 작성 후 목록 페이지로 리디렉션
    }

    // 게시글 수정 페이지 접근 제한
    @GetMapping("/edit/{id}")
    public String getBoardEditPage(@PathVariable Long id, HttpSession session, Model model) {
        String memberId = (String) session.getAttribute("userId");

        if (memberId == null) {
            return "redirect:/login"; // 로그인하지 않은 경우
        }

        Board board = boardService.getBoardById(id);

        // 작성자와 현재 로그인 사용자가 같은지 확인
        if (!board.getMemberId().equals(memberId)) {
            throw new RuntimeException("수정 권한이 없습니다."); // 권한이 없는 경우
        }

        model.addAttribute("board", board);
        return "board/BoardUpdate"; // 수정 페이지로 이동
    }

    // 게시글 수정 처리
    @PostMapping("/edit/{id}")
    public String updateBoard(@PathVariable Long id, @RequestParam String title, @RequestParam String content, HttpSession session) {
        String memberId = (String) session.getAttribute("userId");

        if (memberId == null) {
            return "redirect:/login"; // 로그인하지 않은 경우
        }

        Board existingBoard = boardService.getBoardById(id);

        // 작성자와 현재 로그인 사용자가 같은지 확인
        if (!existingBoard.getMemberId().equals(memberId)) {
            throw new RuntimeException("수정 권한이 없습니다."); // 권한이 없는 경우
        }

        // 수정 정보 적용
        existingBoard.setTitle(title);
        existingBoard.setContent(content);
        existingBoard.setUpdatedDate(LocalDate.now());
        boardService.updateBoard(id, existingBoard);

        return "redirect:/boards"; // 수정 후 목록으로 리디렉션
    }


    // 게시글 삭제 접근 제한
    @GetMapping("/delete/{id}")
    public String deleteBoard(@PathVariable Long id, HttpSession session) {
        String memberId = (String) session.getAttribute("userId");

        if (memberId == null) {
            return "redirect:/login"; // 로그인하지 않은 경우
        }

        Board board = boardService.getBoardById(id);

        // 작성자와 현재 로그인 사용자가 같은지 확인
        if (!board.getMemberId().equals(memberId)) {
            throw new RuntimeException("삭제 권한이 없습니다."); // 권한이 없는 경우
        }

        boardService.deleteBoard(id);
        return "redirect:/boards";
    }

}
