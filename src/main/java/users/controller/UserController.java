package users.controller;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import users.UserRepository;
import users.entity.User;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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
    public String userPage(@RequestParam int id, Model model) {
        model.addAttribute("user",userRepository.findByid(id));
        return "heh";
    }


    @PostMapping("/add")
    public String greetingSubmit(@ModelAttribute User user, Model model,@RequestParam ("imageFile") MultipartFile imageFile) {
        if (userRepository.findByLogin(user.getLogin()) != null) {
            model.addAttribute("prev", user);
            model.addAttribute("user", new User());
            model.addAttribute("msg", "Login Already Exist");
            return "duplicate";
        }
        try {
            byte[] bytes = imageFile.getBytes();
            String extension = imageFile.getOriginalFilename().substring(imageFile.getOriginalFilename().lastIndexOf("."));
            Path absolute = Paths.get(".");
            Path path = Paths.get(absolute + "/images/" + user.getLogin() + extension);
            user.setPictureName(user.getLogin() + extension);
            userRepository.save(user);
            Files.createDirectories(path.getParent());
            Files.write(path,bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        model.addAttribute("array",userRepository.findAll());
        return "redirect:/";
    }

    @Configuration
    public class AdditionalResourceWebConfiguration implements WebMvcConfigurer {
        @Override
        public void addResourceHandlers(final ResourceHandlerRegistry registry) {
            registry.addResourceHandler("/images/**").addResourceLocations("file:images/");
        }
    }

}