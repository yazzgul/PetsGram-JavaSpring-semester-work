package ru.itis.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.itis.model.User;
import ru.itis.services.impl.UserService;

import java.util.List;

@RequiredArgsConstructor
@Controller
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
public class AdminController {
    private final UserService userService;

    @GetMapping("/admin")
    public String toAdmin(Model model) {
        List<User> users = userService.getUsersList();
        model.addAttribute("users", users);
        return "admin_view";
    }
    @GetMapping("/admin/user/edit/{id}")
    public String editUserChangeRoleById(@PathVariable("id") Long id) {
        userService.changeUserRole(id);
        return "redirect:/admin";
    }
    @PostMapping("/admin/user/delete/{id}")
    public String deleteUserByAdmin(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }
}
