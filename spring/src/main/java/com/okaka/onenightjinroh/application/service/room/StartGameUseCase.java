package com.okaka.onenightjinroh.application.service.room;

import com.okaka.jinroh.persistence.*;
import com.okaka.onenightjinroh.application.domain.Game;
import com.okaka.onenightjinroh.application.domain.HolidayRoles;
import com.okaka.onenightjinroh.application.domain.Role;
import com.okaka.onenightjinroh.application.repository.GameRepository;
import com.okaka.onenightjinroh.application.repository.HolidayRolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StartGameUseCase {

    @Autowired
    private RoleSelectDao roleSelectDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private GameParticipationDao gameParticipationDao;

    @Autowired
    private RoomParticipantDao roomParticipantDao;

    @Autowired
    private HolidayRolesRepository holidayRolesRepository;

    @Autowired
    private GameRepository gameRepository;



    public GameStartWebSocketBean startGame(Long roomId, Long hostUserId) throws NotEnoughParticipantsException {
        int participantCount = roomParticipantDao.selectParticipantCount(roomId);

        if (participantCount < 3) {
            throw new NotEnoughParticipantsException("参加人数が足りていません。");
        }

        Game game = Game.Companion.startGame(roomId, participantCount);
        gameRepository.save(game);

        List<RoleEntity> roleEntityList = roleSelectDao.selectRoleListByRuleId(game.getRule().getRuleId());

        Collections.shuffle(roleEntityList);
        List<UserEntity> userEntityList = userDao.selectByRoom(roomId);

        for (int i = 0; i < userEntityList.size(); i ++) {
            GameParticipationEntity gameParticipationEntity = new GameParticipationEntity();
            gameParticipationEntity.game_id = game.getGameId();
            gameParticipationEntity.user_id = userEntityList.get(i).user_id;
            gameParticipationEntity.host_flg = userEntityList.get(i).user_id.equals(hostUserId);
            gameParticipationEntity.role_id = roleEntityList.get(i).role_id;
            gameParticipationDao.insert(gameParticipationEntity);
        }

        List<Role> notUseRoles = roleEntityList.subList(userEntityList.size(), roleEntityList.size())
                .stream().map(roleEntity -> Role.byRoleId(roleEntity.role_id, roleEntity.role_name))
                .collect(Collectors.toList());
        HolidayRoles holidayRoles = new HolidayRoles(game.getGameId(), notUseRoles);
        holidayRolesRepository.save(holidayRoles);

        return new GameStartWebSocketBean(
                roleSelectDao.selectRoleListByRuleId(game.getRule().getRuleId()),
                userEntityList.size(),
                game.getGameId()
        );
    }
}
