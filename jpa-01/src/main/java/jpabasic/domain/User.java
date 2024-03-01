package jpabasic.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Entity // DB 테이블과 매핑할 클래스임을 나타냄
@Table(name = "user") // user 테이블과 매핑
public class User {
    @Id // 식별자에 대응 (보통 테이블의 PK에 매핑)
    private String email; // email column과 매핑
    private String name; // name column과 매핑

    @Column(name = "create_date") // create_date column과 매핑
    private LocalDateTime createDate;

    protected User() {
    }

    public User(String email, String name, LocalDateTime createDate) {
        this.email = email;
        this.name = name;
        this.createDate = createDate;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void changeName(String newName) {
        this.name = newName;
    }
}