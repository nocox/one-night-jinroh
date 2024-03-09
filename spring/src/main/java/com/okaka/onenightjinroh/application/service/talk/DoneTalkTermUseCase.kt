package com.okaka.onenightjinroh.application.service.talk

import com.okaka.onenightjinroh.application.domain.GameTerm
import com.okaka.onenightjinroh.application.repository.GameRepository
import org.springframework.stereotype.Service

@Service
class DoneTalkTermUseCase(
    private val gameRepository: GameRepository,
) {
    fun done(gameId: Long) {
        val game = gameRepository.find(gameId)
        val changedGame = game.changeTerm(GameTerm.VOTE)
        gameRepository.save(changedGame)
    }
}