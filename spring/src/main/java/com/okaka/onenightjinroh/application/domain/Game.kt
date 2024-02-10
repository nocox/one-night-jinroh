package com.okaka.onenightjinroh.application.domain

class Game(
    var gameId: Long?,
    var roomId: Long?,
    var rule: Rule?,
    var term: GameTerm? = GameTerm.NIGHT,
) {
    companion object {
        fun createByRoomId(roomId: Long): Game {
            return Game(null, roomId, null, null)
        }

        fun singleCreate(gameId: Long): Game {
            return Game(gameId, null, null, null)
        }

        fun startGame(roomId: Long, participantCount: Int): Game {
            return Game(
                null,
                roomId,
                Rule.createByParticipantCount(participantCount),
                term = GameTerm.NIGHT
            )
        }
    }

    fun changeTerm(term: GameTerm): Game {
        return Game(
            this.gameId,
            this.roomId,
            this.rule,
            term
        )
    }

}
