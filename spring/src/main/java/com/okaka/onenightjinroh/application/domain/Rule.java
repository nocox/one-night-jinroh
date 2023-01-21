package com.okaka.onenightjinroh.application.domain;

public class Rule {
    private Long ruleId;
    private String ruleName;

    public Rule(Long ruleId, String ruleName) {
        this.ruleId = ruleId;
        this.ruleName = ruleName;
    }

    public Long getRuleId() {
        return ruleId;
    }

    public String getRuleName() {
        return ruleName;
    }
}
