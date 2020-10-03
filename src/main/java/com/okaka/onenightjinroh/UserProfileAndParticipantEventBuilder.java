package com.okaka.onenightjinroh;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class UserProfileAndParticipantEventBuilder {

    @Autowired
    TEventDao tEventDao;

    @Autowired
    TUserDao tUserDao;

    @Autowired
    TEventParticipantDao tEventParticipantDao;

    List<UserProfileAndParticipantEvent> build(List<Integer> userIds) {
        Map<Integer, List<TEventParticipant>> userParticipantEvent =
                tEventParticipantDao.selectByUserIds(userIds, Collectors.groupingBy(s -> s.t_user_id));
        Map<Integer, List<TEvent>> eventMap = tEventDao.selectByUserIds(userIds, Collectors.groupingBy(s -> s.t_event_id));
        List<TUserProfile> tUserProfiles = tUserDao.selectTUserProfileByUserIds(userIds);
        return tUserProfiles.stream()
                .map(tUserProfile -> new UserProfileAndParticipantEvent(
                        tUserProfile,
                        userParticipantEvent.getOrDefault(tUserProfile.user_id, Collections.emptyList()).stream()
                                .map(tEventParticipant -> tEventParticipant.t_event_id)
                                .map(tEventId -> eventMap.getOrDefault(tEventId, Collections.emptyList()).get(0))
                                .collect(Collectors.toList()))
                ).collect(Collectors.toList());
    }
}
