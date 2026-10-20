package com.example.discussion_forum.service;


import com.example.discussion_forum.dto.CommentDto;
import com.example.discussion_forum.entity.Comment;
import com.example.discussion_forum.repository.ArticleRepository;
import com.example.discussion_forum.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private ArticleRepository articleRepository;


    public List<CommentDto> comments(Long articleId) {
        // 댓글 조회
        List<Comment> comments = commentRepository.findByArticleId(articleId);
        // 엔티티 -> DTO 변환
        List<CommentDto> dtos = new ArrayList<CommentDto>();
        for(int i=0; i< comments.size(); i++){
            Comment c = comments.get(i);
            CommentDto dto = CommentDto.createCommentDto(c);
            dtos.add(dto);
        }
        // 결과 반환
        return dtos;
    }
}
