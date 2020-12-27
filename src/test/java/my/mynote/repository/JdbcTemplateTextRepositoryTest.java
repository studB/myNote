package my.mynote.repository;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import my.mynote.domain.Content;

@SpringBootTest
@Transactional
public class JdbcTemplateTextRepositoryTest {

    @Autowired
    TextRepository textRepository;

    @Test
    void findByIdTest() {
        Content content = new Content();
        content = textRepository.findById(1).get();
        Assertions.assertThat(content.getId()).isEqualTo(1);
    }

    @Test
    void findByUsernameTest() {
        String username = "testman";
        List<Content> conts = textRepository.findByUsername(username);
        Assertions.assertThat(conts.size()).isEqualTo(2);
    }

    @Test
    void createTest() {
        Content c = new Content();
        c.setTitle("aa");
        c.setBody("bb");
        c.setUsername("gg");
        Assertions.assertThat(textRepository.create(c)).isEqualTo(3);
    }

    @Test
    void deleteContentTest() {
        Content c = new Content();
        c.setTitle("title");
        c.setUsername("testman");
        System.out.println(textRepository.deleteContent(c));
        Assertions.assertThat(textRepository.deleteContent(c)).isGreaterThan(0);

    }

}
