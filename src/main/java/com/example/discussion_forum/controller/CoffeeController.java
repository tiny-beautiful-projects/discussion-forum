package com.example.discussion_forum.controller;


import com.example.discussion_forum.dto.CoffeeDto;
import com.example.discussion_forum.entity.Coffee;
import com.example.discussion_forum.repository.CoffeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;

@Slf4j
@Controller
public class CoffeeController {

    @Autowired
    private CoffeeRepository coffeeRepository;

    // 브라우저가 GET /coffee 요청을 보냈을 때 이 메서드를 실행하라는 뜻
    @GetMapping("/coffee")
    public String index(Model model){

        // 1. 모든 데이터 가져오기
        ArrayList<Coffee> coffeeEntityList =  coffeeRepository.findAll();
        // 2. 모델에 데이터 등록하기
        model.addAttribute("coffeeList", coffeeEntityList);
        // 3. 페이지 반환
        return "coffee/index";
    }

    @GetMapping("/coffee/new")
    public String create(){
        return "/coffee/new";
    }

    // 커피 생성
    @PostMapping("/coffee/create")
    public String createCoffee(CoffeeDto dto){
        log.info("coffee DTO ==> {}", dto);
        Coffee coffeeEntity = dto.toEntity();
        log.info("coffee Entity ==> {}", coffeeEntity);
        coffeeRepository.save(coffeeEntity);
        // 스프링의 redirect: 뒤에는 HTML 파일 경로(coffee/index)를 적는 것이 아니라, 컨트롤러의 @GetMapping 주소(url)를 적어야 합니다.
        return "redirect:/coffee";
    }

    // 커피 상세 조회
    @GetMapping("/coffee/{id}")
    public String getDetailCoffee(@PathVariable Long id, Model model){
        // id로 db에서 찾는다. 없으면 null 처리
        Coffee target = coffeeRepository.findById(id).orElse(null);
        //2. 가져온 db를 화면에 뿌리자!. 뿌리기 위해서는 모델에 등록해야 한다.
        model.addAttribute("coffeeDetail", target);

        // 뷰페이지 반환하기
        return "/coffee/show";
    }

    // 커피 수정
    @GetMapping("/coffee/{id}/edit")
    public String editCoffee(@PathVariable Long id, Model model){

        // 수정할 데이터 가져오기
        Coffee target = coffeeRepository.findById(id).orElse(null);
        // 모델에 데이터 등록하기
        if(target != null){
            model.addAttribute("coffeeEdit", target);
        }
        // 3. 페이지 반환
        return "/coffee/edit";
    }

    // 커피 수정 화면에서 수정하기 버튼을 누르면 일어나는 일

    @PostMapping("/coffee/update")
    public String updateCoffee(CoffeeDto dto){
        System.out.println("dto ===>" + dto.toString());
        // dto를 entity변화한다
        Coffee coffeeEntity = dto.toEntity();

        // db에서 해당 아이템을 가져오기
        Coffee target = coffeeRepository.findById(coffeeEntity.getId()).orElse(null);
        // patch를 한다
        if(target != null){
            coffeeRepository.save(coffeeEntity);
        }
        return "redirect:/coffee/" + coffeeEntity.getId();
    }


}
