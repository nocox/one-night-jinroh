<template>
<div class="night_page">
    <h1>夜の行動ページ</h1>
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
    <div>
        <a v-on:click="doneNightAct">完了</a>
    </div>
</div>
</template>

<script>
import axios from "axios";
import SockJS from "sockjs-client";
import Stomp from "webstomp-client";

export default {
    name: "TempNightTermPage",
    data(){
        return{
            uuid: "yyyyy",
            playerName: "xxxxx",
            playerRole: "xxxxx",
            otherPlayerList: [{
                id: 1,
                name: "xxxxx",
                role: "---"
            }],
        }
    },
    mounted(){
        axios.get('http://localhost:8080/night-index',{withCredentials: true})
        .then((response) => {
            console.log(response.data);
            this.playerName = response.data.gameIndex.playerName;
            this.playerRole = response.data.gameIndex.playerRole;

            this.otherPlayerList = response.data.gameIndex.otherPlayerList;
            this.configWebSocket(response.data.gameId);
      }).catch(() => {
        this.$router.push('/temp-room');
      });
    },
    methods: {
        configWebSocket: function(gameId) {
            this.socket = new SockJS('http://localhost:8080/jinroh-websocket');
            this.stompClient = Stomp.over(this.socket);
            this.stompClient.connect({}, frame => {
                console.log('Connected: ' + frame);
                console.log('Room name: ' + gameId);
                this.stompClient.subscribe('/topic/' + gameId, () => {
                    this.$router.push('/temp-talk');
                });
            });
        },
        doneNightAct: () => {
            axios.get('http://localhost:8080/done-night-act',{withCredentials: true})
            .then((response) => {
                console.log(response.data);
                console.log("夜の行動完了");
            }).catch(() => {
                console.log("夜の行動完了に失敗しました");
            });
        },

    }

}
</script>

<style scoped>
.night_page {
    text-align: left;
    margin: 20px;
}

</style>