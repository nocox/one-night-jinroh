package com.okaka.onenightjinroh.api;

import com.okaka.onenightjinroh.api.bean.NightJinrohIndexBean;
import com.okaka.onenightjinroh.api.bean.NightKaitoResultBean;
import com.okaka.onenightjinroh.api.form.NightKaitoForm;
import com.okaka.onenightjinroh.api.form.NightUranaiForm;
import com.okaka.onenightjinroh.application.bean.NightUranaiResultBean;
import com.okaka.onenightjinroh.application.service.night.DoneNightTermActUseCase;
import com.okaka.onenightjinroh.application.service.night.ExecuteKaitoUseCase;
import com.okaka.onenightjinroh.application.service.night.ExecuteNightUranaiUseCase;
import com.okaka.onenightjinroh.application.service.night.GamePersonalBean;
import com.okaka.onenightjinroh.application.service.night.GetGamePersonalUseCase;
import com.okaka.onenightjinroh.application.service.night.GetNightJinrohIndexUseCase;
import com.okaka.onenightjinroh.application.service.night.GetNightTermIndexUseCase;
import com.okaka.onenightjinroh.application.service.night.GetUranaishiNightResultUseCase;
import com.okaka.onenightjinroh.application.service.night.NightTermIndexBean;
import com.okaka.onenightjinroh.application.service.night.NightUranaiStatus;
import com.okaka.onenightjinroh.application.service.night.dto.NightUranaiResultDto;
import com.okaka.onenightjinroh.application.service.room.RoleBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
    GetUranaishiNightResultUseCase getUranaishiNightResultUseCase;

    @Autowired
    ExecuteNightUranaiUseCase executeNightUranaiUseCase;

    @Autowired
    ExecuteKaitoUseCase executeKaitoUseCase;

    @Autowired
    GetNightJinrohIndexUseCase getNightJinrohIndexUseCase;

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

    @GetMapping(path = "/night/uranai")
    Optional<NightUranaiResultBean> uranaiGet() {
        String strGameParticipationId = session.getAttribute("game_participation_id").toString();
        Long gameParticipantId = Long.valueOf(strGameParticipationId);

        Optional<NightUranaiResultDto> optDto = getUranaishiNightResultUseCase.invoke(gameParticipantId);
        if (optDto.isEmpty()) {
            return Optional.empty();
        }

        NightUranaiResultDto dto = optDto.get();
        List<RoleBean> roleBeans = dto.getRoles().stream().map(RoleBean::new).collect(Collectors.toList());
        NightUranaiResultBean.UserBean userBean = Optional.ofNullable(dto.getUser()).
                map(user -> new NightUranaiResultBean.UserBean(user.getUserId(), user.getUserName()))
                .orElse(null);
        return Optional.of(new NightUranaiResultBean(dto.getStatus().toString(), dto.getSelectedPlayer(), roleBeans, userBean));
    }


    @PostMapping(path = "/night/uranai")
    NightUranaiResultBean uranai(@RequestBody NightUranaiForm form) {
        String strGameId = session.getAttribute("game_id").toString();
        Long gameId = Long.valueOf(strGameId);
        String strGameParticipationId = session.getAttribute("game_participation_id").toString();
        Long gameParticipantId = Long.valueOf(strGameParticipationId);

        NightUranaiStatus status = mapStatus(form.getStatus());
        NightUranaiResultDto dto = executeNightUranaiUseCase.invoke(gameId, gameParticipantId, form.getParticipantId(), status);

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

    @PostMapping(path = "/night/kaito")
    ResponseEntity<NightKaitoResultBean> kaito(@RequestBody NightKaitoForm form) {
        String strGameParticipationId = session.getAttribute("game_participation_id").toString();
        Long gameParticipantId = Long.valueOf(strGameParticipationId);

        NightKaitoResultBean bean = executeKaitoUseCase.invoke(gameParticipantId, form.getParticipantId());
        return ResponseEntity.ok().body(bean);
    }

    @GetMapping(path = "/night/jinroh/index")
    ResponseEntity<NightJinrohIndexBean> getJinroh() {
        String strGameId = session.getAttribute("game_id").toString();
        Long gameId = Long.valueOf(strGameId);
        String strGameParticipationId = session.getAttribute("game_participation_id").toString();
        Long gameParticipantId = Long.valueOf(strGameParticipationId);

        NightJinrohIndexBean bean = getNightJinrohIndexUseCase.invoke(gameParticipantId, gameId);
        return ResponseEntity.ok().body(bean);
    }
}
