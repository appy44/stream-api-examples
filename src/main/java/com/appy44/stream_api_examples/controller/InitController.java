package com.appy44.stream_api_examples.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InitController {

    @GetMapping("/")
    public String home() {
        return "Project is up and running!";
    }
}