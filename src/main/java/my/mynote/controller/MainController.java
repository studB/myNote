package my.mynote.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import my.mynote.domain.UserForm;
import my.mynote.service.AuthService;

@Controller
public class MainController {

    private final AuthService authService;

    @Autowired
    public MainController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("check_result", "noconfirm");
        return "home/home";
    }

    @PostMapping("/check")
    public String authCheck(UserForm userForm) {

        String result = authService.checkAuth(userForm);

        if (result.equals("deny")) {
            return "redirect:/?check_result=deny";
        } else {
            return "check/confirm";
        }

    }

    @GetMapping("/list")
    public String viewList() {
        return "list/list";
    }

}
