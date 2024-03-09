package com.okaka.onenightjinroh.application.service.tally

import com.okaka.onenightjinroh.application.domain.GameTerm
import com.okaka.onenightjinroh.application.repository.GameRepository
import org.springframework.stereotype.Service

@Service
class ShowResultUseCase(
    private val gameRepository: GameRepository,
) {
    fun invoke(gameId: Long) {
        val game = gameRepository.find(gameId)
        val changedGame = game.changeTerm(GameTerm.RESULT)
        gameRepository.save(changedGame)
    }
}