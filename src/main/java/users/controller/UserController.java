package users.controller;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import users.UserRepository;
import users.entity.User;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/add")
    public String userForm(Model model) {
        model.addAttribute("user", new User());
        return "add";
    }

    @GetMapping("/")
    public String table(Model model) {
        model.addAttribute("array",userRepository.findAll());
        return "table";
    }
    @GetMapping("/userPage")
    public String userPage(@RequestParam String name, Model model) {
        System.out.println(name);
        return "heh";
    }

    @PostMapping("/add")
    public String greetingSubmit(@ModelAttribute User user, Model model) {
        userRepository.save(user);
        model.addAttribute("array",userRepository.findAll());
        return "table";
    }

}