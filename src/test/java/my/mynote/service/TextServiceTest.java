package my.mynote.service;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.attoparser.dom.Text;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import my.mynote.domain.Content;

@SpringBootTest
@Transactional
public class TextServiceTest {

    @Autowired
    private TextService textService;

    @Test
    void getListTest() {
        String username = "testman";
        List<Content> conts = textService.getList(username);
        Assertions.assertThat(conts.size()).isEqualTo(2);
    }

    @Test
    void loadBodyTest() {
        String username = "testman";
        String title = "title";
        String body = textService.loadBody(username, title);
        Assertions.assertThat(body).isNotBlank();
    }

    @Test
    void createContentTest() {
        String username = "testman";
        String title = "newtitle";
        int result = textService.createContent(username, title);
        Assertions.assertThat(result).isGreaterThan(1);
    }

    @Test
    void UpdatebodyTest() {
        String username = "testman";
        String title = "title";
        String body = "Change2";
        Content content = new Content();
        content.setUsername(username);
        content.setTitle(title);
        content.setBody(body);
        int result = textService.Updatebody(content);
        Assertions.assertThat(result).isEqualTo(0);
        Assertions.assertThat(textService.loadBody(username, title)).isEqualTo(body);
    }
}
