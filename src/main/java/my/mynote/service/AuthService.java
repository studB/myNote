package my.mynote.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import my.mynote.domain.User;

import my.mynote.domain.UserForm;
import my.mynote.repository.UserRepository;

public class AuthService {

    private final UserRepository userRepository;

    @Autowired
    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String confirmName() {
        return "dataman";
    }

    public String checkAuth(UserForm userForm) {

        String inputUser = userForm.getUser();
        String inputPassword = userForm.getPassword();

        try {
            existedUserCheck(inputUser);
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
            return "deny";
        } finally {
            System.out.println("End Inspection");
        }

        User result = userRepository.findByUser(inputUser).get();

        if (inputUser.equals(result.getUser()) && inputPassword.equals(result.getPassword())) {
            return "pass";
        } else {
            return "deny";
        }

    }

    private void existedUserCheck(String inputUser) {
        Optional<User> result = userRepository.findByUser(inputUser);
        if (result.isEmpty()) {
            throw new IllegalStateException("That isn't our user...!");
        }
    }

}
