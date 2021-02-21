package com.okaka.onenightjinroh.application.domain;

import com.okaka.jinroh.persistence.UserDao;
import com.okaka.jinroh.persistence.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class UserRepository {
    @Autowired
    UserDao userDao;

    public Map<Long, User> toMapUsersByGameId(Long gameId) {
        Map<Long, User> usersMap = new HashMap<>();
        userDao.selectByGame(gameId).forEach(entity -> {
            User user = toDomain(entity);
            usersMap.put(entity.user_id, user);
        });
        return usersMap;
    }

    private User toDomain(UserEntity entity) {
        User user = new User(entity.user_id);
        user.setUserName(entity.user_name);
        return user;
    }
}
