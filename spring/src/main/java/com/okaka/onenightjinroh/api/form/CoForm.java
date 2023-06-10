package com.okaka.onenightjinroh.api.form;

public class CoForm {
    Long playerId;
    String role;

    public CoForm() {
    }

    public CoForm(Long playerId, String role) {
        this.playerId = playerId;
        this.role = role;
    }

    public Long getPlayerId() {
        return playerId;
    }

    public String getRole() {
        return role;
    }
}
