package com.tms.controller;

import com.tms.model.User;
import com.tms.model.dto.UserRequestDto;
import com.tms.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/create")
    public String getUserCreatePage() {
        return "createUser";
    }

    @GetMapping("/update-page/{id}")
    public String getUserUpdatePage(@PathVariable("id") Long userId, Model model, HttpServletResponse response) {
        Optional<User> user = userService.getUserById(userId);
        if (user.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND); //404
            model.addAttribute("message", "User not found: id=" + userId);
            return "innerError";
        }
        model.addAttribute("user", user.get());
        return "editUser";
    }

    //Create
    @PostMapping("/create")
    public String createUser(@ModelAttribute("user") UserRequestDto userRequestDto, HttpServletResponse response, Model model) {
        Optional<User> createdUser = userService.createUser(userRequestDto);
        if (createdUser.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_CONFLICT);
            model.addAttribute("message", "User not created");
            return "innerError";
        }
        model.addAttribute("user", createdUser.get());
        return "user";
    }

    //Read
    @GetMapping("/{id}")
    public String getUserById(@PathVariable("id") Long id, Model model, HttpServletResponse response) {
        Optional<User> user = userService.getUserById(id);
        if (user.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND); //404
            model.addAttribute("message", "User not found: id=" + id);
            return "innerError";
        }
        response.setStatus(HttpServletResponse.SC_OK); //200
        model.addAttribute("user", user.get());
        return "user";
    }

    //Update
    @PostMapping
    public String updateUser(@ModelAttribute("user") UserRequestDto userRequestDto, Model model, HttpServletResponse response) {
        Optional<User> userUpdated = userService.updateUser(userRequestDto);
        if (userUpdated.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_CONFLICT);
            model.addAttribute("message", "User not updated.");
            return "innerError";
        }
        response.setStatus(HttpServletResponse.SC_OK);
        model.addAttribute("user", userUpdated.get());
        return "user";
    }

    //Delete
    @PostMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Long userId, Model model, HttpServletResponse response) {
        Optional<User> userDeleted = userService.deleteUser(userId);
        if (userDeleted.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_CONFLICT);
            model.addAttribute("message", "User not deleted.");
            return "innerError";
        }
        response.setStatus(HttpServletResponse.SC_OK);
        model.addAttribute("user", userDeleted.get());
        return "user";
    }

    //getAll
    @GetMapping("/getAll")
    public String getUserListPage(Model model, HttpServletResponse response) {
        List<User> users = userService.getAllUsers();
        if (users.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_CONFLICT);
            model.addAttribute("message", "Users not found");
            return "innerError";
        }
        response.setStatus(HttpServletResponse.SC_OK);
        model.addAttribute("users", users);
        return "usersList";
    }
}
