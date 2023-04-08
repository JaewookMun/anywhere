package prj.margin.anywhere.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import prj.margin.anywhere.domain.User;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserRepository {

    private final EntityManager em;

    public void flush() {
        em.flush();
    }

    public Long save(User user) {
        em.persist(user);

        return user.getId();
    }

    public User findOne(Long id) {
        return em.find(User.class, id);
    }

    public User findOneByLoginId(String loginId) {
        return em.createQuery("select u from User u where u.loginId = :loginId", User.class)
                .setParameter("loginId", loginId)
                .getResultList().get(0);
    }

    public List<User> findAll() {
        return em.createQuery("select u from User u", User.class)
                .getResultList();
    }
}
