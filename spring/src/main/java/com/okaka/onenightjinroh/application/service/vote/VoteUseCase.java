package com.okaka.onenightjinroh.application.service.vote;


import com.okaka.onenightjinroh.application.domain.Vote;
import com.okaka.onenightjinroh.application.repository.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VoteUseCase {
    @Autowired
    VoteRepository voteRepository;

    public void vote(Long fromGameParticipantId, Long toGameParticipantId) {
        Vote vote = new Vote(fromGameParticipantId, toGameParticipantId);
        voteRepository.createVote(vote);
    }
}
