package hoangnguyen.dev.lab_4.controllers;

import hoangnguyen.dev.lab_4.models.Role;
import hoangnguyen.dev.lab_4.models.User;
import hoangnguyen.dev.lab_4.requests.UserEdit;
import hoangnguyen.dev.lab_4.requests.UserRequest;
import hoangnguyen.dev.lab_4.services.RoleService;
import hoangnguyen.dev.lab_4.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @GetMapping
    public String showFormUser(Model model){
        List<User> userList = userService.getAllUsers();
        model.addAttribute("users",userList);
        return "/users/frm-view";
    }
    @GetMapping("/new")
    public String addStudent(Model model){
        User user = new User();
        List<Role> roles = roleService.getAllRoles();
        model.addAttribute("user", user);
        model.addAttribute("roles",roles);
        return "users/frm-addUser";
    }
    @PostMapping("/save")
    public String addUser(UserRequest userRequest, BindingResult result){
        if (result.hasErrors()) {
            return "/users/frm-addUser";
        }
        userService.createUser(userRequest);
        return "redirect:/users";
    }
    @GetMapping("/edit/{id}")
    public String editStudent(@PathVariable Long id, Model model){
        User user = userService.getUserById(id);
        model.addAttribute("users", user);
        return "/users/frm-edit";
    }
    @PostMapping("/saveedit")
    public String saveUserEdit(UserEdit userEdit){
        userService.updateUser(userEdit);
        return "redirect:/users";
    }
    @GetMapping("/view/{id}")
    public String ViewUser(@PathVariable Long id, Model model){
        User user = userService.getUserById(id);
        model.addAttribute("users", user);
        return "/users/frm-view";
    }
}
