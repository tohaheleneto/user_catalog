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
import users.RoleRepository;
import users.UserRepository;
import users.entity.Role;
import users.entity.User;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @GetMapping("/add")
    public String userForm(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("selectableRoles",roleRepository.findAll());
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
        return "show";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute User user, Model model,@RequestParam ("imageFile") MultipartFile imageFile) {
        if (userRepository.findByLogin(user.getLogin()) != null) {
            model.addAttribute("prev", user);
            model.addAttribute("user", new User());
            model.addAttribute("msg", "Login Already Exist");
            model.addAttribute("selectableRoles",roleRepository.findAll());
            return "userDuplicate";
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
        return "add";
    }

    @GetMapping("/deleteUser")
    public String deleteUser() {
        return "deleteUser";
    }

    @PostMapping("/deleteUser")
    public String deleteRole(Model model,@RequestParam String userName) {
        User user= userRepository.findByLogin(userName);
        if (user != null) {
            userRepository.delete(user);
            model.addAttribute("msg","User deleted successfully");
        }
        else
            model.addAttribute("msg","No such user exists");
        return "deleteUser";
    }


    @Configuration
    public class AdditionalResourceWebConfiguration implements WebMvcConfigurer {
        @Override
        public void addResourceHandlers(final ResourceHandlerRegistry registry) {
            registry.addResourceHandler("/images/**").addResourceLocations("file:images/");
        }
    }
}