package com.okaka.onenightjinroh.api;

import com.okaka.onenightjinroh.application.service.tally.TallyUseCase;
import com.okaka.onenightjinroh.application.service.vote.GetVoteTermIndexUseCase;
import com.okaka.onenightjinroh.application.service.vote.IsAllVoteUseCase;
import com.okaka.onenightjinroh.application.service.vote.VoteForm;
import com.okaka.onenightjinroh.application.service.vote.VoteTermIndexBean;
import com.okaka.onenightjinroh.application.service.vote.VoteUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
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
    IsAllVoteUseCase isAllVoteUseCase;

    @Autowired
    TallyUseCase tallyUseCase;

    @RequestMapping(path = "/vote-index")
    @CrossOrigin(origins = {"http://localhost:8081"}, allowCredentials = "true")
    public VoteTermIndexBean getVoteTermIndex() {
        String strGameId = session.getAttribute("game_id").toString();
        Long gameId = Long.valueOf(strGameId);

        String strGameParticipationId = session.getAttribute("game_participation_id").toString();
        Long gameParticipantId = Long.valueOf(strGameParticipationId);

        return getVoteTermIndexUseCase.get(gameId, gameParticipantId);
    }

    @RequestMapping(path = "/vote")
    @CrossOrigin(origins = {"http://localhost:8081"}, allowCredentials = "true")
    public int vote(@RequestBody VoteForm voteForm) {
        String strGameParticipationId = session.getAttribute("game_participation_id").toString();
        Long gameParticipantId = Long.valueOf(strGameParticipationId);
        voteUseCase.vote(gameParticipantId, voteForm.getGameParticipantId());

        String strGameId = session.getAttribute("game_id").toString();
        Long gameId = Long.valueOf(strGameId);
        if (isAllVoteUseCase.is(gameId)) {
            tallyUseCase.tally(gameId);
            messagingTemplate.convertAndSend("/topic/done-tally/" + gameId, "");
        }
        return 0;
    }
}
