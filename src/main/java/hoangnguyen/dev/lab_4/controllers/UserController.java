package hoangnguyen.dev.lab_4.controllers;

import hoangnguyen.dev.lab_4.models.User;
import hoangnguyen.dev.lab_4.services.RoleService;
import hoangnguyen.dev.lab_4.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @GetMapping
    public String showUserList(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "/users/user-list";
    }
    // For adding a new user
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("roles", roleService.getAllRoles());
        return "/users/add-user";
    }
    @PostMapping("/add")
    public String addUser(@Valid User user,
                             BindingResult result) {
        if (result.hasErrors()) {
            return "/users/add-user";
        }
        userService.addUser(user);
        return "redirect:/users";
    }
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id,
                               Model model) {
        User user = userService.getUserById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        model.addAttribute("user",user);
        model.addAttribute("roles", roleService.getAllRoles()); //
        return "/users/update-user";
    }
    // Process the form for updating a user
    @PostMapping("/update/{id}")
    public String updateUser(@PathVariable Long id,
                                @Valid User user,
                                BindingResult result) {
        if (result.hasErrors()) {
            user.setId(id);
            return "/users/update-user";
        }
        userService.updateUser(user);
        return "redirect:/users";
    }
    // Handle request to delete a user
    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUserById(id);
        return "redirect:/users";
    }
}
