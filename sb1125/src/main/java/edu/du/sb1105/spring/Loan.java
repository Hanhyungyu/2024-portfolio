package edu.du.sb1105.spring;

import lombok.ToString;

import javax.persistence.*;
import java.awt.print.Book;
import java.time.LocalDateTime;

@Entity
@Table(name = "loan")
@ToString
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "loan_id")
    private Integer loanId;

    @ManyToOne
    @JoinColumn(name = "member_id", referencedColumnName = "member_id", nullable = false)
    private Member member;  // 'lmembers' 테이블과 연관 (회원 엔티티)

    @ManyToOne
    @JoinColumn(name = "book_id", referencedColumnName = "id", nullable = false)
    private Books book;  // 'books' 테이블과 연관 (책 엔티티)

    @Column(name = "loan_date", columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime loanDate;  // 대출일

    @Column(name = "return_date")
    private LocalDateTime returnDate;  // 반납일

    @Column(name = "extended")
    private Boolean extended;  // 연장 여부

    // Getters and Setters
    public Integer getLoanId() {
        return loanId;
    }

    public void setLoanId(Integer loanId) {
        this.loanId = loanId;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Books getBook() {
        return book;
    }

    public void setBook(Books book) {
        this.book = book;
    }

    public LocalDateTime getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(LocalDateTime loanDate) {
        this.loanDate = loanDate;
    }

    public LocalDateTime getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDateTime returnDate) {
        this.returnDate = returnDate;
    }

    public Boolean isExtended() {
        return extended;
    }

    public void setExtended(Boolean extended) {
        this.extended = extended;
    }
}
