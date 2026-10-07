package com.example.discussion_forum.dto;


import com.example.discussion_forum.entity.Member;

// MemberForm (DTO): 사용자가 웹 브라우저에서 form데이터에 입력 email, password를 가져오는 클라스
public class MemberForm {
    private String email;
    private String password;

    public MemberForm(String email, String password){
        this.email = email;
        this.password = password;
    }

    @Override
    public String toString() {
        return "MemberForm{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    // DTO의 데이터를 entity로 만들기. Entity객체는 다른 파일에서 만들어야한다.
    public Member toEntity() {
        
        return new Member(null, email, password);
    }

}
