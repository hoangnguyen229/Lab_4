package hoangnguyen.dev.lab_4.controllers;

import hoangnguyen.dev.lab_4.models.Role;
import hoangnguyen.dev.lab_4.services.RoleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/roles")
public class RoleController {
    @Autowired
    private RoleService roleService;
    @GetMapping("/add")
    public String showAddForm(Model model){
        model.addAttribute("role" ,new Role());
        return "/role/add-role";
    }

    @PostMapping("/add")
    public String addRole(@Valid Role role, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "/role/add-role";
        }
        roleService.saveRole(role);
        return "redirect:/roles";
    }

    @GetMapping
    public String listRoles(Model model){
        List<Role> roles = roleService.getAllRoles();
        model.addAttribute("roles",roles);
        return "/role/roles-list";
    }

    // POST request to update role
    @PostMapping("/update/{id}")
    public String updateRole(@PathVariable("id") Long id, @Valid Role role,
                                 BindingResult result, Model model) {
        if (result.hasErrors()) {
            role.setRole_id(id);
            return "/role/update-role";
        }
        roleService.updateRole(role);
        model.addAttribute("roles", roleService.getAllRoles());
        return "redirect:/roles";
    }
    // GET request for deleting role
    @GetMapping("/delete/{id}")
    public String deleteRole(@PathVariable("id") Long id, Model model) {
        Role role = roleService.getRoleById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid role Id:"
                        + id));
        roleService.deleteRoleById(id);
        model.addAttribute("roles", roleService.getAllRoles());
        return "redirect:/roles";
    }

}
