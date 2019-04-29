package users.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import users.RoleRepository;
import users.entity.Role;

@Controller
public class RoleController {
    @Autowired
    private RoleRepository roleRepository;

    @GetMapping("/addRole")
    public String addRolGet(Model model) {
        model.addAttribute("role",new Role());
        return "addRole";
    }
    @PostMapping("/addRole")
    public String addRolePost(@ModelAttribute Role role,Model model) {
        if (roleRepository.findByName(role.getName()) != null)
        {
            model.addAttribute("msg","Role already exist");
            model.addAttribute("role",role);
            return "roleDuplicate";
        }
        roleRepository.save(role);
        return "redirect:/";
    }

    @GetMapping("/deleteRole")
    public String deleteRole() {
        return "deleteRole";
    }

    @PostMapping("/deleteRole")
    public String deleteRole(@RequestParam String RoleName) {
        roleRepository.delete(roleRepository.findByName(RoleName));
        return "redirect:/";
    }

}
