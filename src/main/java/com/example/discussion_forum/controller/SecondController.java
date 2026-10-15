package com.example.discussion_forum.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class SecondController {
    @GetMapping("/quote")
    public String randomQuote(Model model){
        String[] quotes = {
                "행복은 습관이다. 그것을 몸에 지녀라" + "-허버드-",
                "고개숙이지 마십시오. 세상을 똑바로 정면으로" + "-헬렌켈러-",
                "당신이 할 수 있다고 믿든 할 수 없다고 믿든 믿는대로 될 것이다" + "-헨리 포드-"
        };
        int randInt = (int) (Math.random() + quotes.length-1);
        model.addAttribute("randomQuote", quotes[randInt]);
        return "quote";
    }
}
