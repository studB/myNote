package my.mynote.repository;

import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import my.mynote.domain.User;

@SpringBootTest
@Transactional
public class JdbcTemplateUserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Test
    void findByIdTest() {

        User user = new User();

        user = userRepository.findById(1).get();

        Assertions.assertThat(user.getId()).isEqualTo(1);

    }

    @Test
    void findByUserTest() {

        User user = new User();

        user = userRepository.findByUser("dataman").get();

        Assertions.assertThat(user.getUser()).isEqualTo("dataman");

    }

}
