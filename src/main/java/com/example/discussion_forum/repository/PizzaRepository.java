package com.example.discussion_forum.repository;

import com.example.discussion_forum.entity.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PizzaRepository extends JpaRepository<Pizza, Long> {
    
}
