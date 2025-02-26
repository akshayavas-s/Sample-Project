package com.exampleapi.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/userStory")
public class UserStoryController {

    @PostMapping("/addStory")
    public String addUserStory() {
        return "User story added successfully";
    }
}
