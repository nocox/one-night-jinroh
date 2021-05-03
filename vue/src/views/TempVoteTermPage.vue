<template>
<div class="vote_page">
    <h1>投票ページ</h1>
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
        投票してください
        <ul>
            <li v-for="player in canVotePlayers" v-bind:key="player.id">
                <label>
                    <input type="radio" v-model="checkPlayerId" v-bind:value="player.id">
                    {{player.name}}
                </label>
            </li>
        </ul>
        <button v-on:click="vote">確定</button>
    </div>
    <modal name="vote-start-modal">
        <div class="modal-header">
            <h2>話し合いが終了しました．投票してください</h2>
        </div>
        <div>
            <button v-on:click="closeModal">OK</button>
        </div>
    </modal>
</div>
</template>

<script>
import axios from "axios";
import SockJS from "sockjs-client";
import Stomp from "webstomp-client";
import { JINROH_API_BASE_URL} from "../Env";

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
            canVotePlayers: [{
                id: 1,
                name: "xxxxx",
                role: "---"
            }],
            checkPlayerId: 0
        }
    },
    mounted() {
        axios.get(JINROH_API_BASE_URL + '/vote-index', {withCredentials: true})
        .then((response) => {
            console.log(response.data);
            this.playerName = response.data.gameIndex.playerName;
            this.playerRole = response.data.gameIndex.playerRole;
            this.hostFlag = response.data.gameIndex.hostFlag;
            this.otherPlayerList = response.data.gameIndex.otherPlayerList;

            this.canVotePlayers = response.data.voteIndex.canVotePlayers;
            this.$modal.show("vote-start-modal");
            this.configWebSocket(response.data.gameId);
        }).catch(() => {
            this.$router.push('/temp-room');
        });

    },
    methods: {
        closeModal() {
            this.$modal.hide("vote-start-modal");
        },
        vote() {
            axios.post(JINROH_API_BASE_URL + '/vote', 
                JSON.stringify({gameParticipantId: this.checkPlayerId}), 
                {
                    withCredentials: true,
                    headers: {
                        'Content-Type': 'application/json'
                    }
                }
            )
            .then((response) => {
                console.log(response.data);
            }).catch(() => {
                this.$router.push('/temp-room');
            });
        },
        configWebSocket: function(gameId) {
            this.socket = new SockJS(JINROH_API_BASE_URL + '/jinroh-websocket');
            this.stompClient = Stomp.over(this.socket);
            this.stompClient.connect({}, frame => {
                console.log('Connected: ' + frame);
                this.stompClient.subscribe('/topic/done-tally/' + gameId, () => {
                    this.$router.push('/temp-tally');
                });
            });
        },
    }
}
</script>

<style scoped>
.vote_page {
    text-align: left;
    margin: 20px;
}

</style>