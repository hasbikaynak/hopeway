package com.hpw.controller;

import com.hpw.payload.response.UserResponse;
import com.hpw.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;


    //getById()
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public UserResponse getUserById(@PathVariable Long id){
        return userService.getUserById(id);
    }


}
