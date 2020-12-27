package my.mynote.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;

import my.mynote.domain.Content;
import my.mynote.repository.TextRepository;

public class TextService {

    private final TextRepository textRepository;

    @Autowired
    public TextService(TextRepository textRepository) {
        this.textRepository = textRepository;
    }

    public List<Content> getList(String username) {

        List<Content> conts = textRepository.findByUsername(username);
        return conts;

    }

    public String loadBody(String username, String title) {

        Optional<Content> content = textRepository.findByTitle(username, title);
        return content.get().getBody();

    }

    public int createContent(String username, String title) {
        Content content = new Content();
        content.setUsername(username);
        content.setTitle(title);
        content.setBody("");
        int result = textRepository.create(content);

        return result;
    }

    public int Updatebody(Content content) {
        int result = textRepository.Updatebody(content);

        return result;
    }
}
