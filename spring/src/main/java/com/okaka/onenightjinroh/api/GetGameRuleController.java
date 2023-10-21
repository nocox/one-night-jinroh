package com.okaka.onenightjinroh.api;

import com.okaka.jinroh.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class GetGameRuleController {
    @Autowired
    HttpSession session;

    @Autowired
    GameDao gameDao;

    @Autowired
    RoleSelectDao roleSelectDao;

    @Autowired
    RoleDao roleDao;

    @RequestMapping(path = "/game-rule/{gameId}")
    Response getGameRule(@PathVariable Long gameId) {
        GameEntity game = gameDao.select(gameId);
        Long ruleId = game.rule_id;

        List<RoleEntity> gameRuleRoleList = roleSelectDao.selectRoleListByRuleId(ruleId);

        List<RoleEntity> roleList = roleDao.selectAll();

        List<RoleResponse> roleResponseList = roleList.stream().map(role ->
                new RoleResponse(
                        role.role_id,
                        role.role_name,
                        gameRuleRoleList.stream().filter(it -> it.role_id.equals(role.role_id)).count()
                )
        ).collect(Collectors.toUnmodifiableList());

        return new Response(roleResponseList);
    }

    public class Response{
        List<RoleResponse> roleList;

        public Response(List<RoleResponse> roleList){
            this.roleList = roleList;
        }

        public List<RoleResponse> getRoleList() {
            return roleList;
        }
    }

    public class RoleResponse{
        Long roleId;
        String roleName;
        Long count;

        public RoleResponse(
            Long roleId,
            String roleName,
            Long count
        ){
            this.roleId = roleId;
            this.roleName = roleName;
            this.count = count;

        }

        public Long getRoleId() {
            return roleId;
        }

        public String getRoleName() {
            return roleName;
        }

        public Long getCount() {
            return count;
        }
    }
}
