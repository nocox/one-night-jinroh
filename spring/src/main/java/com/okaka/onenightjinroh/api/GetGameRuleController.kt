package com.okaka.onenightjinroh.api

import com.okaka.jinroh.persistence.GameDao
import com.okaka.jinroh.persistence.RoleDao
import com.okaka.jinroh.persistence.RoleEntity
import com.okaka.jinroh.persistence.RoleSelectDao
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class GetGameRuleController(
    private val gameDao: GameDao,
    private val roleSelectDao: RoleSelectDao,
    private val roleDao: RoleDao
) {
    @RequestMapping(path = ["/game-rule/{gameId}"])
    fun getGameRule(@PathVariable gameId: Long?): Response {
        val game = gameDao.select(gameId)
        val gameRuleRoleList = roleSelectDao.selectRoleListByRuleId(game.rule_id)
        val roleList = roleDao.selectAll()
        val roleResponseList = roleList.map { role: RoleEntity ->
            RoleResponse(
                role.role_id,
                role.role_name,
                gameRuleRoleList.count { it.role_id == role.role_id }
            )
        }

        return Response(roleResponseList)
    }

    class Response(
        val roleList: List<RoleResponse>
    )

    class RoleResponse(
        val roleId: Long,
        val roleName: String,
        val count: Int
    )
}
