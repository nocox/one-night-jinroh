package com.okaka.onenightjinroh;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.List;

@JsonSerialize
public class UserProfileAndParticipantEvent {
    TUserProfile userProfile;
    List<TEvent> events;

    public UserProfileAndParticipantEvent(TUserProfile userProfile, List<TEvent> events) {
        this.userProfile = userProfile;
        this.events = events;
    }

    public List<TEvent> getEvents() {
        return events;
    }

    public TUserProfile getUserProfile() {
        return userProfile;
    }
}
