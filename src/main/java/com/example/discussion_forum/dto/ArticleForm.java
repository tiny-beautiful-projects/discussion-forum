package com.example.discussion_forum.dto;

import com.example.discussion_forum.entity.Article;
import lombok.AllArgsConstructor;
import lombok.ToString;


//ArticleForm (DTO): 사용자가 웹 브라우저에서 입력한 title과 content를 받아오는 임시 바구니입니다. 화면과의 소통만을 위해 존재
@AllArgsConstructor
/**
 *  클래스에 선언된 모든 필드를 매개변수로 받는 생성자를 자동으로 만들어주는 역할
 */
@ToString
/**
 * 클래스의 모든 필드를 조합하여 toString() 메서드를 자동으로 만들어주는 역할
 */
public class ArticleForm {
    private Long id;
    private String title;
    private String content;

    // 바구니(ArticleForm)에 담긴 데이터만으로는 데이터베이스에 바로 저장할 수 없습니다.
    // 따라서 바구니 안의 내용물(title, content)을 꺼내서 DB 저장용 객체인 Article로 새로 포장(생성)해 주어야 하는 것입니다.
    public Article toEntity() {
        return new Article(id, title, content);
    }
}
