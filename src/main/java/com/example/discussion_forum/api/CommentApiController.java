package com.example.discussion_forum.api;


import com.example.discussion_forum.dto.CommentDto;
import com.example.discussion_forum.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

// rest controller 선언
@RestController
public class CommentApiController {
    @Autowired
    private CommentService commentService; // 댓글 서비스 객체 선언
    // 댓글 조회
    @GetMapping("/api/articles/{articleId}/comments")
    public ResponseEntity<List<CommentDto>> comments(@PathVariable Long articleId){
        // 서비스에 위임
       List<CommentDto> dtos =  commentService.comments(articleId);
        // 결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(dtos);
    }
    // 댓글 생성
    // 댓글 수정
    // 댓글 삭제
}
