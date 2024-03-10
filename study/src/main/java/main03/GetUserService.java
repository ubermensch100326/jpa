package main03;

import jakarta.persistence.EntityManager;
import jpabasic.reserve.domain.User;

public class GetUserService {

    public User getUser(String email) {
        EntityManager em = EMF.createEntityManager();
        try {
            User user = em.find(User.class, email);
            if (user == null) {
                // RuntimeException을 상속받은 예외는 throws로 선언할 필요 없음
                throw new NoUserException();
            }
            return user;
        } finally {
            em.close();
        }
    }
}
