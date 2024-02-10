package com.okaka.onenightjinroh.application.domain

class Rule(
    var ruleId: Long?,
    val ruleName: String,
    val roleIds: List<Long>
) {
    companion object {
        val RULE_MAP: Map<Int, List<Long>> =
            mapOf(
                3 to listOf(1L,2L,3L,4L,5L),
                4 to listOf(1L,2L,3L,4L,5L,6L),
                5 to listOf(1L,2L,3L,4L,5L,6L,1L),
                6 to listOf(1L,2L,3L,4L,5L,6L,1L,2L),
                7 to listOf(1L,2L,3L,4L,5L,6L,1L,2L,3L),
                8 to listOf(1L,2L,3L,4L,5L,6L,1L,2L,3L,4L),
                9 to listOf(1L,2L,3L,4L,5L,6L,1L,2L,3L,4L,5L),
            )

        fun createByParticipantCount(playerNum: Int): Rule {
            return Rule(
                null,
                "xxxxxxx",
                RULE_MAP[playerNum] ?: throw IllegalArgumentException()
            )
        }
    }

}
