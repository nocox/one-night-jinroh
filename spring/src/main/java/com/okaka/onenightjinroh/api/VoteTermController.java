package com.okaka.onenightjinroh.api;

import com.okaka.onenightjinroh.application.service.tally.TallyUseCase;
import com.okaka.onenightjinroh.application.service.vote.GetVoteTermIndexUseCase;
import com.okaka.onenightjinroh.application.service.vote.VoteForm;
import com.okaka.onenightjinroh.application.service.vote.VoteTermIndexBean;
import com.okaka.onenightjinroh.application.service.vote.VoteUseCase;
import com.okaka.onenightjinroh.application.service.vote.VoteUseCase.VoteStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class VoteTermController {
    @Autowired
    HttpSession session;

    @Autowired
    SimpMessagingTemplate messagingTemplate;

    @Autowired
    GetVoteTermIndexUseCase getVoteTermIndexUseCase;

    @Autowired
    VoteUseCase voteUseCase;

    @Autowired
    TallyUseCase tallyUseCase;

    @RequestMapping(path = "/vote-index")
    public VoteTermIndexBean getVoteTermIndex() {
        String strGameId = session.getAttribute("game_id").toString();
        Long gameId = Long.valueOf(strGameId);

        String strGameParticipationId = session.getAttribute("game_participation_id").toString();
        Long gameParticipantId = Long.valueOf(strGameParticipationId);

        return getVoteTermIndexUseCase.get(gameId, gameParticipantId);
    }

    @RequestMapping(path = "/vote")
    public int vote(@RequestBody VoteForm voteForm) {
        String strGameParticipationId = session.getAttribute("game_participation_id").toString();
        Long gameParticipantId = Long.valueOf(strGameParticipationId);

        String strGameId = session.getAttribute("game_id").toString();
        Long gameId = Long.valueOf(strGameId);

        VoteStatus voteStatus = voteUseCase.vote(
                gameId,
                gameParticipantId,
                voteForm.getGameParticipantId()
        );

        if (voteStatus instanceof VoteStatus.AllVoted) {
            tallyUseCase.tally(gameId);
            messagingTemplate.convertAndSend("/topic/done-tally/" + gameId, "");
        }
        return 0;
    }
}
