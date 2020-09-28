package com.okaka.onenightjinroh;

import org.seasar.doma.Entity;

import java.util.List;
import java.util.Optional;

public class UserProfileAndParticipantEvent {
    Optional<TUserProfile> userProfile;
    List<TEvent> events;
}
