package main03;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jpabasic.reserve.domain.User;

public class RemoveUserService {

    public void removeUser(String email) {
        EntityManager em = EMF.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            User user = em.find(User.class, email);
            if (user == null) {
                throw new NoUserException();
            }
            /*
             * 트랜잭션 범위 안에서 find() 메서드로 읽어온 객체를 전달해야 삭제할 수 있음
             */
            em.remove(user);
            /*
             * 이 시점에 다른 프로세스가 데이터를 삭제하면 예외 발생
             * 그렇게 발생하는 예외를 알아서 잘 처리하면 됨
             */
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
    }
}
