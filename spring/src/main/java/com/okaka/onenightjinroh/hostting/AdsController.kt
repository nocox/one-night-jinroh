package com.okaka.onenightjinroh.hostting

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping

@Controller
class AdsController {
    @GetMapping(path = ["/ads.txt"])
    fun topPageHosting(model: Model?): String {
        return "ads"
    }
}