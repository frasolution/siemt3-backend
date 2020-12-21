package com.mycompany.siemproject.controller;

import com.mycompany.siemproject.model.User;
import com.mycompany.siemproject.service.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/user")
public class UserController {

    @Autowired
    private UserService userService;
    
    @GetMapping
    @ResponseBody
    public List<User> getAll() {
        return userService.getAll();
    }
 
    @PostMapping
    public User createUser(@RequestBody User user) throws Exception {
        return userService.createUserOrUpdate(user);
    }
}

