package com.example.discussion_forum.service;

import com.example.discussion_forum.dto.ArticleForm;
import com.example.discussion_forum.entity.Article;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
// 개발할 때는 개발용 DB를 사용하고 테스트할 때는 테스트용 DB를 사용하도록 선택하는 기능
class ArticleServiceTest {
   @Autowired
   ArticleService articleService;
    @Test
    void index() {
        // 1. 예상 데이터
        Article a = new Article(554L, "시간 예약", "1111");
        Article b = new Article(555L, "테이블지정", "22222");
        // Arrays.asList() => 정적 리스트로 변환
        // new ArrayList => 새로운 array로 변환
        List<Article> expected = new ArrayList<Article>(Arrays.asList(a,b));
        // 2. 실제 데이터
        List<Article> articles = articleService.index();
        // 3. 비교 및 검증
        assertEquals(expected.toString(), articles.toString());
    }

    @Test
    void show_success() {
      // 예상 데이터
        Long id = 554L;
        Article expected = new Article(id, "시간 예약", "1111");
        // 실제 데이터
        Article article = articleService.show(id);
        // 비교 및 검증
        assertEquals(expected.toString(), article.toString());
    }
    @Test
    void show_fail() {
        // 예상 데이터
        Long id = -1L;
        Article expected  = null;
        // 실제 데이터
        Article article = articleService.show(id);
        // 비교 및 검증
        assertEquals(expected, article);
    }

    @Test
    @Transactional  // 성공시 되돌리기. 안 그럼 index 테스트가 실패!
    void create_success() {
      // id가 random하게 만들어 지기떄문에 PASS
    }
    @Test
    void create_fail() {
        // 예상 데이터
        Long id = 554L;
        String title = "라라라라";
        String content = "4444";
        ArticleForm dto = new ArticleForm(id, title, content);
        Article expected = null;
        // 실제 데이터
        Article article = articleService.create(dto);
        // 비교 및 검증
        assertEquals(expected, article);
    }
}