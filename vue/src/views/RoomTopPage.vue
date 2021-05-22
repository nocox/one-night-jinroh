<template>
  <main id="room-page">
    <!-- ルーム番号を表示 -->
    <section class="room-num">
      <h2>へやばんごう</h2>
      <div class="copy-area">
        <input id="copyTarget" type="text" :value="uuid" readonly />
        <button @click="copyToClipboard">
          <span class="material-icons"> content_copy </span>
        </button>
      </div>
      <p>この番号を友達に教えてあげてね！</p>
      <p v-show="is_copied">コピーできました！</p>
    </section>

    <!-- 参加者一覧を表示 -->

    <section class="player-list">
      <h2>さんかしゃ</h2>
      <ul>
        <li v-for="player in playerList" v-bind:key="player.id">
          {{ player.name }}
        </li>
      </ul>
    </section>

    <!-- スタートボタンを表示（ホストのみ）-->
    <section class="start">
      <div class="button-area">
        <myButton
          class="start-btn"
          :text="'はじめる！'"
          :method="gameStart"
          v-if="hostFlg"
        />
        <p v-if="!hostFlg">ホストがゲームを始めるまでお待ちください！</p>
      </div>

      <!-- ゲーム開始を通知するモーダル -->
      <modal
        name="game-rule-modal"
        :clickToClose="false"
        :height="'auto'"
        :scrollable="true"
      >
        <div class="modal-header">
          <h2>ゲームを開始します</h2>
        </div>

        <div class="modal-content">
          <p>参加人数 : {{ playerCount }}</p>

          <div class="role-list">
            <h3>役職一覧</h3>
            <ul>
              <li v-for="role in roleList" v-bind:key="role.id">
                {{ role.roleName }}
              </li>
            </ul>
          </div>
          <myButton class="btn" :text="'OK'" :method="gotoGamePage" />
        </div>
      </modal>
    </section>
  </main>
</template>

<script>
import axios from "axios";
import SockJS from "sockjs-client";
import Stomp from "webstomp-client";
import { JINROH_API_BASE_URL } from "../Env";

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
      is_copied: false,
    };
  },
  components: {
    myButton,
  },
  methods: {
    // ホストがスタートボタンを押下した時の処理
    gameStart: function () {
      axios
        .get(JINROH_API_BASE_URL + "/game-start", { withCredentials: true })
        .then((response) => {
          console.log(response.data);
        })
        .catch(() => {
          this.$router.push("/room");
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
      this.$router.push("/night");
    },
    configWebSocket: function () {
      this.socket = new SockJS(JINROH_API_BASE_URL + "/jinroh-websocket");
      this.stompClient = Stomp.over(this.socket);
      this.stompClient.connect({}, (frame) => {
        console.log("Connected: " + frame);
        console.log("Room name: " + this.uuid);
        this.stompClient.subscribe("/topic/" + this.uuid, (value) => {
          console.log("##### subscribe!!: " + value.body);
          this.roleList = JSON.parse(value.body).roleList;
          this.roleList.sort((a, b) => {
            if (a.roleId < b.roleId) return -1;
            if (a.roleId > b.roleId) return 1;
            return 0;
          });
          this.playerCount = JSON.parse(value.body).playerCount;
          this.$modal.show("game-rule-modal");
        });
      });
    },
    copyToClipboard: function () {
      let copyTarget = document.getElementById("copyTarget");
      copyTarget.select();
      document.execCommand("Copy");
      this.is_copied = true;
    },
  },
  mounted() {
    axios
      .get(JINROH_API_BASE_URL + "/room-index", { withCredentials: true })
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
  text-align: center;
}

.room-num {
  .copy-area {
    display: flex;
    align-items: center;
    justify-content: center;
    #copyTarget {
      width: 16rem;
      height: 2em;
    }
    button {
      margin-left: 0.5rem;
      border: none;
      background: none;
      &:hover {
        cursor: pointer;
      }
    }
  }
}

.player-list {
  height: 24rem;
  display: flex;
  align-items: center;
  justify-content: center;
  flex-direction: column;

  color: #fff;
  background: url("../assets/images/room-top-bg.png") no-repeat center center;
  background-size: contain;
  ul {
    li {
      list-style: none;
    }
  }
}

.start {
  .button-area {
    .start-btn {
      width: 10rem;
      padding: 1rem 2rem;
      background-color: #bd625a;
      border: none;
      border-radius: 10px;
      color: #fff;
    }
    p {
      margin-top: 2rem;
    }
  }
}

.modal-content {
  .role-list {
    h3 {
      margin: 0.5em auto;
    }
    ul {
      display: flex;
      justify-content: space-evenly;
      li {
        list-style: none;
      }
    }
  }
  .btn {
    margin: 1rem auto;
  }
}
</style>