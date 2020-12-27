package my.mynote.repository;

import java.util.List;
import java.util.Optional;

import my.mynote.domain.Content;

public interface TextRepository {

    Optional<Content> findById(int id);

    Optional<Content> findByTitle(String username, String title);

    List<Content> findByUsername(String username);

    int create(Content content);

    int Updatebody(Content content);
}
