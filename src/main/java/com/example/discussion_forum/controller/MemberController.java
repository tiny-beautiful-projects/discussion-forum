package com.example.discussion_forum.controller;


import com.example.discussion_forum.dto.MemberForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MemberController {

    @GetMapping("/members/new")
    public String MemberForm(){

        return "/members/new";
    }

    @PostMapping("/join")
    public String CreateForm(MemberForm memberForm){
        // DTO 를 통해서 사용자가 입력한 값 가져오기.
        System.out.println(memberForm.toString());
        // DTO를 entity로 저장하기
        return "";
    }
}
