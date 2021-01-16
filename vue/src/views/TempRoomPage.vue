<template>
  <div class="room_page">
    <div>
      <span>Room番号:</span>
      <span>{{ uuid }}</span>
    </div>
    <div>
      <div>参加者一覧:</div>
      <ul>
        <li v-for="player in playerList" v-bind:key="player.id">
          {{ player.name }}
        </li>
      </ul>
    </div>
    <div v-if="hostFlg">
      <div class="start" v-on:click="gameStart">スタート</div>
    </div>
    <modal name="game-rule-modal" :clickToClose="false">
      <div class="modal-header">
        <h2>ゲームを開始します</h2>
      </div>
      <div>
        <div>
          <span>人数:</span>
          <span>{{ playerCount }}</span>
        </div>
        <div>
          <div>役職一覧:</div>
          <ul>
            <li v-for="role in roleList" v-bind:key="role.id">
              {{ role.roleName }}
            </li>
          </ul>
        </div>
        <div>
          <a v-on:click="gotoGamePage">OK</a>
        </div>
      </div>
    </modal>
  </div>
</template>

<script>
import axios from "axios";
import SockJS from "sockjs-client";
import Stomp from "webstomp-client";

export default {
  name: "TempRoomPage",
  data() {
    return {
      uuid: "yyyyy",
      playerList: [
        {
          userId: 1,
          name: "xxxxx",
          hostFlg: true,
        },
      ],
      hostFlg: true,
      roleList: [],
      playerCount: 0,
    };
  },
  methods: {
    show: function () {
      this.$modal.show("game-rule-modal");
    },
    hide: function () {
      this.$modal.hide("game-rule-modal");
    },
    gotoGamePage: function () {
      this.$modal.hide("game-rule-modal");
      this.$router.push("/temp-night");
    },
    configWebSocket: function () {
      this.socket = new SockJS("http://localhost:8080/jinroh-websocket");
      this.stompClient = Stomp.over(this.socket);
      this.stompClient.connect({}, (frame) => {
        console.log("Connected: " + frame);
        console.log("Room name: " + this.uuid);
        this.stompClient.subscribe("/topic/" + this.uuid, (value) => {
          console.log("##### subscribe!!: " + value.body);
          this.roleList = JSON.parse(value.body).roleList;
          this.playerCount = JSON.parse(value.body).playerCount;
          this.$modal.show("game-rule-modal");
        });
      });
    },
    gameStart: function () {
      axios
        .get("http://localhost:8080/game-start", { withCredentials: true })
        .then((response) => {
          console.log(response.data);
        })
        .catch(() => {
          this.$router.push("/temp-room");
        });
    },
  },
  mounted() {
    axios
      .get("http://localhost:8080/room-index", { withCredentials: true })
      .then((response) => {
        console.log(response.data);
        this.uuid = response.data.uuid;
        this.playerList = response.data.userList;
        this.hostFlg = response.data.hostFlg;
        this.configWebSocket();
      })
      .catch(() => {
        this.$router.push("/top");
      });
  },
};
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
  color: #50a0f6;
  border-radius: 30px;
  cursor: pointer;
  border: solid 1px;
  border-color: #50a0f6;
}
</style>