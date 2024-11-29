package edu.du.sb1105.spring;

import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "books")
@ToString
public class Books {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 255)
    private String title;

    @Column(nullable = false, length = 100)
    private String author;

    @Column(name = "published_year")
    private Integer publishedYear;

    @Column(length = 20, unique = true)
    private String isbn;

    @Column(name = "available_copies", nullable = false)
    private int availableCopies;

    @Column(name = "total_copies", nullable = false)
    private int totalCopies;

    @Column(name = "reg_time", nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Date regTime;

    // 기본 생성자, getter 및 setter 추가
    public Books() {
    }

    public Books(String title, String author, Integer publishedYear, String isbn, int availableCopies, int totalCopies) {
        this.title = title;
        this.author = author;
        this.publishedYear = publishedYear;
        this.isbn = isbn;
        this.availableCopies = availableCopies;
        this.totalCopies = totalCopies;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getPublishedYear() {
        return publishedYear;
    }

    public void setPublishedYear(Integer publishedYear) {
        this.publishedYear = publishedYear;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getAvailableCopies() {
        return availableCopies;
    }

    public void setAvailableCopies(int availableCopies) {
        this.availableCopies = availableCopies;
    }

    public int getTotalCopies() {
        return totalCopies;
    }

    public void setTotalCopies(int totalCopies) {
        this.totalCopies = totalCopies;
    }

    public Date getRegTime() {
        return regTime;
    }

    public void setRegTime(Date regTime) {
        this.regTime = regTime;
    }
}
