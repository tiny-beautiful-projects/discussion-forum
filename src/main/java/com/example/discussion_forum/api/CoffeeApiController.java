package com.example.discussion_forum.api;


import com.example.discussion_forum.dto.CoffeeDto;
import com.example.discussion_forum.entity.Coffee;
import com.example.discussion_forum.repository.CoffeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;


@Slf4j
@RestController
public class CoffeeApiController {

    @Autowired
    private CoffeeRepository coffeeRepository;

    // 생성 api/coffee (POST)
    @PostMapping("/api/coffee")
    public Coffee create(@RequestBody CoffeeDto dto){
        // 1. dto를 entity로 바꾸기
        Coffee coffeeEntity = dto.toEntity();
        // 2. entity를 save로 하기
        return coffeeRepository.save(coffeeEntity);
    }

    // 조회 /api/coffee (GET)
    @GetMapping("api/coffee")
    public ArrayList<Coffee> getCoffees(){
        return coffeeRepository.findAll();
    }

    // 상세 조회 /api/coffee/1 (GET)
    @GetMapping("api/coffee/{id}")
    public Coffee getCoffee(@PathVariable Long id){
        // 1. 해당 커피를 db에서 찾아보기. 못 찾을 경우 null처리
        // 2. 찾은 걸 뿌려주기
        return coffeeRepository.findById(id).orElse(null);
    }

    // 수정 /api/coffee/1 (PATCH)

    // 삭제 /api/coffee/1 (DELETE)
}
