<template>
<div class="room_page">
    <div>
        <span>Room番号:</span>
        <span>{{uuid}}</span>
    </div>
    <div>
        <div>参加者一覧:</div>
        <ul>
            <li v-for="player in playerList" v-bind:key="player.id">
                {{player.name}}
            </li>
        </ul>
    </div>
    <div v-if="hostFlg">
        <div class="start" v-on:click="gameStart">スタート</div>
    </div>
</div>
</template>

<script>
import axios from "axios";
import SockJS from "sockjs-client";
import Stomp from "webstomp-client";

export default {
    name: "TempRoomPage",
    data(){
        return{
            uuid: "yyyyy",
            playerList: [{
                userId: 1,
                name: "xxxxx",
                hostFlg: true
            }],
            hostFlg: true
        }
    },
    methods: {
        configWebSocket: function() {
            this.socket = new SockJS('http://localhost:8080/jinroh-websocket');
            this.stompClient = Stomp.over(this.socket);
            this.stompClient.connect({}, frame => {
                console.log('Connected: ' + frame);
                console.log('Room name: ' + this.uuid);
                this.stompClient.subscribe('/topic/' + this.uuid, function (value) {
                    console.log('##### subscribe!!: ' + value.body);
                });
            });

        },
        gameStart: function() {
            axios.get('http://localhost:8080/game-start',{withCredentials: true})
            .then((response) => {
                console.log(response.data);
            })
            .catch(() => {
                this.$router.push('/temp-room')
            })
        }
    },
    mounted(){
        axios.get('http://localhost:8080/room-index',{withCredentials: true})
        .then((response) => {
            console.log(response.data);
            this.uuid = response.data.uuid;
            this.playerList = response.data.userList;
            this.hostFlg = response.data.hostFlg;
            this.configWebSocket()
      }).catch(() => {
        this.$router.push('/top');
      });
    }
}
</script>

<style scoped>
.room_page {
    text-align: left;
    margin: 20px;
}

.start {
    display: inline-block;
    max-width: 120px;
    margin-top: 10px;
    padding: 5px 40px;
    background-color: white;
    color: #50A0F6;
    border-radius: 30px;
    cursor: pointer;
    border: solid 1px;
    border-color: #50A0F6;
}


</style>