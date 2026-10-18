package com.example.discussion_forum.api;


import com.example.discussion_forum.dto.ArticleForm;
import com.example.discussion_forum.entity.Article;
import com.example.discussion_forum.repository.ArticleRepository;
import com.example.discussion_forum.service.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class ArticleApiController {
    @Autowired
    private ArticleService articleService;
    @Autowired
    private ArticleRepository articleRepository;

    @GetMapping("/api/articles")
    public List<Article> index() {
        return articleService.index();
    }


    @GetMapping("/api/articles/{id}")
    public ResponseEntity<Article> show(@PathVariable Long id){
        Article article = articleService.show(id);
        if(article == null){
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(article);
    }

    @PostMapping("/api/articles")
    public ResponseEntity<Article> create(@RequestBody ArticleForm dto) {
        Article created = articleService.create(dto);
        return (created != null) ? ResponseEntity.status(HttpStatus.OK).body(created) : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @PatchMapping("/api/articles/{id}")
    public ResponseEntity<Article> update(@PathVariable Long id, @RequestBody ArticleForm dto){
        Article updated = articleService.update(id, dto);
        return (updated !=null) ? ResponseEntity.status(HttpStatus.OK).body(updated): ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
//
//    // DELETE
//    @DeleteMapping("/api/articles/{id}")
//    public ResponseEntity<Article> delete(@PathVariable Long id){
//        // 대상 찾기
//        Article target = articleRepository.findById(id).orElse(null);
//        // 잘못된 요청 처리하기
//        if(target == null){
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
//        }
//        // 대상 삭제 하기
//        articleRepository.delete(target);
//        return ResponseEntity.status(HttpStatus.OK).body(null);
//    }


}
