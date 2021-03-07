<template>
<div class="tally_page">
    <h1>集計ページ</h1>
    <div>
        <span>自分:</span>
        <span>{{playerName}}</span>
        <span>({{playerRole.roleName}})</span>
    </div>
    <hr>
    <div>
        他のプレイヤー
        <ul>
            <li v-for="player in otherPlayerList" v-bind:key="player.id">
                <span>{{player.name}}</span>
                <span>({{player.role.roleName}})</span>
            </li>
        </ul>
    </div>

    <h2>集計結果</h2>
    <div>
        <span>選ばれたのは,</span>
        <u><span>{{tallyResult.selectedPlayer.name}}</span></u>
        <span>です．</span>
    </div>
    <h2>投票数</h2>
    <ul>
        <li v-for="player in tallyResult.players" v-bind:key="player.id">
            <span>{{player.name}}</span>
            <span>: {{player.voteCount}}</span>
        </li>
    </ul>

    <modal name="done-tally-modal">
        <div class="modal-header">
            <h2>全員投票が完了しました．集計結果を表示します．</h2>
        </div>
        <div>
            <a v-on:click="closeModal">OK</a>
        </div>
    </modal>
</div>
</template>

<script>
import axios from "axios";
// import SockJS from "sockjs-client";
// import Stomp from "webstomp-client";

export default {
    name: "TempTalkPage",
    data() {
        return{
            playerName: "xxxxx",
            playerRole: "xxxxx",
            hostFlag: false,
            otherPlayerList: [{
                id: 1,
                name: "xxxxx",
                role: "---"
            }],
            tallyResult: {
                selectedPlayer: {
                    id: 1,
                    name: "xxxxx",
                    voteCount: 0
                },
                players: [{
                    id: 1,
                    name: "xxxxx",
                    voteCount: 0
                }]
            }
        }
    },
    mounted() {
        axios.get('http://localhost:8080/tally-index', {withCredentials: true})
        .then((response) => {
            console.log(response.data);
            this.playerName = response.data.gameIndex.playerName;
            this.playerRole = response.data.gameIndex.playerRole;
            this.hostFlag = response.data.gameIndex.hostFlag;
            this.otherPlayerList = response.data.gameIndex.otherPlayerList;

            this.tallyResult = response.data.tallyResult;
            this.$modal.show("done-tally-modal");
        }).catch(() => {
            this.$router.push('/temp-room');
        });
    },
    methods: {
        closeModal() {
            this.$modal.hide("done-tally-modal");
        },
    }
}
</script>

<style scoped>
.tally_page {
    text-align: left;
    margin: 20px;
}

</style>