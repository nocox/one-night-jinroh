package com.okaka.onenightjinroh.application.repository

import com.okaka.onenightjinroh.application.domain.Game

interface GameRepository {
    fun find(gameId: Long): Game?
    fun findByRoomId(roomId: Long): List<Game>
    fun save(game: Game)
}