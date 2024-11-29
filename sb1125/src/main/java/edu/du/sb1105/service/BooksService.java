package edu.du.sb1105.service;

import edu.du.sb1105.repository.BooksRepository;
import edu.du.sb1105.spring.Books;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.print.Book;
import java.util.Date;
import java.util.List;

@Service
public class BooksService {

    @Autowired
    private BooksRepository booksRepository;

    // 모든 도서 목록 조회
    public List<Books> getAllBooks() {
        return booksRepository.findAll();
    }

    // ISBN으로 도서 조회
    public Books getBookByIsbn(String isbn) {
        return booksRepository.findByIsbn(isbn);
    }

    // 제목으로 도서 검색
    public List<Books> searchBooksByTitle(String title) {
        return booksRepository.findByTitleContaining(title);
    }

    // 저자명으로 도서 검색
    public List<Books> searchBooksByAuthor(String author) {
        return booksRepository.findByAuthorContaining(author);
    }

    // ISBN, 제목, 저자 중 하나로 검색
    public List<Books> searchBooks(String isbn, String title, String author) {
        return booksRepository.findByIsbnContainingOrTitleContainingOrAuthorContaining(isbn, title, author);
    }

    // 도서 추가
    public Books addBooks(Books books) {
        return booksRepository.save(books);
    }

    // 도서 수정
    public Books updateBooks(Books books) {
        return booksRepository.save(books);
    }

    // 도서 삭제
    public void deleteBooks(int id) {
        booksRepository.deleteById(id);
    }
    //최근도서조회
    public List<Books> getRecentBooks() {
        return booksRepository.findTop5ByOrderByRegTimeDesc(); // 최근 등록된 책 5개를 조회
    }


}