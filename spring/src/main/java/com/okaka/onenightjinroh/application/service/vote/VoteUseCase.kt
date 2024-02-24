package com.okaka.onenightjinroh.application.service.vote

import com.okaka.onenightjinroh.application.domain.GameParticipant
import com.okaka.onenightjinroh.application.domain.GameTerm
import com.okaka.onenightjinroh.application.domain.Vote
import com.okaka.onenightjinroh.application.repository.GameParticipantRepository
import com.okaka.onenightjinroh.application.repository.GameRepository
import com.okaka.onenightjinroh.application.repository.VoteRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.function.Function
import java.util.function.Predicate
import java.util.stream.Collectors


@Service
class VoteUseCase(
    private val voteRepository: VoteRepository,
    private val gameParticipantRepository: GameParticipantRepository,
    private val gameRepository: GameRepository,
) {

    sealed interface VoteStatus {
        object AllVoted : VoteStatus
        object NotAllVoted : VoteStatus
    }

    fun vote(gameId: Long, fromGameParticipantId: Long, toGameParticipantId: Long): VoteStatus {
        val vote = Vote(fromGameParticipantId, toGameParticipantId)
        voteRepository.createVote(vote)

        val voteStatus = isDoneAllVoted(gameId)

        if (voteStatus is VoteStatus.AllVoted) {
            doneAllVote(gameId)
        }

        return voteStatus
    }

    private fun doneAllVote(gameId: Long) {
        val game = gameRepository.find(gameId)
        val changedGame = game.changeTerm(GameTerm.TALLY)
        gameRepository.save(changedGame)
    }

    private fun isDoneAllVoted(gameId: Long): VoteStatus {
        val voteIds = voteRepository.findByGameId(gameId)
            .map { it.getGameParticipationId() }
        val isDoneAllVoted = gameParticipantRepository.findByGameId(gameId)
            .map { it.getGameParticipationId() }
            .all { voteIds.contains(it) }

        return if (isDoneAllVoted) {
            VoteStatus.AllVoted
        } else {
            VoteStatus.NotAllVoted
        }
    }
}
