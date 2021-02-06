<template>
  <div id="room-page">
    <h1>ルームトップページ</h1>

    <!-- ルーム番号を表示 -->
    <div class="room-num">
      <h2>
        部屋番号:
        <input id="copyTarget" type="text" :value="uuid" readonly />
        <button @click="copyToClipboard">コピー</button>
      </h2>
    </div>
    <!-- 参加者一覧を表示 -->
    <div class="player">
      <h2>参加者一覧</h2>
      <div class="player-list">
        <ul>
          <li v-for="player in playerList" v-bind:key="player.id">
            {{ player.name }}
          </li>
        </ul>
      </div>
    </div>

    <!-- スタートボタンを表示（ホストのみ）-->
    <div>
      <myButton :text="'スタート'" :method="gameStart" v-if="hostFlg" />
    </div>

    <!-- ゲーム開始を通知するモーダル -->
    <modal name="game-rule-modal" :clickToClose="false" :scrollable="true">
      <div class="modal-header">
        <h2>ゲームを開始します</h2>
      </div>

      <div class="modal-content">
        <h3>人数:{{ playerCount }}</h3>

        <div class="role-list">
          <h3>役職一覧:</h3>
          <ul>
            <li v-for="role in roleList" v-bind:key="role.id">
              {{ role.roleName }}
            </li>
          </ul>
        </div>
        <myButton :text="'OK'" :method="gotoGamePage" />
      </div>
    </modal>
  </div>
</template>

<script>
import axios from "axios";
import SockJS from "sockjs-client";
import Stomp from "webstomp-client";

import myButton from "@/components/Button.vue";

export default {
  name: "RoomTopPage",
  data() {
    return {
      uuid: "yyyyy",
      playerList: [
        {
          userID: 1,
          name: "xxxxx",
          hostFlg: true,
        },
      ],
      hostFlg: true,
      roleList: [],
      playerCount: 0,
    };
  },
  components: {
    myButton,
  },
  methods: {
    // ホストがスタートボタンを押下した時の処理
    gameStart: function () {
      axios
        .get("http://localhost:8080/game-start", { withCredentials: true })
        .then((response) => {
          console.log(response.data);
        })
        .catch(() => {
          this.$router.push("/room-top");
        });
    },
    // モーダル関係の処理
    show: function () {
      this.$modal.show("game-rule-modal");
    },
    hide: function () {
      this.$modal.hide("game-rule-modal");
    },
    gotoGamePage: function () {
      // モーダルを隠してページ遷移
      this.$modal.hide("game-rule-modal");
      this.$router.push("/night-page");
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
    copyToClipboard: function () {
      let copyTarget = document.getElementById("copyTarget");
      copyTarget.select();
      document.execCommand("Copy");
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

<style lang="scss" scoped>
#room-page {
  max-width: 1024px;
  margin: 0 auto;
  padding: 0 8rem;

  .player {
    display: flex;
    flex-direction: column;
    justify-content: center;
    .player-list {
      ul li {
        display: inline-block;
        background-color: aqua;
        margin: 0 1rem;
        list-style: none;
      }
    }
  }

  .start {
    a {
      display: block;

      width: 10rem;
      height: 2rem;

      margin: 0 auto;

      background-color: aqua;
      &:hover {
        cursor: pointer;
      }
    }
  }
}

.role-list {
  ul {
    display: flex;
    justify-content: center;
    padding: 0;
    li {
      margin: auto;
      text-align: center;
      list-style: none;
      width: 5rem;
      background-color: aqua;
    }
  }
}
</style>