package com.example.discussion_forum.dto;


import com.example.discussion_forum.entity.Member;
import lombok.AllArgsConstructor;
import lombok.ToString;


@ToString
@AllArgsConstructor
// MemberForm (DTO): 사용자가 웹 브라우저에서 form데이터에 입력 email, password를 가져오는 클라스
public class MemberForm {
    private Long id;
    private String email;
    private String password;



    // DTO의 데이터를 entityå로 만들기. Entity객체는 다른 파일에서 만들어야한다.
    public Member toEntity() {
        return new Member(id, email, password);
    }

}
