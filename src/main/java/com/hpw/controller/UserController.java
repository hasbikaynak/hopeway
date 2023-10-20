package com.hpw.controller;

import com.hpw.payload.response.ResponseMessage;
import com.hpw.payload.response.UserResponse;
import com.hpw.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PreAuthorize("hasAnyAuthority('USER')")
    @GetMapping("/getUserByUserName")
    public UserResponse getUserByUsername(@RequestParam(name = "userName") String userName){
        return userService.getUserByUsername(userName);
    }

    @PreAuthorize("hasAnyAuthority('USER')")
    @GetMapping("/getUserByEmail")
    public UserResponse getUserByEmail(@RequestParam(name = "email") String email) {
        return userService.getUsersByEmail(email);
    }
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseMessage deleteUser(@PathVariable Long id){
        return userService.deleteUser(id);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public UserResponse getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }
}
