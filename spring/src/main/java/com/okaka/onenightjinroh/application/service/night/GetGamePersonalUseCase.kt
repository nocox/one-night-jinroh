package com.okaka.onenightjinroh.application.service.night

import com.okaka.onenightjinroh.application.repository.GameParticipantRepository
import com.okaka.onenightjinroh.application.repository.GameRepository
import com.okaka.onenightjinroh.application.validater.ExistRoomValidate
import org.springframework.stereotype.Service

@Service
class GetGamePersonalUseCase(
    private val existRoomValidate: ExistRoomValidate,
    private val gameRepository: GameRepository,
    private val gameParticipantRepository: GameParticipantRepository,
) {
    fun get(gameId: Long, uuid: String, userId: Long): Long {
        // gameIdが存在することを確認
        val game = gameRepository.find(gameId) ?: throw IllegalArgumentException()

        // 参加ルームが正しいことを確認。
        val room = existRoomValidate.existRoom(uuid).orElseThrow { IllegalArgumentException() }
        if(game.roomId != room.roomId) throw IllegalArgumentException()


        val gameParticipantId = gameParticipantRepository.findIdByGameIdAndUserId(gameId, userId)

        return gameParticipantId
    }

}
