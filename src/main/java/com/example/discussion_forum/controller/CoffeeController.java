package com.example.discussion_forum.controller;


import com.example.discussion_forum.entity.Coffee;
import com.example.discussion_forum.repository.CoffeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;

@Slf4j
@Controller
public class CoffeeController {

    @Autowired
    private CoffeeRepository coffeeRepository;

    @GetMapping("/coffee")
    public String index(Model model){

        // 1. 모든 데이터 가져오기
        ArrayList<Coffee> coffeeEntityList =  coffeeRepository.findAll();
        // 2. 모델에 데이터 등록하기
        model.addAttribute("coffeeList", coffeeEntityList);
        // 3. 페이지 반환
        return "coffee/index";
    }

    @GetMapping("/coffee/create")
    public String create(){
        return "coffee/create";
    }
}
