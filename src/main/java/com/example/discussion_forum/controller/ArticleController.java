package com.example.discussion_forum.controller;


import com.example.discussion_forum.dto.ArticleForm;
import com.example.discussion_forum.entity.Article;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ArticleController {
    @GetMapping("/articles/new")
    public String newArticleForm(){
        return "articles/new";
    }

    @PostMapping("/articles/create")
   // form 데이터를 dto로 받아오기
    public String createArticle(ArticleForm form){
        System.out.println(form.toString());
        // 1. DTO를 엔티티로 변환
        Article article = form.toEntity();
        // 2. 리파지터리로 엔티티를 DB에 저장
        return "";
    }
}
