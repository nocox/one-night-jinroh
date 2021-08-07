package com.okaka.onenightjinroh.api;

import com.okaka.onenightjinroh.api.form.NightUranaiForm;
import com.okaka.onenightjinroh.application.bean.NightUranaiResultBean;
import com.okaka.onenightjinroh.application.service.night.DoneNightTermActUseCase;
import com.okaka.onenightjinroh.application.service.night.ExecuteNightUranaiUseCase;
import com.okaka.onenightjinroh.application.service.night.GamePersonalBean;
import com.okaka.onenightjinroh.application.service.night.GetGamePersonalUseCase;
import com.okaka.onenightjinroh.application.service.night.GetNightTermIndexUseCase;
import com.okaka.onenightjinroh.application.service.night.NightTermIndexBean;
import com.okaka.onenightjinroh.application.service.night.NightUranaiStatus;
import com.okaka.onenightjinroh.application.service.night.dto.NightUranaiResultDto;
import com.okaka.onenightjinroh.application.service.room.RoleBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class NightController {
    @Autowired
    HttpSession session;

    @Autowired
    GetNightTermIndexUseCase getNightTermIndexUseCase;

    @Autowired
    DoneNightTermActUseCase doneNightTermActUseCase;

    @Autowired
    GetGamePersonalUseCase getGamePersonalUseCase;

    @Autowired
    ExecuteNightUranaiUseCase executeNightUranaiUseCase;

    @RequestMapping(path = "/night-index")
    NightTermIndexBean getNightTermIndex() {
        String uuid = session.getAttribute("room_uuid").toString();
        String strUserId = session.getAttribute("user_id").toString();
        Long userId = Long.valueOf(strUserId);

        GamePersonalBean gamePersonalBean = getGamePersonalUseCase.get(uuid, userId);
        session.setAttribute("game_id", gamePersonalBean.getGameId());
        session.setAttribute("game_participation_id", gamePersonalBean.getGameParticipationId());

        return getNightTermIndexUseCase.get(gamePersonalBean.getGameId(), gamePersonalBean.getGameParticipationId());
    }

    @RequestMapping(path = "/done-night-act")
    int doneNightTermAct() {
        String strGameId = session.getAttribute("game_id").toString();
        Long gameId = Long.valueOf(strGameId);

        String strGameParticipationId = session.getAttribute("game_participation_id").toString();
        Long gameParticipantId = Long.valueOf(strGameParticipationId);
        doneNightTermActUseCase.done(gameParticipantId, gameId);

        return 0;
    }

    @PostMapping(path = "/night/uranai")
    NightUranaiResultBean uranai(@RequestBody NightUranaiForm form) {
        String strGameId = session.getAttribute("game_id").toString();
        Long gameId = Long.valueOf(strGameId);

        NightUranaiStatus status = mapStatus(form.getStatus());
        NightUranaiResultDto dto = executeNightUranaiUseCase.invoke(gameId, form.getParticipantId(), status);

        List<RoleBean> roleBeans = dto.getRoles().stream().map(RoleBean::new).collect(Collectors.toList());
        NightUranaiResultBean.UserBean userBean = Optional.ofNullable(dto.getUser()).
                map(user -> new NightUranaiResultBean.UserBean(user.getUserId(), user.getUserName()))
                .orElse(null);
        return new NightUranaiResultBean(form.getStatus(), dto.getSelectedPlayer(), roleBeans, userBean);
    }

    private NightUranaiStatus mapStatus(String uranaiStatus) {
        NightUranaiStatus status = NightUranaiStatus.PLAYER;
        if ("HOLIDAY_ROLES".equals(uranaiStatus)) {
            status = NightUranaiStatus.HOLIDAY_ROLES;
        } else if ("NOT_CHOOSE".equals(uranaiStatus)) {
            status = NightUranaiStatus.NOT_CHOOSE;
        }
        return status;
    }
}
