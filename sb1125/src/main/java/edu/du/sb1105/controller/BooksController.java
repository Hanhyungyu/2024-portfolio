package edu.du.sb1105.controller;

import edu.du.sb1105.repository.BooksRepository;
import edu.du.sb1105.service.BooksService;
import edu.du.sb1105.spring.Books;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.awt.print.Book;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/books")  // /books로 경로 변경
public class BooksController {

    @Autowired
    private BooksService booksService;

    @Autowired
    private BooksRepository booksRepository;

    // 도서 목록 보기 (페이징 추가)
    @GetMapping("/list")
    public String showBooks(@RequestParam(defaultValue = "0") int page, Model model) {
        Pageable pageable = PageRequest.of(page, 5);  // 페이지당 5개의 책을 표시
        Page<Books> bookPage = booksRepository.findAll(pageable);
        model.addAttribute("books", bookPage.getContent());
        model.addAttribute("totalPages", bookPage.getTotalPages());
        model.addAttribute("currentPage", page);
        return "books";  // books.html을 렌더링
    }

    @GetMapping("/find")
    public String showBookFindPage() {
        return "bookFind"; // bookFind.html을 렌더링
    }

    // 책 검색 결과 페이지로 이동
    @GetMapping("/result")
    public String searchBooks(
            @RequestParam("query") String query,
            @RequestParam(value = "category", defaultValue = "all") String category, // 'all', 'title', 'author', 'isbn' 등 검색 기준을 파라미터로 받음
            Model model) {

        List<Books> books;

        // category 값에 따라 검색할 필드 다르게 설정
        if ("all".equals(category)) {
            books = booksService.searchBooks(query, query, query); // 전체 검색: title, author, isbn 모두 포함
        } else if ("title".equals(category)) {
            books = booksService.searchBooks(query, null, null); // title만 검색
        } else if ("author".equals(category)) {
            books = booksService.searchBooks(null, query, null); // author만 검색
        } else if ("isbn".equals(category)) {
            books = booksService.searchBooks(null, null, query); // isbn만 검색
        } else {
            books = booksService.searchBooks(query, query, query); // 기본은 전체 검색
        }

        model.addAttribute("books", books);
        model.addAttribute("query", query); // 검색어도 모델에 추가해서 검색어를 유지할 수 있게 함
        model.addAttribute("category", category); // 선택한 카테고리도 유지
        return "bookResult"; // bookResult.html을 렌더링
    }

    @GetMapping("/newBooks")
    public String getRecentBooks(Model model) {
        List<Books> recentBooks = booksService.getRecentBooks(); // 서비스에서 최근 책을 가져옵니다

        // 책 목록이 존재하는지 확인하고 콘솔에 출력
        if (recentBooks != null && !recentBooks.isEmpty()) {
            recentBooks.forEach(book -> {
                System.out.println("책 제목: " + book.getTitle());
                System.out.println("저자: " + book.getAuthor());
                System.out.println("출판 연도: " + book.getPublishedYear());
                System.out.println("ISBN: " + book.getIsbn());
                System.out.println("등록 시간: " + book.getRegTime());
            });
        } else {
            System.out.println("최근 등록된 책이 없습니다.");
        }

        model.addAttribute("books", recentBooks); // 모델에 데이터 추가
        return "newBooks"; // newBooks.html을 렌더링
    }

}
