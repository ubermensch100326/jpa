package main;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jpabasic.reserve.domain.User;

public class UserGetMain {
    public static void main(String[] args) {
        // persistence.xml에 설정한 영속성 이름(jpapersistenceunit)을 넣어줌
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpapersistenceunit");
        // 엔티티 매니저 팩토리로부터 엔티티 매니저를 생성
        // 엔티티 매니저는 일종의 DB 커넥션과 비슷한 역할을 함
        EntityManager entityManager = emf.createEntityManager();
        // 엔티티 매니저로부터 트랜잭션을 얻어냄
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            User user = entityManager.find(User.class, "user@user.com");
            if (user == null) {
                System.out.println("User 없음");
            } else {
                System.out.printf("User 있음: email=%s, name=%s, createDate=%s\n",
                        user.getEmail(), user.getName(), user.getCreateDate());
            }
            transaction.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            transaction.rollback();
        } finally {
            entityManager.close();
        }
        emf.close();
    }
}