package com.okaka.onenightjinroh.hostting;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GamePageController {

    @GetMapping(path = "/game")
    public String topPageHosting(Model model) {
        return "game_page";
    }
}