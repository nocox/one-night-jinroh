package com.okaka.onenightjinroh.domaimpl

import com.okaka.jinroh.persistence.*
import com.okaka.onenightjinroh.application.domain.Game
import com.okaka.onenightjinroh.application.domain.GameTerm
import com.okaka.onenightjinroh.application.domain.Rule
import com.okaka.onenightjinroh.application.repository.GameRepository
import org.springframework.stereotype.Repository

@Repository
class GameRepositoryDomaImpl(
    private val gameDao: GameDao,
    private val ruleDao: RuleDao,
    private val ruleSelectDao: RoleSelectDao,
    private val gameTermDao: GameTermDao
) : GameRepository {
    override fun find(gameId: Long): Game {
        val gameEntity = gameDao.select(gameId)
        val roomId = gameEntity.room_id
        val ruleEntity = ruleDao.select(gameEntity.rule_id)
        val roleEntities = ruleSelectDao.selectRoleListByRuleId(ruleEntity.rule_id)
        val roleIds = roleEntities.map { it.role_id }
        val rule = Rule.of(ruleEntity.rule_id, ruleEntity.rule_name, roleIds)
        val gameTermEntity = gameTermDao.selectByGameId(gameId)
        val gameTerm = toGameTermDomainFromEntity(gameTermEntity)

        return Game.of(gameId, roomId, rule, gameTerm)
    }

    override fun save(game: Game) {
        val rule = game.rule!!
        val ruleEntity = RuleEntity().also {
            it.rule_name = rule.ruleName
        }
        ruleDao.insert(ruleEntity)

        rule.ruleId = ruleEntity.rule_id

        rule.roleIds.map { roleId ->
            ruleSelectDao.insert(RoleSelectEntity().also {
                it.role_id = roleId
                it.rule_id = rule.ruleId
            })
        }

        val gameEntity = GameEntity().also {
            it.room_id = game.roomId
            it.rule_id = rule.ruleId
        }
        gameDao.insert(gameEntity)

        game.gameId = gameEntity.game_id

        val gameTermEntity = GameTermEntity().also {
            it.game_id = game.gameId
            it.game_term =
                game.term?.let { term -> toGameTermEntityFromDomain(term) } ?: throw IllegalArgumentException()
        }
        gameTermDao.insert(gameTermEntity)
    }

    private fun toGameTermEntityFromDomain(domain: GameTerm): String =
        when (domain) {
            GameTerm.NIGHT -> "night"
            GameTerm.TALK -> "talk"
            GameTerm.VOTE -> "vote"
            GameTerm.TALLY -> "tally"
            GameTerm.RESULT -> "result"
        }

    private fun toGameTermDomainFromEntity(entity: GameTermEntity): GameTerm =
        when (entity.game_term) {
            "night" -> GameTerm.NIGHT
            "talk" -> GameTerm.TALK
            "vote" -> GameTerm.VOTE
            "tally" -> GameTerm.TALLY
            "result" -> GameTerm.RESULT
            else -> throw IllegalArgumentException()
        }
}