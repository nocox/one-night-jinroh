package com.okaka.onenightjinroh.api.bean;

import java.util.List;

public class NightJinrohIndexBean {
    private final List<String> playerNames;

    public NightJinrohIndexBean(List<String> playerNames) {
        this.playerNames = playerNames;
    }

    public List<String> getPlayerNames() {
        return playerNames;
    }
}
