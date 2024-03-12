package jpabasic.reserve.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

/*
 * Entity 클래스 제약 조건
 * 1. @Entity 적용해야 함
 * 2. @Id 적용해야 함
 * 3. 인자 없는 기본 생성자 필요
 * 4. 기본 생성자는 public이나 protected로 선언
 * 5. 최상위 클래스여야 함 (중첩 클래스 불가능)
 * 6. final 클래스면 안 됨
 */

/*
 * 접근 타입
 * 1. 필드 접근: 필드 값을 사용해서 매핑
 * 2. 프로퍼티 접근: getter/setter를 사용해서 매핑
 * 
 * 설정 방법
 * @Id 어노테이션을 필드에 붙이면 필드 접근
 * @Id 어노테이션을 getter 메서드에 붙이면 프로퍼티 접근
 * @Access 어노테이션을 사용해서 명시적으로 지정할 수도 있음 (@Access(AccessType.FIELD) or @Access(AccessType.PROPERTY))
 * 
 * 일반적으로는 필드 접근이 선호된다고 함
 * 굳이 setter 메서드를 만들 필요가 없음
 */

/*
 * 객체와 테이블 매핑 : @Entity, @Table
 * 기본 키 매핑 : @Id
 * 필드와 컬럼 매핑 : @Column
 * 연관관계 매핑 : @ManyToOne, @JoinColumn
 */

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