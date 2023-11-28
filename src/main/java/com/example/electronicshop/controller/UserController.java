package com.example.electronicshop.controller;

import com.example.electronicshop.model.Role;
import com.example.electronicshop.model.User;
import com.example.electronicshop.repo.UserRepo;
import com.example.electronicshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@Controller
@RequestMapping("/user")
@PreAuthorize("hasAuthority('ADMIN')")
public class UserController {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private UserService service;

    @GetMapping
    public String UserList(Model model) {
        model.addAttribute("user", userRepo.findAll());
        return "user/userList";
    }

    @GetMapping("{user}")
    public String userEditForm(@PathVariable User user, Model model){
        model.addAttribute("user", user);
        model.addAttribute("roles", Role.values());
        return "user/userEdit";
    }

    @PostMapping("/edit/{id}")
    public String UserSave(
            @PathVariable("id") long idi,
            @RequestParam(name = "id", defaultValue = "0") Long id,
            @RequestParam(name="username", defaultValue="ddd") String username,
            @RequestParam(name = "role", defaultValue="USER") Set<Role> role) {
        User user = new User();

        user.setRoles(role);
        user.setUsername(username);
        System.out.println("Before update");
        service.updateUser(idi, user);

        return "redirect:/user";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") long id){
        userRepo.deleteById(id);
        return "redirect:/user";
    }

}
