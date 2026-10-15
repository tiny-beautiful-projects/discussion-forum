package com.example.discussion_forum.api;


import com.example.discussion_forum.dto.CoffeeDto;
import com.example.discussion_forum.entity.Coffee;
import com.example.discussion_forum.repository.CoffeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
public class CoffeeApiController {
    private CoffeeRepository coffeeRepository;

    // 생성 api/coffee (POST)
    @PostMapping("/api/coffee")
    public Coffee create(@RequestBody CoffeeDto dto){
      // 1. dto를 entity로 바꾸기
        log.info("entity ===>" + dto.toString());
        // 2. entity를 save로 하기
        Coffee coffeeEntity = dto.toEntity();
        // 3. return entity
        return coffeeRepository.save(coffeeEntity);
    }

    // 조회 /api/coffee (GET)

    // 상세 조회 /api/coffee/1 (GET)

    // 수정 /api/coffee/1 (PATCH)

    // 삭제 /api/coffee/1 (DELETE)
}
