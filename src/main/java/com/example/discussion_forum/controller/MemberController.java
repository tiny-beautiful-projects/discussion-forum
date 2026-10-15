package com.example.discussion_forum.controller;


import com.example.discussion_forum.dto.MemberForm;
import com.example.discussion_forum.entity.Member;
import com.example.discussion_forum.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;


@Slf4j
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
        // DTO 를 통해서 사용자가 입력한 값 가져 ㅇ오기.
        log.info(memberForm.toString());
        // DTO를 entity로 만들기
        /**
         * 택배 상자(DTO)에 들어있던 물건을 꺼내서, 우리 집 거실(메모리)에 똑같은 모양의 가구(Entity)를 새로 조립해서 놓아둔 상태입니다.
         * 아직 창고(데이터베이스)에 가져다 넣은 게 아니기 때문에, 이 가구에는 아직 관리 번호(ID)가 부여되지 않았습니다.
         */
        Member member = memberForm.toEntity();
        log.info(member.toString());
        Member saved = memberRepository.save(member);
        log.info(saved.toString());

        return "redirect:/members/" + saved.getId();
    }
    // 회원 리스트 조회
    @GetMapping("/members")
    public String index(Model model){
        // 1. repository에서 데이터 가져오기
        List<Member> memberList =  memberRepository.findAll();
        // 2. 모델에 데이터 저장하기
        model.addAttribute("memberList", memberList);
        // 3.뷰 반환
        return "/members/index";
    }
    // 회원 조회
    @GetMapping("/members/{id}")
    public String show(@PathVariable Long id, Model model){
        // 1. repository에서 데이터 가져오기
        Member member = memberRepository.findById(id).orElse(null);
        // 2. 모델에 데이터 저장하기
        model.addAttribute("member", member);
        // 3. 뷰 반환
        return "/members/show";
    }

    // 회원 수정 return
    @GetMapping("/members/{id}/edit")
    public String edit(@PathVariable Long id, Model model){
        // 1. DB에서 수정할 데이터를 가져옴
        Member memberEntity = memberRepository.findById(id).orElse(null);
        // 2. 모델에 데이터 등록 (이때 이름이 "article"이 됨)
        // Model은 컨트롤러와 뷰(HTML) 사이의 '택배 상자'입니다.
        // 컨트롤러가 DB에서 가져온 articleEntity 객체를 그냥 HTML에 툭 던져줄 수는 없습니다.
        // 그래서 Model이라는 임시 상자에 데이터를 쏙 집어넣고 이름을 붙여서("article") HTML 쪽으로 건네주는 것입니다.
        model.addAttribute("member", memberEntity);

        // 3. 수정 페이지 뷰 파일로 리턴 (예: articles/edit.mustache)
        return "/members/edit";
    }

    // 회원 수정 업데이트 함수
    @PostMapping("/members/update")
    public String update(MemberForm memberForm){
        log.info("member DTO ===>" + memberForm.toString());
        // 1. user의 DTO를 entity 로 변경한다.
        Member memberEntity = memberForm.toEntity();
        // 2. 해당 entity가 db에 있는지 확인한다. 없으면 null처리
        Member target = memberRepository.findById(memberEntity.getId()).orElse(null);
        // 3. entity가 있으면은 update!
        if(target != null){
            memberRepository.save(memberEntity); // entity를 DB에 저장
        }
        return "redirect:/members" + memberEntity.getId();
    }


    // 회원 삭제
    @GetMapping("/members/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes rttr){
        // 1. id로 user를 찾는다. 해당 entity가 db에 있는지 확인하다. 없으면 null 처리
         Member target = memberRepository.findById(id).orElse(null);
        // 2.  entity가 있으면은 delete
        if(target != null){
            memberRepository.delete(target);
            rttr.addFlashAttribute("msg", "멤버가 삭제되었습니다.");
        }
        return "redirect:/members";
    }

}
