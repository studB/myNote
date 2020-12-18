package my.mynote.repository;

import java.util.Optional;

import my.mynote.domain.User;

public interface UserRepository {

    Optional<User> findById(int id);

    Optional<User> findByUser(String user);

}
