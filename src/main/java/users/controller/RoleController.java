package users.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import users.RoleRepository;
import users.UserRepository;
import users.entity.Role;
import users.entity.User;

@Controller
public class RoleController {
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/addRole")
    public String addRolGet(Model model) {
        model.addAttribute("role",new Role());
        return "addRole";
    }
    @PostMapping("/addRole")
    public String addRolePost(Model model,@ModelAttribute Role role) {
        if (roleRepository.findByName(role.getName()) != null)
        {
            model.addAttribute("msg","Role already exist");
            model.addAttribute("role",role);
            return "roleDuplicate";
        }
        roleRepository.save(role);
        model.addAttribute("msg","Role successfully added");
        return "addRole";
    }

    @GetMapping("/deleteRole")
    public String deleteRole() {
        return "deleteRole";
    }

    @PostMapping("/deleteRole")
    public String deleteRole(Model model,@RequestParam String RoleName) {
        Role role = roleRepository.findByName(RoleName);
        if (role != null) {
            for (User user : userRepository.findAll()) {
                user.getRoles().remove(role.getName());
            }
            model.addAttribute("msg","Role deleted successfully");
            roleRepository.delete(role);
        }
        else
            model.addAttribute("msg","No such role exists");
        return "/deleteRole";
    }

}
