// 데이터베이스 테이블과 1:1로 매핑되는 핵심 객체입니다. DB와 밀접하게 연관되어 있어 비즈니스 로직의 핵심 상태를 표현
package com.example.discussion_forum.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;


@NoArgsConstructor // 기본 생성자 추가 어노테이션
@AllArgsConstructor
@ToString
@Getter
@Entity
public class Article {
    @Id // 엔티티의 대표값
    @GeneratedValue // 자동 생성
    private Long id;
    @Column // title column
    private String title;
    @Column // content column
    private String content;

    public void patch(Article article) {
        if(article.title != null){
            this.title = article.title;
        }
        if(article.content != null){
            this.content = article.content;
        }
    }
}
