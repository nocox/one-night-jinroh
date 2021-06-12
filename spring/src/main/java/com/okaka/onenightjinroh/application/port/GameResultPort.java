package com.okaka.onenightjinroh.application.port;

import com.okaka.onenightjinroh.application.domain.GameResult;

public interface GameResultPort {
    GameResult load(Long gameId);
}
