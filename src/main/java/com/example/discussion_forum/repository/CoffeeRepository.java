package com.example.discussion_forum.repository;

import com.example.discussion_forum.entity.Coffee;
import org.springframework.data.repository.CrudRepository;


/**
 *  *  <Aricle, Long> => 1. Article: 관리 대상 엔티티의 클래스 타입. 2. 관리 대상 엔티티의 대푯값 타입.  Article.java 파일에 가 보면 id가 대표값.
 */
public interface CoffeeRepository extends CrudRepository<Coffee, Long> {
}
