package com.example.discussion_forum.service;

import com.example.discussion_forum.dto.CommentDto;
import com.example.discussion_forum.entity.Article;
import com.example.discussion_forum.entity.Comment;
import com.example.discussion_forum.repository.ArticleRepository;
import com.example.discussion_forum.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private ArticleRepository articleRepository;
    public List<CommentDto> comments(Long articleId) {
        // 댓글 조회
        return commentRepository.findByArticleId(articleId).stream().map(comment -> CommentDto.createCommentDto(comment)).collect(Collectors.toList());
    }

    @Transactional // 문제가 발생시 롤백하기.
    public CommentDto create(Long articleId, CommentDto dto) {
        // 게시글 조회 및 에러 발생
        Article article = articleRepository.findById(articleId).orElseThrow(()-> new IllegalArgumentException("댓글 생성 실패" + "대상 게시글이 없습니다."));
        // 댓글 엔티티 생성
        Comment comment = Comment.createComment(dto, article);
        // 댓글 엔티티를 db에 저장
        Comment created = commentRepository.save(comment);
        // dto로 변환해 반환
        return CommentDto.createCommentDto(created);
    }
    @Transactional
    public CommentDto update(Long id, CommentDto dto) {
        // 댓글 조회 및 예외 발생
        Comment target = commentRepository.findById(id).orElseThrow(()->new IllegalArgumentException("댓글 수정 실패 " + "대상 댓글이 없습니다."));
        // 댓글 수정
        target.patch(dto);
        // db로 갱신
        Comment updated = commentRepository.save(target);
        // 댓글 엔티티를 DTO로 변환 및 반환
        return CommentDto.createCommentDto(updated);
    }


    @Transactional
    public CommentDto delete(Long id) {
        // 댓글 조회 및 예외 발생
        Comment target = commentRepository.findById(id).orElseThrow(()->new IllegalArgumentException("댓글 삭제 실패" + "대상이 없습니다."));
        // 댓글 삭제
        commentRepository.delete(target);
        // 삭제 댓글을 DTO로 변환 및 반환
        return CommentDto.createCommentDto(target);
    }
}
