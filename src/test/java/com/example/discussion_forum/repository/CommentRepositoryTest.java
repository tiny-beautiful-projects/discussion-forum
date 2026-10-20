package com.example.discussion_forum.repository;

import com.example.discussion_forum.entity.Article;
import com.example.discussion_forum.entity.Comment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


// repository 테스트를 할때 사용하는 annotation

@DataJpaTest
// 기존 DB를 사용하려고 할떄 하는 설정
@AutoConfigureTestDatabase(
        replace = AutoConfigureTestDatabase.Replace.NONE
)
class CommentRepositoryTest {
    @Autowired
    CommentRepository commentRepository;

    @Test
    @DisplayName("특정 게시글의 모든 댓글 조회")
    void findByArticleId() {
        // case 1. 1번 게시글의 모든 댓글 조회 (있는 데이터)
        // 입력 데이터 준비
        Long articleId = 1L;
        // 실제 데이터
        List<Comment> comments = commentRepository.findByArticleId(articleId);
        // 예상 데이터
        Article article = new Article(
                1L,
                "당신의 인생 영화",
                "살면서 가장 깊은 울림을 주었던 영화는 무엇인가요? 인생 영화와 그 이유를 댓글로 나눠주세요."
        );        List<Comment> expected = Arrays.asList(
                new Comment(1L, article, "park", "굿 윌 헌팅 정말 명작이죠! 인생 영화로 손꼽을 만합니다."),
                new Comment(2L, article, "lee", "저는 이 영화 보고 눈물 많이 흘렸어요."),
                new Comment(3L, article, "kim", "따뜻한 위로가 되는 영화라 주기적으로 재탕합니다.")
        );
        // 비교 및 검증
        assertEquals(expected.toString(), comments.toString());

        // case 2.  100번 게시글의 모든 댓글 조회 (없는 데이터)
        // 1. 입력 데이터
        Long articleId2 = 100L;
        // 2. 실제 데이터
        List<Comment> comments2 = commentRepository.findByArticleId(articleId2);
        // 3. 예상 데이터
        List<Comment> expected2 = List.of();
        assertEquals(expected2, comments2);

    }
    @Test
    void findByNickname() {
    }
}