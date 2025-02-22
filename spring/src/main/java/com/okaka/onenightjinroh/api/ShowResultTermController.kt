package com.okaka.onenightjinroh.api

import com.okaka.onenightjinroh.application.service.result.GetGameResultUseCase
import com.okaka.onenightjinroh.application.service.result.ShowResultTermIndexBean
import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.HttpSession

@RestController
class ShowResultTermController(
    private val session: HttpSession,
    private val useCase: GetGameResultUseCase,
    private val messagingTemplate: SimpMessagingTemplate,
) {
    @get:RequestMapping(path = ["/result-index"])
    val showResultTermIndex: ShowResultTermIndexBean
        get() {
            val strGameId = session.getAttribute("game_id").toString()
            val gameId = strGameId.toLong()
            val strGameParticipationId = session.getAttribute("game_participation_id").toString()
            val gameParticipantId = strGameParticipationId.toLong()

            val gameResult = useCase.invoke(gameId, gameParticipantId)
            return ShowResultTermIndexBean.fromDomain(gameResult)
        }

    @RequestMapping(path = ["/return-room"])
    fun returnRoom(): Int {
        val gameId = session.getAttribute("game_id").toString().toLong()

        session.removeAttribute("game_id")
        session.removeAttribute("game_participation_id")

        messagingTemplate.convertAndSend("/topic/return-room/$gameId", "")
        return 0
    }
}
