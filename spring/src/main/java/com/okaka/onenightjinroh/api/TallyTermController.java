package com.okaka.onenightjinroh.api;

import com.okaka.onenightjinroh.application.service.tally.GetTallyTermIndexUseCase;
import com.okaka.onenightjinroh.application.service.tally.TallyTermIndexBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TallyTermController {

    @Autowired
    GetTallyTermIndexUseCase getTallyTermIndexUseCase;

    public TallyTermIndexBean getTallyTermIndex() {
        return getTallyTermIndexUseCase.get();
    }
}
