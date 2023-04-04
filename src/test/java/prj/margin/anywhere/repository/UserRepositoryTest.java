package prj.margin.anywhere.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import prj.margin.anywhere.domain.User;
import prj.margin.anywhere.domain.Role;

@RunWith(SpringRunner.class)
@SpringBootTest
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Test
    @Transactional
    @Rollback(value = false)
    void testUser() {
        // when
        User user = new User();
        user.setName("testor");

        // given
        Long saveId = userRepository.save(user);
        User findOne = userRepository.findOne(saveId);

        // then
        Assertions.assertThat(findOne.getId()).isEqualTo(saveId);
    }

    @Test
    @Transactional
    @Rollback(value = false)
    void testLoginID() {
        User user = new User();
        user.setName("mun");
        user.setRole(Role.ADMIN);
        user.setLoginId("mun1103");

        userRepository.save(user);
        userRepository.flush();

        User oneByLoginId = userRepository.findOneByLoginId(user.getLoginId());

        System.out.println("oneByLoginId = " + oneByLoginId);
    }

}