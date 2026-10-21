package com.example.discussion_forum.api;


import com.example.discussion_forum.dto.PizzaDto;
import com.example.discussion_forum.repository.PizzaRepository;
import com.example.discussion_forum.service.PizzaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// 클라이언트에게 DTO를 받고, Service를 호출한 뒤 결과를 응답해.
@Slf4j
@RestController
public class PizzaApiController {
    @Autowired
    private PizzaRepository pizzaRepository;
    @Autowired
    private PizzaService pizzaService;
    // 생성
    @PostMapping("/api/pizza")
    public ResponseEntity<PizzaDto> create(@RequestBody PizzaDto dto){
        // service에 위임
        PizzaDto created = pizzaService.create(dto);
        // 결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(created);
    }

    // 리스트 조회
    @GetMapping("/api/pizza")
    public ResponseEntity<List<PizzaDto>> pizzaList(){
        // service에 위임
        List<PizzaDto> dtos = pizzaService.getList();
        // 결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(dtos);

    }
    // 상세 조회
    @GetMapping("/api/pizza/{id}")
    public ResponseEntity<PizzaDto> detailPizza(@PathVariable Long id){
        // 서비스에 위침
        PizzaDto dto = pizzaService.getDetail(id);
        // 결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    // 수정
    @PatchMapping("/api/pizza/{id}")
    public ResponseEntity<PizzaDto> editPizza(@PathVariable Long id, @RequestBody PizzaDto dto){
        log.info("수정 요청 도착! id = {}", id);
        log.info("전달받은 DTO = {}", dto);
        try{
            PizzaDto edited = pizzaService.editPizza(id, dto);
            return ResponseEntity.status(HttpStatus.OK).body(edited);
        }catch(IllegalArgumentException e){

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    // 삭제

}
