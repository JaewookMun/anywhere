package prj.margin.anywhere;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import prj.margin.anywhere.domain.User;
import prj.margin.anywhere.domain.UserRole;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

@Component
@RequiredArgsConstructor
public class DbInitializer {

    private final InitService initService;

    @PostConstruct
    public void init() {
        initService.initUsers();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {

        private final EntityManager em;

        public void initUsers() {
            User dummy1 = new User();
            dummy1.setName("administrator");
            dummy1.setLoginId("admin");
            dummy1.setPassword("admin123");
            dummy1.setUserRole(UserRole.ADMIN);

            User dummy2 = new User();
            dummy2.setName("manager");
            dummy2.setLoginId("manager");
            dummy2.setPassword("manager123");
            dummy2.setUserRole(UserRole.ADMIN);

            em.persist(dummy1);
            em.persist(dummy2);
        }
    }
}
