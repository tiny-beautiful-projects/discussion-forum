package com.example.discussion_forum.entity;


import com.example.discussion_forum.dto.CommentDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    @Id // 대표키 선언
    @GeneratedValue(strategy= GenerationType.IDENTITY) // db가 자동으로 1씩 증가
    private Long id; // 대표키
    @ManyToOne // Comment entity와 Article entity를 다대일 관계로 설정
    @JoinColumn(name="article_id") // 외래키 생성, Article 엔티티의 기본키(id)와 매핑
    private Article article; // 해당 댓글의 부모 게시글
    @Column
    private String nickname; // 댓글을 단 사람
    @Column
    private String body; // 댓글 본문


    public static Comment createComment(CommentDto dto, Article article){
        // 예외 발생
        if(dto.getId() != null) throw new IllegalArgumentException("댓글 생성 실패. 댓글의 ID가 없어야 합니다.");
        // 엔티티 생성 및 변환
        if(dto.getArticleId() != article.getId()) throw new IllegalArgumentException("댓글 생성 실패. 게시글의 ID가 잘못됐습니다.");
        return new Comment(dto.getId(), article, dto.getNickname(), dto.getBody());
    }

    public void patch(CommentDto dto) {
        if(this.id != dto.getId()) throw new IllegalArgumentException("댓글 수정 실패. 잘못된 ID가 입력됐습니다.");
        // 객체 갱신
        if(dto.getNickname() !=null){
            this.nickname = dto.getNickname();
        }
        if(dto.getBody() != null){
            this.body = dto.getBody();
        }
    }
}
