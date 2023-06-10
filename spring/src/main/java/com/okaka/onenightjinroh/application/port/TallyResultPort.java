package com.okaka.onenightjinroh.application.port;

import com.okaka.onenightjinroh.application.domain.TallyResult;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TallyResultPort {
    TallyResult load(Long gameId);
    List<TallyResult> searchTallyResults(Long gameId);
    void saveGameVoteTally(TallyResult tallyResult);
}
