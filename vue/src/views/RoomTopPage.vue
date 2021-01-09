<template>
    <div>
        <!-- <IsMatch_rule_players /> -->

        <div id="RoomNum">
            部屋番号：ｘｘｘｘ
        </div>

        <div id="player-area">
            <PlayerList />

        </div>        
        
        <!-- <div id="rule-area">
            <div class="change-member">
                <a href="">
                    人数変更
                </a>
                ：{{ join_num=3 }}人
            </div>
            <RoleSetting role="村人サイド" />
            <RoleSetting role="人狼サイド" />
        </div>         -->
        
        <div id="btn-area">
            <!-- <a href="">
                準備OK
            </a>：{{ ready_num=1 }}/ {{ join_num }}人 -->
            <a v-on:click="show" >
                ゲームスタート
            </a>
            <modal name="start">
                <p>このルールで開始します</p>
                <p>人数：{{ player_num }}人</p>
                <div class="">
                    <div>村人サイド</div>

                    <p>VS</p>
                    <div>人狼サイド</div>
                </div>
                <a v-on:click="hide">戻る</a>
            </modal>
        </div>        
        
        <GameDescription />
    </div>
</template>

<script>
import GameDescription from "@/components/GameDescription.vue"
// import IsMatch_rule_players from "@/components/IsMatch_rule_players.vue"
import PlayerList from "@/components/PlayerList.vue"
// import RoleSetting from "@/components/RoleSetting.vue"

export default {
    name: "RoomTopPage",
    props:["join_num", "ready_num", ],
    data: function(){
        return {
            player_num : 3,
        }
    },
    components:{
        GameDescription,
        // IsMatch_rule_players,
        PlayerList,
        // RoleSetting
    },
    methods: {
        show: function(){
            this.$modal.show("start");
        },
        hide: function(){
            this.$modal.hide("start");
        }
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
        display: flex;
        justify-content: center;
        flex-direction: column;
    }

    #rule-area .change-member a{
        font-weight: bold;
        display: inline-block;
        text-decoration: none;
        color: #50A0F6;
        border: 1px solid #50A0F6;
        border-radius: 1rem;
        padding: 1rem;
    }

    #btn-area{
        background-color: #b3b3b3;
        padding:10rem;
        display: flex;
        justify-content: center;
        align-items: center;
    }
    #btn-area a{
        display: inline-block;
        width: 8rem;
        background-color: white;
        padding: 2rem;
        margin: 0 2rem;
        text-decoration: none;
        color: #50A0F6;
        border:1px solid #50A0F6;
        border-radius: 1rem;
    }
</style>