package com.okaka.onenightjinroh.hostting;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TalkPageController {

    @GetMapping(path = "/talk")
    public String topPageHosting(Model model) {
        return "talk_page";
    }
}