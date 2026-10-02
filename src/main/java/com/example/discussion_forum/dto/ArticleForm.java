package com.example.discussion_forum.dto;

import com.example.discussion_forum.entity.Article;


//ArticleForm (DTO): 사용자가 웹 브라우저에서 입력한 title과 content를 받아오는 임시 바구니입니다. 화면과의 소통만을 위해 존재
public class ArticleForm {
    private String title;
    private String content;

    //  전송받은 제목과 내용을 필드에 저장하는 생성자 추가
    public ArticleForm(String title, String content) {
        this.title = title;
        this.content = content;
    }

   //  데이터를 잘 받았는지 확인할 toString() 메서드 추가
    @Override
    public String toString() {
        return "ArticleForm{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
    // 바구니(ArticleForm)에 담긴 데이터만으로는 데이터베이스에 바로 저장할 수 없습니다.
    // 따라서 바구니 안의 내용물(title, content)을 꺼내서 DB 저장용 객체인 Article로 새로 포장(생성)해 주어야 하는 것입니다.
    public Article toEntity() {
        return new Article(null, title, content);
    }
}
