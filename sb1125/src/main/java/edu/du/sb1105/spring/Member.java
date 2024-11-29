package edu.du.sb1105.spring;

import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "lmembers")
@ToString
public class Member implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // AUTO_INCREMENT에 해당하는 설정
    @Column(name = "id")
    private Long id;  // DB에서 자동 증가하는 고유 ID

    @Column(name = "member_id", unique = true, nullable = false)
    private String memberId;  // 회원 고유 ID (Primary Key)

    @Column(name = "password", nullable = false)
    private String password; // 비밀번호

    @Column(name = "name")
    private String name; // 이름

    // Getter & Setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
