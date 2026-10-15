package com.example.discussion_forum.repository;

import com.example.discussion_forum.entity.Article;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

/**
 *  CrudRepository 는 JPA에서 제공하는 인터페이스. 이를 상속해 엔티티를 관리(생성, 조회, 수정, 삭제) 할 수 있다.
 *  <Aricle, Long> => 1. Article: 관리 대상 엔티티의 클래스 타입. 2. 관리 대상 엔티티의 대푯값 타입.  Article.java 파일에 가 보면 id가 대표값.
 *
 */
public interface ArticleRepository extends CrudRepository<Article, Long> {

    // findAll 메서드를 iterable이 아닌, ArrayList로 반환하는 오버라이드
    @Override
    ArrayList<Article> findAll();

}
