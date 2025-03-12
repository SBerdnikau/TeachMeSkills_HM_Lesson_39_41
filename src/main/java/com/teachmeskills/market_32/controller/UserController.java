package com.teachmeskills.market_32.controller;

import com.teachmeskills.market_32.model.User;
import com.teachmeskills.market_32.servise.UserService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    public String getUserCreatePage(){
        return "createUser";
    }

    //update
    @GetMapping
    public String getUserUpdatePage(@RequestParam("userId") Long userId, Model model, HttpServletResponse response){
        Optional<User> user = userService.getUserById(userId);
        if(user.isEmpty()){
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            model.addAttribute("message", "User not found: id=" + userId);
            return "error";
        }
        model.addAttribute("user", user.get());
        return "edit";
    }

    //Create
   @PostMapping("/create")
    public String createUser(@ModelAttribute("user") User user, Model model, HttpServletResponse response ){
        Optional<User> createdUser = userService.createUser(user);
        if(createdUser.isEmpty()){
            response.setStatus(HttpServletResponse.SC_CONFLICT);
            model.addAttribute("message", "User not created");
            return "error";
        }
        model.addAttribute("user", createdUser.get());
        return "user";
    }

    //Read
    @GetMapping("/{id}")
    public String getUserById(@PathVariable Long id, Model model, HttpServletResponse response){
        Optional<User> user = userService.getUserById(id);
        if(user.isEmpty()){
            response.setStatus(HttpServletResponse.SC_CONFLICT);
            model.addAttribute("message", "User not found: id=" + id);
            return "error";
        }
        response.setStatus(HttpServletResponse.SC_OK);
        model.addAttribute("user", user.get());
        return "user";
    }

    //Update
    @GetMapping
    public String updateUser(@ModelAttribute("user") User user, Model model, HttpServletResponse response ){
        Optional<User> userUpdated = userService.updateUser(user);
        if(userUpdated.isEmpty()){
            response.setStatus(HttpServletResponse.SC_CONFLICT);
            model.addAttribute("message", "User not updated");
            return "error";
        }
        response.setStatus(HttpServletResponse.SC_OK);
        model.addAttribute("user", userUpdated.get());
        return "user";
    }

    //Delete
    @GetMapping("/delete")
    public String deleteUser(@RequestParam("userId") Long userId, Model model, HttpServletResponse response){
        Optional<User> userDeleted = userService.deleteUser(userId);
        if(userDeleted.isEmpty()){
            response.setStatus(HttpServletResponse.SC_CONFLICT);
            model.addAttribute("message", "User not deleted");
            return "error";
        }
        response.setStatus(HttpServletResponse.SC_OK);
        model.addAttribute("user", userDeleted.get());
        return "user";
    }
}
