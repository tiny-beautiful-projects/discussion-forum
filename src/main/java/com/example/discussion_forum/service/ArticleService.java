package com.example.discussion_forum.service;


import com.example.discussion_forum.dto.ArticleForm;
import com.example.discussion_forum.entity.Article;
import com.example.discussion_forum.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService {
   @Autowired
    private ArticleRepository articleRepository;

    public List<Article> index() {
        return articleRepository.findAll();
    }

    public Article show(Long id) {
       return articleRepository.findById(id).orElse(null);
    }

    public Article create(ArticleForm dto) { 
        Article article = dto.toEntity();
        if(article.getId() != null){
            return null;
        }
        return articleRepository.save(article);
    }

    public Article update(Long id, ArticleForm dto) {
        // DTO를 entity 로 변환하기
        Article article = dto.toEntity();
        // 타겟 조회하기
        Article target = articleRepository.findById(id).orElse(null);

        if(target == null || id != article.getId()){
            return  null;
        }
        target.patch(article);
        Article updated = articleRepository.save(target);
        return updated;

    }
}

