package com.example.discussion_forum.api;


import com.example.discussion_forum.dto.CoffeeDto;
import com.example.discussion_forum.entity.Coffee;
import com.example.discussion_forum.repository.CoffeeRepository;
import com.example.discussion_forum.service.CoffeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;


@Slf4j
@RestController
public class CoffeeApiController {

    @Autowired
    private CoffeeRepository coffeeRepository;

    @Autowired
    CoffeeService coffeeService;

    @GetMapping("/api/coffee")
    public ArrayList<Coffee> index(){
        return coffeeService.index();
    }


    @GetMapping("/api/coffee/{id}")
    public ResponseEntity<Coffee> getCoffee(@PathVariable Long id){
         Coffee target =  coffeeService.detail(id);
         if(target == null){
             return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
         }
         return ResponseEntity.status(HttpStatus.OK).body(target);
    }

    @PostMapping("/api/coffee")
    public ResponseEntity<Coffee> create(@RequestBody CoffeeDto dto){
        Coffee created = coffeeService.create(dto);
        return (created !=null) ?  ResponseEntity.status(HttpStatus.OK).body(created)
                : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }


    @PatchMapping("/api/coffee/{id}")
    public ResponseEntity<Coffee> editCoffee(@PathVariable Long id, @RequestBody CoffeeDto dto){
          Coffee updated =  coffeeService.update(id, dto);
          if(updated == null){
              return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
          }
        return ResponseEntity.status(HttpStatus.OK).body(updated);
    };

    // 삭제 /api/coffee/1 (DELETE)
    @DeleteMapping("/api/coffee/{id}")
    public ResponseEntity<Void> deleteCoffee(@PathVariable Long id){

        // 1. 삭제할 id를 db에서 찾기
        Coffee target = coffeeRepository.findById(id).orElse(null);
        // 2. 없는 경우 404 에러 반환
        if(target == null){
            // 응답 본문 없이 반환
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

            // 응답 본문에 문자열을 넣어서 반환
//            return ResponseEntity.status(HttpStatus.NOT_FOUND)
//                    .body("커피를 찾을 수 없습니다.");
        }
        // 3. 있는 경우에만 삭제!
        coffeeRepository.delete(target);
       return ResponseEntity.status(HttpStatus.OK).build();
    }


}
