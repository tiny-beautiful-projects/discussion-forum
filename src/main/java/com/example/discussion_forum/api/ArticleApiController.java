package com.example.discussion_forum.api;


import com.example.discussion_forum.dto.ArticleForm;
import com.example.discussion_forum.entity.Article;
import com.example.discussion_forum.repository.ArticleRepository;
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
    private ArticleRepository articleRepository;
    // GET
    @GetMapping("/api/articles")
    public List<Article> index(){
        return  articleRepository.findAll();
    }

    // GET
    @GetMapping("/api/articles/{id}")
    public Article show(@PathVariable Long id){
        return articleRepository.findById(id).orElse(null);
    }

    // POST
    @PostMapping("/api/articles")
    public Article create(@RequestBody ArticleForm dto){
        Article article = dto.toEntity();
        return articleRepository.save(article);
    }

    // PATCH
    @PatchMapping("/api/articles/{id}")
    public ResponseEntity<Article> update(@PathVariable Long id, @RequestBody ArticleForm dto){
        // 1. 수정용 앤티티 생성
        Article article = dto.toEntity();
        // 2. 타켓 조회
        Article target = articleRepository.findById(id).orElse(null);
        // 3. 잘못된 요청 처리
        /**
         * 클라이언트가 요청을 보낼 때 URL의 {id}와 JSON Body 내부의 ID가 서로 일치하는지(id != article.getId() 또는 id != dto.getId()) 검증하기 위한 용도
         * 만약 사용자가 URL로는 1번을 요청해놓고 Body에는 2번을 보냈다면 이 조건문에 걸려 잘못된 요청으로 처리
         */
        log.info("요청 id: {}", id);
        log.info("조회 결과 target: {}", target);
        log.info("요청 article id: {}", article.getId());
        if(target == null || !id.equals(article.getId())){
            log.info("잘못된 요청 id: {}, article: {}", id, article.toString());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        // 4. 업데이트 및 정상 응답
        /**
         * 만약 기존 데이터(target)에 새 데이터(article)를 덮어씌우지 않고,
         * 클라이언트가 보낸 article을 곧바로 articleRepository.save(article) 해버리면 어떤 문제가 생길까요?
         *  1. 부분 수정(PATCH)의 의미 상실:
         *   PATCH 요청은 보통 "수정할 필드만 일부 보내서 바꾸는 것"입니다. 예를 들어 제목만 바꾸고 내용은 그대로 두고 싶을 때,
         *   클라이언트가 제목만 담아서 보낼 수 있습니다.
         *   이때 article 객체에는 제목만 들어있고 내용(content)은 null일 수 있습니다.
         *   만약 article을 통째로 save 해버리면, DB에 있던 기존 내용까지 null로 덮어씌워져서 내용이 날아가는 참사가
         *
         *   2. 안전한 값 병합 (target.patch(article)):
         *   그래서 먼저 DB에서 기존 데이터(target)를 가져옵니다.
         *   target.patch(article) 메서드는 "기존 데이터(target) 중 클라이언트가 수정을 요청한 값(article)만 골라서 바꿔치기 해라" 라는 뜻입니다.
         *   (보통 엔티티 내부에 patch 메서드가 정의되어 있습니다.)
         *
         *   결과적으로 target에는 "기본 정보는 유지되면서, 사용자가 바꾼 부분만 업데이트된 최종 데이터"가 담기게 됩니다.
         */
        target.patch(article);
        Article updated = articleRepository.save(target);
        return ResponseEntity.status(HttpStatus.OK).body(updated);
    }

    // DELETE
    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Article> delete(@PathVariable Long id){
        // 대상 찾기
        Article target = articleRepository.findById(id).orElse(null);
        // 잘못된 요청 처리하기
        if(target == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        // 대상 삭제 하기
        articleRepository.delete(target);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

}
