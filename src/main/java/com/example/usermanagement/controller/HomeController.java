package com.example.usermanagement.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController
{
    @GetMapping String home()
    {
        return "User Management API is runnning. Try /users";
    }
}
