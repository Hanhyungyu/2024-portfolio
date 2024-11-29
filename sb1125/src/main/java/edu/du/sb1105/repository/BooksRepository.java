package edu.du.sb1105.repository;


import edu.du.sb1105.spring.Books;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;


@Repository
public interface BooksRepository extends JpaRepository<Books, Integer> {

    // ISBN으로 도서 찾기
    Books findByIsbn(String isbn);

    // 제목으로 도서 찾기
    List<Books> findByTitleContaining(String title);

    // 저자로 도서 찾기
    List<Books> findByAuthorContaining(String author);

    // ISBN, 제목, 저자 조합으로 검색 (옵션: 하나 이상의 필드 사용)
    List<Books> findByIsbnContainingOrTitleContainingOrAuthorContaining(String isbn, String title, String author);

   //신착도서조회
    List<Books> findTop5ByOrderByRegTimeDesc();

}
