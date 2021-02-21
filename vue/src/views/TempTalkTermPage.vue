<template>
<div class="talk_page">
    <h1>話し合いページ</h1>
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
    <div v-if="hostFlag">
        <div class="start" v-on:click="endTalk">話し合いを終了する</div>
    </div>
    <modal name="talk-start-modal">
        <div class="modal-header">
            <h2>話し合いを始めてください</h2>
        </div>
        <div>
            <a v-on:click="closeModal">OK</a>
        </div>
    </modal>
</div>
</template>

<script>
import axios from "axios";
import SockJS from "sockjs-client";
import Stomp from "webstomp-client";

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
        }
    },
    mounted() {
        axios.get('http://localhost:8080/talk-index',{withCredentials: true})
        .then((response) => {
            console.log(response.data);
            this.playerName = response.data.gameIndex.playerName;
            this.playerRole = response.data.gameIndex.playerRole;
            this.hostFlag = response.data.gameIndex.hostFlag;
            this.otherPlayerList = response.data.gameIndex.otherPlayerList;
            this.$modal.show("talk-start-modal");
            this.configWebSocket(response.data.gameId)
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
                this.stompClient.subscribe('/topic/end-talk/' + gameId, () => {
                    this.$router.push('/temp-vote');
                });
            });
        },
        closeModal() {
            this.$modal.hide("talk-start-modal");
        },
        endTalk() {
            axios.get('http://localhost:8080/end-talk',{withCredentials: true})
            .then((response) => {
                console.log(response.data);
            }).catch(() => {
                this.$router.push('/temp-room');
            });
        }
    }
}
</script>

<style scoped>
.talk_page {
    text-align: left;
    margin: 20px;
}

</style>