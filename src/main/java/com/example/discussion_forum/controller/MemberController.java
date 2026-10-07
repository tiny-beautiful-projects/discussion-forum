package com.example.discussion_forum.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MemberController {

    @GetMapping("/members/new")
    public String MemberForm(){
        return "/members/new";
    }
}
