package com.superdaily.groceryapp.controller;

import com.superdaily.groceryapp.entity.User;
import com.superdaily.groceryapp.models.ApiResponse;
import com.superdaily.groceryapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired private UserService userService;

    @PostMapping("")
    public ApiResponse<String> signUp(@RequestBody User user){
        userService.signUp(user);
        return new ApiResponse<>("Sign Up success");
    }
}
