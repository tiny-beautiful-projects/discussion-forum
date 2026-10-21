// DTO: 오직 클라이언트(웹 브라우저의 폼 등)와 데이터를 주고받기 위한 전송 전용 객체

package com.example.discussion_forum.controller;


import com.example.discussion_forum.dto.ArticleForm;
import com.example.discussion_forum.dto.CommentDto;
import com.example.discussion_forum.entity.Article;
import com.example.discussion_forum.repository.ArticleRepository;
import com.example.discussion_forum.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;


// @Slf4j는 Simple Logging Facade for Java의 약자
@Slf4j
// 이 클래스가 스프링 MVC에서 웹 요청을 받아 처리하는 컨트롤러 역할
// 프로그램 실행 중에 기록(로그)을 남길 수 있는 로거(Logger) 객체를 자동으로 생성해 주는 역할
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
    @Autowired
    private CommentService commentService;

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
        log.info(form.toString());
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
        log.info(saved.toString());
        return "redirect:/articles/" + saved.getId();
    }



    @GetMapping("/articles/{id}")
    // @PathVariabl: URL 경로(Path)에 포함된 변수 값을 메서드의 매개변수로 받아올 때 사용하는 어노테이션
    public String show(@PathVariable Long id, Model model){
        log.info("articles id = " + id);
        // id를 조회해 데이터 가져오기
        Article articleEntity = articleRepository.findById(id).orElse(null);
        // id에 해당하는 댓글 가져오기
        List<CommentDto> commentsDtos = commentService.comments(id);
        // 모델에 데이터 등록하기'
        model.addAttribute("article", articleEntity);
        model.addAttribute("commentDtos", commentsDtos);
        // 3. 뷰 페이지 반환하기
        return "articles/show";
    }


    @GetMapping("/articles")
    public String index(Model model){
        // 1. 모든 데이터 가져오기
        // findAll 메서드가 Iterable이 아닌 ArrayList를 반환하도록 수정.
         List<Article> articleEntityList = articleRepository.findAll();;
        // 2. 모델에 데이터 등록하기
        model.addAttribute("articleList", articleEntityList);
        // 3. 뷰페이지 반환하기
        return "articles/index";
    }

    @GetMapping("/articles/{id}/edit")
    public String edit(@PathVariable Long id, Model model){
        // 수정할 데이터 가져오기
        Article articleEntity = articleRepository.findById(id).orElse(null);
        // 모델에 데이터 등록하기
        model.addAttribute("article", articleEntity);

        return "articles/edit";
    }

    @PostMapping("articles/update")
    // dto를 매개변수로 받아온다.
    public String update(ArticleForm form){
          log.info("edit DTO 받아오기" + form.toString());
          // 1. DTO를 엔티티로 변환하기
          Article articleEntity = form.toEntity();
          log.info(articleEntity.toString());
          // 2. 엔티티를 DB에 저장하기
          // 2-1 DB에서 기존 데이터 가져오기
          Article target = articleRepository.findById(articleEntity.getId()).orElse(null);
          if(target != null){
              articleRepository.save(articleEntity); // 엔티티를 DB에 저장
          }
          // 3. 수정 결과 페이지로 리다이렉트 하기
          return "redirect:/articles/" + articleEntity.getId();
    }

    // 삭제 완료 메시지 남기기
    /**
     *  RedirectAttributes 객체의 addFlashAttribute() 메서드를 활용하며 리다이렉트 시점에 한 번만 사용할 데이터를 등록할 수 있다.
     *  객체명.addFlashAttribute(넘겨주려는 키 문자열, 넘겨 주려는 값 객체);
     */
    @GetMapping("/articles/{id}/delete")
    public String delete(@PathVariable Long id,  RedirectAttributes rttr){
        log.info("삭제 요청이 들어왔습니다.");
        // 1. 삭제할 대상 가져오기
        Article target = articleRepository.findById(id).orElse(null);
        // 2. 대상 엔티티 삭제하기
        log.info(target.toString());
        if(target != null){
            articleRepository.delete(target);
            rttr.addFlashAttribute("msg", "삭제됐습니다.");
        }
        // 3. 결과 페이지로 리다이렉트로
        return "redirect:/articles";
    }

}
