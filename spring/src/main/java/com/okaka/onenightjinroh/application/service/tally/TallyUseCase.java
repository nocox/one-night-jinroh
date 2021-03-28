package com.okaka.onenightjinroh.application.service.tally;

import com.okaka.onenightjinroh.application.domain.Game;
import com.okaka.onenightjinroh.application.domain.GameParticipant;
import com.okaka.onenightjinroh.application.domain.GameParticipantRepository;
import com.okaka.onenightjinroh.application.domain.TallyResult;
import com.okaka.onenightjinroh.application.domain.Vote;
import com.okaka.onenightjinroh.application.domain.VoteRepository;
import com.okaka.onenightjinroh.application.port.TallyResultPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TallyUseCase {
    @Autowired
    VoteRepository voteRepository;
    @Autowired
    GameParticipantRepository gameParticipantRepository;
    @Autowired
    TallyResultPort tallyResultPort;

    public void tally(Long gameId) {
        List<Vote> votes = voteRepository.findByGameId(gameId);
        List<GameParticipant> gameParticipants = gameParticipantRepository.findByGameIdWithUserAndRole(gameId);
        Game game = new Game(gameId);
        List<Long> selectedParticipant = TallyResult.selectedParticipant(votes);

        gameParticipants.forEach(gameParticipant -> {
            Long gameParticipationId = gameParticipant.getGameParticipationId();
            Integer voteCount = TallyResult.countVote(gameParticipationId, votes);
            boolean selected = selectedParticipant.contains(gameParticipationId);
            TallyResult tallyResult = new TallyResult(game, gameParticipant, voteCount, selected);
            tallyResultPort.saveGameVoteTally(tallyResult);
        });
    }
}
