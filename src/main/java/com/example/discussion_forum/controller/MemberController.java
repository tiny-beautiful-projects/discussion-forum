package com.example.discussion_forum.controller;


import com.example.discussion_forum.dto.MemberForm;
import com.example.discussion_forum.entity.Member;
import com.example.discussion_forum.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MemberController {

    @Autowired
    private MemberRepository memberRepository;

    @GetMapping("/members/new")
    public String MemberForm(){

        return "/members/new";
    }

    @PostMapping("/join")
    public String CreateForm(MemberForm memberForm){
        // DTO 를 통해서 사용자가 입력한 값 가져오기.
        System.out.println(memberForm.toString()); // {email='yunajoe@gmail.com', password='1234'}
        // DTO를 entity로 만들기
        /**
         * 택배 상자(DTO)에 들어있던 물건을 꺼내서, 우리 집 거실(메모리)에 똑같은 모양의 가구(Entity)를 새로 조립해서 놓아둔 상태입니다.
         * 아직 창고(데이터베이스)에 가져다 넣은 게 아니기 때문에, 이 가구에는 아직 관리 번호(ID)가 부여되지 않았습니다.
         */
        Member member = memberForm.toEntity();
        System.out.println(member.toString()); // Member{id=null, email='yunajoe@gmail.com', password='1234'}
        Member saved = memberRepository.save(member);
        System.out.println(saved.toString());
        return "";
    }
}
