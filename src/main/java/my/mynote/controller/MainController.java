package my.mynote.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.condition.ParamsRequestCondition;

import my.mynote.domain.Content;
import my.mynote.domain.ContentForm;
import my.mynote.domain.UserForm;
import my.mynote.service.AuthService;
import my.mynote.service.TextService;

@Controller
public class MainController {

    private final AuthService authService;
    private final TextService textService;

    @Autowired
    public MainController(AuthService authService, TextService textService) {
        this.authService = authService;
        this.textService = textService;
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("check_result", "noconfirm");
        return "home/home";
    }

    @PostMapping("/check")
    public String authCheck(UserForm userForm, Model model) {

        String result = authService.checkAuth(userForm);

        if (result.equals("deny")) {
            return "redirect:/check";
        } else {
            model.addAttribute("username", userForm.getUser());
            return "check/confirm";
        }

    }

    @GetMapping("/check")
    public String authdeny(Model model) {

        model.addAttribute("check_result", "deny");

        return "home/home";
    }

    @GetMapping("/list")
    public String viewList(@RequestParam("username") String name, @RequestParam("newtitle") String newtitle,
            Model model) {

        if (newtitle.length() != 0) {
            textService.createContent(name, newtitle);
        }
        model.addAttribute("username", name);
        List<Content> conts = textService.getList(name);
        model.addAttribute("conts", conts);
        return "list/list";
    }

    @GetMapping("/list/delete")
    public String deleteOne(@RequestParam("username") String username, @RequestParam("title") String title) {
        Content content = new Content();
        content.setTitle(title);
        content.setUsername(username);
        textService.deleteC(content);
        String params = "username=" + content.getUsername() + "&newtitle=";
        return "redirect:/list?" + params;
    }

    @GetMapping("/list/content")
    public String viewCotent(@RequestParam("title") String title, @RequestParam("username") String username,
            Model model) {

        String body = textService.loadBody(username, title);

        model.addAttribute("username", username);
        model.addAttribute("title", title);
        model.addAttribute("body", body);

        return "list/content";
    }

    @PostMapping("/list/content")
    public String bodyUpdate(ContentForm contentForm, Model model) {

        Content content = new Content();
        content.setUsername(contentForm.getUsername());
        content.setTitle(contentForm.getTitle());
        content.setBody(contentForm.getBody());

        // Update Logic
        textService.Updatebody(content);

        String params = "username=" + content.getUsername() + "&title=" + content.getTitle();
        return "redirect:/list/content?" + params;
    }

}
