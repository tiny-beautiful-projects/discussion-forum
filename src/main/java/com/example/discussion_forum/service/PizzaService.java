package com.example.discussion_forum.service;


import com.example.discussion_forum.dto.PizzaDto;
import com.example.discussion_forum.entity.Pizza;
import com.example.discussion_forum.repository.PizzaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class PizzaService {
    @Autowired
    PizzaRepository pizzaRepository;
    public PizzaDto create(PizzaDto dto) {
        // 1. entity로 변환
        Pizza pizzaEntity = dto.toEntity();
        // 2. 디비에 저장
        Pizza created = pizzaRepository.save(pizzaEntity);
        // 4. dto 형태로 return
        return PizzaDto.toDto(created);
    };


    public List<PizzaDto> getList() {
        // db에서 가져오기 (entity)
        List<Pizza> pizzas = pizzaRepository.findAll();
        return pizzas.stream().map(pizza -> PizzaDto.toDto(pizza)).toList();
    }


    public PizzaDto getDetail(Long id) {
        // db에서 가져오기 없으면 에러를 내기
        Pizza target = pizzaRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 피자를 찾을 수 없습니다."));
        // entity를 dto 형태로 return
        return PizzaDto.toDto(target);
    }

    public PizzaDto editPizza(Long id, PizzaDto dto) {
        // id와 dto id 비교를 한다. 같지 않으면 에러!
        if(id != dto.getId()) {
            throw new IllegalArgumentException("잘못된 요청입니다. ID가 일치하지 않습니다.");
        }
        Pizza pizza = pizzaRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 피자를 찾을 수 없습니다."));
        // dto 를 타켓 데이터(entity)에 업데이트 한다.
        pizza.patch(dto);
        // 타켓 데이터(entity) 를 save하자.
        Pizza saved = pizzaRepository.save(pizza);
        log.info("저장된 피자!! ++" + saved);
        return PizzaDto.toDto(saved);
    }

    public PizzaDto deletePizza(Long id) {
        // id를 db에서 찾기. 없으면 에러를 던져용
        Pizza target = pizzaRepository.findById(id).orElse(null);
        if(target == null){
            return null;
        }
        // 삭제하기
        pizzaRepository.delete(target);
        return PizzaDto.toDto(target);
    }
}
