package my.mynote.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import my.mynote.domain.UserForm;
import my.mynote.repository.UserRepository;

@SpringBootTest
@Transactional
public class AuthServiceTest {

    @Autowired
    AuthService authService;

    @Test
    void checkAuthTest() {

        String testid = "dataman";
        String testpassword = "bdytest1234";

        UserForm userForm = new UserForm();
        userForm.setUser(testid);
        userForm.setPassword(testpassword);

        Assertions.assertThat(authService.checkAuth(userForm)).isEqualTo("pass");

    }

}
