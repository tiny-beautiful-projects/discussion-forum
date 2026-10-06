package com.example.discussion_forum.controller;


import com.example.discussion_forum.dto.ArticleForm;
import com.example.discussion_forum.entity.Article;
import com.example.discussion_forum.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

// 이 클래스가 스프링 MVC에서 웹 요청을 받아 처리하는 컨트롤러 역할
@Controller
public class ArticleController {
    /**
     * 리파지터리(ArticleRepository): 데이터베이스와 소통하며 엔티티를 관리하는 JPA 인터페이스
     *
     * 스프링 프레임워크에서 의존성 주입(Dependency Injection, DI)을 자동으로 처리해 주는 어노테이션
     * "스프링아, 이 객체에 필요한 부품(여기서는 ArticleRepository)을 알아서 찾아서 꽂아(연결해) 줘!"라고 지시하는 명령어'
     * 개발자가 직접 new ArticleRepository()처럼 객체를 직접 생성할 필요가 없습니다. 스프링 컨테이너가 미리 생성해 둔(관리하고 있는) 객체를 가져다가 해당 변수에 자동으로 연결
     *
     */
    @Autowired
    private ArticleRepository articleRepository;

    /**
     * 사용자가 브라우저 주소창에 /articles/new 경로로 접속(GET 요청)했을 때, 글을 작성할 수 있는 입력 폼 페이지(articles/new 뷰)를 보여줍니다.
     */
    @GetMapping("/articles/new")
    public String newArticleForm(){
        return "articles/new";
    }

    /**
     * 사용자가 폼에 내용을 채우고 '작성 완료' 버튼을 눌렀을 때(POST 요청), 데이터가 전송되어 처리되는 주소
     */
    @PostMapping("/articles/create")

    /**
     *  form 데이터를 dto로 받아오기 (ArticleForm form)
     * 클라이언트(브라우저)가 보낸 폼 데이터(예: 제목, 내용 등)를 ArticleForm이라는 DTO(Data Transfer Object, 데이터 전송 객체) 객체로 자동 바인딩(수집)
     */
    public String createArticle(ArticleForm form){
        System.out.println(form.toString());
        // 1. DTO를 엔티티로 변환
        /**
         * 웹 계층에서 사용하는 객체(ArticleForm)를 데이터베이스와 직접 매핑되는 핵심 객체인 엔티티(Article)로 변환합니다.
         * 데이터베이스에 저장하려면 엔티티 형태로 바꿔야 하기 때문
         */
        Article article = form.toEntity();
        // 2. 리파지터리로 엔티티를 DB에 저장
        /**
         * save(article) 메서드를 호출하여 변환된 엔티티 객체를 실제 데이터베이스에 저장하고, 저장된 결과 객체를 saved 변수에 담습니다.
         */
        Article saved = articleRepository.save(article);
        System.out.println(saved.toString());
        return "";
    }
}
