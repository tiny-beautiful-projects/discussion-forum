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
        // entity를 dto 형태로 return
        // return pizzas.stream().map(PizzaDto:: toDto).toList();
        return pizzas.stream().map(pizza -> PizzaDto.toDto(pizza)).toList();
    }


}
