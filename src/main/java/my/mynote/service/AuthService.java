package my.mynote.service;

import my.mynote.domain.UserForm;

public class AuthService {

    public String confirmName() {
        return "dataman";
    }

    public String checkAuth(UserForm userForm) {

        if (userForm.getName().equals("dataman") && userForm.getPassword().equals("bdytest1234")) {
            return "pass";
        } else {
            return "deny";
        }

    }

}
