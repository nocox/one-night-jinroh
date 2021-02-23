package com.okaka.onenightjinroh.application.service.tally;

import org.springframework.stereotype.Service;

@Service
public class GetTallyTermIndexUseCase {
    public TallyTermIndexBean get() {
        return new TallyTermIndexBean();
    }
}
