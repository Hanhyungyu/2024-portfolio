package edu.du.sb1105.spring;

import java.time.LocalDateTime;

public class LoanDTO extends Books{

    private int loanID;
    private int book_ID;
    private String member_ID;
    private LocalDateTime loan_ID;
    private LocalDateTime return_date;

    private Member member;
    private Books book;

    public int getLoanID() {
        return loanID;
    }

    public void setLoanID(int loanID) {
        this.loanID = loanID;
    }

    public int getBook_ID() {
        return book_ID;
    }

    public void setBook_ID(int book_ID) {
        this.book_ID = book_ID;
    }

    public String getMember_ID() {
        return member_ID;
    }

    public void setMember_ID(String member_ID) {
        this.member_ID = member_ID;
    }

    public LocalDateTime getLoan_ID() {
        return loan_ID;
    }

    public void setLoan_ID(LocalDateTime loan_ID) {
        this.loan_ID = loan_ID;
    }

    public LocalDateTime getReturn_date() {
        return return_date;
    }

    public void setReturn_date(LocalDateTime return_date) {
        this.return_date = return_date;
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
}
