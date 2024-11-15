package com.example.demo.controller;

import com.example.demo.dto.User;
import com.example.demo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/view/users")
public class UserViewController {

    // UserService를 주입받아 사용
    private final UserService userService;

    public UserViewController(UserService userService) {
        this.userService = userService;
    }

    // 사용자 정보 조회
    @GetMapping
    public String getAllUsers(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "users";
    }

    // 사용자 생성
    @GetMapping("/create")
    public String createUserForm(Model model) {
        model.addAttribute("user", new User());
        return "createUser";
    }

    // 사용자 정보 생성
    @PostMapping("/create")
    public String createUser(@RequestParam String name, @RequestParam String email) {
        userService.createUser(name, email);
        return "redirect:/view/users";
    }

    // 사용자 정보 수정
    @GetMapping("/edit/{id}")
    public String editUserForm(@PathVariable Long id, Model model) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "editUser";
    }

    // 사용자 정보 수정
    @PostMapping("/update/{id}")
    public String updateUser(@PathVariable Long id, @RequestParam String name, @RequestParam String email) {
        userService.updateUser(id, name, email);
        return "redirect:/view/users";
    }

    // 사용자 정보 삭제
    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return "redirect:/view/users";
    }
}
