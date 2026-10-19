package com.example.discussion_forum.service;

import com.example.discussion_forum.dto.CoffeeDto;
import com.example.discussion_forum.entity.Coffee;
import com.example.discussion_forum.repository.CoffeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.http.HttpResponse;
import java.util.ArrayList;

@Slf4j
@Service
public class CoffeeService {
    @Autowired
    private CoffeeRepository coffeeRepository;

    public ArrayList<Coffee> index() {
       return coffeeRepository.findAll();
    }
    public Coffee detail(Long id){
        return coffeeRepository.findById(id).orElse(null);
    }
    public Coffee create(CoffeeDto dto){
        Coffee coffeeEntity = dto.toEntity();
        if(coffeeEntity.getId() != null){
            return null;
        }
        return coffeeRepository.save(coffeeEntity);
    }
    public Coffee update(Long id, CoffeeDto dto){
      Coffee coffeeEntity = dto.toEntity();
      Coffee target = coffeeRepository.findById(id).orElse(null);

      if(target == null || !id.equals(coffeeEntity.getId())){
          return null;
      }
      target.patch(coffeeEntity);
      return coffeeRepository.save(target);
    }

    public Coffee delete(Long id){
        Coffee target = coffeeRepository.findById(id).orElse(null);
        if(target == null){
            return null;
        }
        return target;
    }

}
