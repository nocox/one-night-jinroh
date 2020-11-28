<template>
    <div>
        <IsMatch_rule_players />

        <div id="RoomNum">
            部屋番号：ｘｘｘｘ
        </div>

        <div id="player-area">
            プレイヤー表示エリア
            <PlayerList />

        </div>        
        
        <div id="rule-area">
            ルール設定エリア
        </div>        
        
        <div id="btn-area">
            準備完了エリア
        </div>        
        
        <GameDescription />
    </div>
</template>

<script>
import GameDescription from "@/components/GameDescription.vue"
import IsMatch_rule_players from "@/components/IsMatch_rule_players.vue"
import PlayerList from "@/components/PlayerList.vue"

export default {
    name: "RoomTopPage",
    components:{
        GameDescription,
        IsMatch_rule_players,
        PlayerList
    }
}
</script>

<style>


    #RoomNum{
        border: solid 1px #50A0F6;
        width:20rem;
        margin: 1rem auto;
    }

    #player-area{
        background-color: #b3b3b3;
        padding:10rem;
    }

    #rule-area{
        padding:10rem;
    }

    #btn-area{
        background-color: #b3b3b3;
        padding:10rem;
    }
</style>