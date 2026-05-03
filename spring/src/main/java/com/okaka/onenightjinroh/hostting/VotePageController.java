package com.okaka.onenightjinroh.hostting;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class VotePageController {

    @GetMapping(path = "/vote")
    public String topPageHosting(Model model) {
        return "vote_page";
    }
}