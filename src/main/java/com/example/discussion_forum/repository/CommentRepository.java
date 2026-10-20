package com.example.discussion_forum.repository;

import com.example.discussion_forum.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

// Comment Entity를 관리 하므로 대상 엔티티에는 Comment, 대표키 값에는 id타입인 Long.
public interface CommentRepository extends JpaRepository<Comment, Long> {
    // 네이티브 쿼리 메서드:데이터베이스가 이해하는 순수 SQL(Native SQL)을 직접 작성하여 사용하는 리포지토리 메서드
    /**
     * 1. @query annotation  사용
     * 2. orm.xml 파일 이용
     */
    // 특정 게시글의 모든 댓글 조회 : @Query 어노테이션
    @Query(value = "SELECT * FROM comment WHERE article_id = :articleId" , nativeQuery = true)
    List<Comment> findByArticleId(Long articleId);
    // 특정 닉네임의 모든 댓글 조회 : orm.xml 이용
    List<Comment> findByNickname(String nickname);

}
