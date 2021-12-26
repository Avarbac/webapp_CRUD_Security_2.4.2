package org.example.controllers;

import org.example.dao.UserDAO;
import org.example.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
public class UsersController {

    private final UserDAO userDAO;

    @Autowired
    public UsersController(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Transactional
    @GetMapping(value = "/")
    public String printAllUsers(Model model) {
        model.addAttribute("users", userDAO.getAllUsers());
        return "users/index";
    }

    @Transactional
    @GetMapping("/{id}")
    public String printUserById(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userDAO.getUserById(id));
        return "users/show";
    }

    @GetMapping("/new")
    public String showNewUser(@ModelAttribute("user") User user) {
        return "users/new";
    }

    @Transactional
    @PostMapping()
    public String createUser(@ModelAttribute("newUser") User user) {
        userDAO.saveUser(user);
        return "redirect:/users/";
    }

    @Transactional
    @GetMapping("/{id}/edit")
    public String editUser(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userDAO.getUserById(id));
        return "users/edit";
    }

    @Transactional
    @PatchMapping("/{id}")
    public String updateUser(@ModelAttribute("user") User user, @PathVariable("id") int id) {
        userDAO.updateUser(user, id);
        return "redirect:/users/";
    }

    @Transactional
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable("id") int id) {
        userDAO.deleteUser(id);
        return "redirect:/users/";
    }
}