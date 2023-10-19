package com.hpw.controller;

import com.hpw.payload.response.UserResponse;
import com.hpw.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    // @PreAuthorize("hasAnyAuthority('USER')")
    @GetMapping("/getUserByUsername")
    public List<UserResponse> getUserByUsername(@RequestParam(name = "username") String username){
        return userService.getUserByUsername(username);
    }

    // @PreAuthorize("hasAnyAuthority('USER')")
    @GetMapping("/getUserByEmail")
    public List<UserResponse> getUserByEmail(@RequestParam(name = "email") String email){
        return userService.getUsersByEmail(email);
    }
}
