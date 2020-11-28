<template>
    <div>
        <IsMatch_rule_players />

        <div id="RoomNum">
            部屋番号：
        </div>

        <div id="player-area">
            

        </div>        
        
        <div>
            ルール設定エリア
        </div>        
        
        <div>
            準備完了エリア
        </div>        
        
        <GameDescription />
    </div>
</template>

<script>
import GameDescription from "@/components/GameDescription.vue"
import IsMatch_rule_players from "@/components/IsMatch_rule_players.vue"

export default {
    name: "RoomTopPage",
    components:{
        GameDescription,
        IsMatch_rule_players
    }
}
</script>

<style>


    #RoomNum{
        border: solid 1px #50A0F6;
        width:20rem;
        margin: 1rem auto;
    }
</style>