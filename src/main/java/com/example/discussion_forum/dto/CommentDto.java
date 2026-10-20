package com.example.discussion_forum.dto;

import com.example.discussion_forum.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString()
public class CommentDto {
    private Long id;
    private Long articleId;
    private String nickname;
    private String body;

    // public static: 객체 생성없이 호출 가능한 메서드
    public static CommentDto createCommentDto(Comment comment) {
       return new CommentDto(
               comment.getId(),
               comment.getArticle().getId(),
               comment.getNickname(),
               comment.getBody()
       );
    }
}
