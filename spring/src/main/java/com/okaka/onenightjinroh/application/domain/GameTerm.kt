package com.okaka.onenightjinroh.application.domain

enum class GameTerm(val code: String) {
    NIGHT(code = "night"),
    TALK(code = "talk"),
    VOTE(code = "vote"),
    TALLY(code = "tally"),
    RESULT(code = "result")
}
