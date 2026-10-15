package com.example.discussion_forum.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class CoffeeController {

    @GetMapping("/coffee")
    public String index(){
        return "coffee/index";
    }

    @GetMapping("/coffee/create")
    public String create(){
        return "coffee/create";
    }
}
