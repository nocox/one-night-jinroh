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
          {{ player.name }} <span v-if="myselfUserId === player.userId">（あなた）</span>
        </li>
      </ul>
      <p>ページをリロードすると<br />最新の参加者を取得できます。</p>
    </section>

    <section class="btn-content">
      <!-- スタートボタンを表示（ホストのみ）-->
      <div class="start">
        <div class="button-area">
          <myButton
              class="default-btn start-btn"
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
            :width="'90%'"
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
                <li v-for="(count, name) in $store.state.rolls" v-bind:key="name">
                  <span> {{ name }}</span>
                  <span> : </span>
                  <span>{{ count }} </span>
                </li>
              </ul>
            </div>
            <myButton class="btn" :text="'OK'" :method="gotoGamePage" />
          </div>
        </modal>
      </div>

      <div class="finish">
        <div class="button-area">
          <myButton
              class="default-btn finish-btn"
              :text="'かいさん...'"
              :method="openRoomFinishModal"
              v-if="hostFlg"
          />
          <p v-if="!hostFlg">ホストがゲームを始めるまでお待ちください！</p>
        </div>

        <!-- ゲーム終了の最終確認モーダル -->
        <modal
            name="room-finish-modal"
            :clickToClose="true"
            :height="'auto'"
            :width="'90%'"
            :scrollable="true"
        >
          <div class="modal-header">
            <h2>ルームを解散します</h2>
          </div>
          <div class="modal-content">
            <p>本当によろしいですか？</p>
            <div class="btn-content">
              <myButton class="action-btn" :text="'解散'" :method="finishRoom" />
              <myButton class="cancel-btn" :text="'戻る'" :method="closeRoomFinishModal" />
            </div>
          </div>
        </modal>
      </div>
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
      myselfUserId: 0,
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
    updateRolls: function () {
      const roleNames = this.roleList.map((role) => role.roleName);
      let countedRoles = {};
      for (let i = 0; i < roleNames.length; i++) {
        let key = roleNames[i];
        countedRoles[key] = countedRoles[key] ? countedRoles[key] + 1 : 1;
      }
      this.$store.commit('setRolls', countedRoles);
    },
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
          this.updateRolls();
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
    openRoomFinishModal: function () {
      this.$modal.show("room-finish-modal");
    },
    closeRoomFinishModal: function () {
      this.$modal.hide("room-finish-modal");
    },
    finishRoom: function () {
      console.log("ゲームを解散します。")

      axios
          .get(JINROH_API_BASE_URL + "/room-finish", { withCredentials: true })
          .then(() => {
            this.$router.push("/top");
          })
          .catch(() => {
            console.log("通信に失敗しました。")
          });
    }
  },
  mounted() {
    window.addEventListener("beforeunload", (event) => {
      event.returnValue = "ゲームを終了しますか？";
    });
    axios
      .get(JINROH_API_BASE_URL + "/room-index", { withCredentials: true })
      .then((response) => {
        console.log(response.data);
        this.uuid = response.data.uuid;
        this.playerList = response.data.userList;
        this.hostFlg = response.data.hostFlg;
        this.myselfUserId = response.data.myselfUserId;
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
      background: none;
      border: none;

      &:hover {
        cursor: pointer;
      }
    }
  }
}

.player-list {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  height: 24rem;
  color: #fff;
  background: url("../assets/images/room-top-bg.png") no-repeat center center;
  background-size: contain;

  ul li {
    text-align: left;
    list-style: none;
  }

  p {
    margin-top: 0.8rem;
    font-size: 0.8em;
  }
}

.btn-content {
  display: flex;
  justify-content: center;
  gap: 32px;

  a {
    margin: 0;
  }
  .button-area {
    .default-btn {
      display: block;
      width: 10rem;
      padding: 1rem 2rem;
      border: solid 2px #bd625a;
      border-radius: 10px;
    }

    .start-btn {
      background-color: #bd625a;
      color: #fff;
    }

    .finish-btn {
      background-color: #fff;
      color: #bd625a;
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

    ul li {
      display: grid;
      grid-template-columns: 4em 1em 1em;
      justify-content: center;
      text-align: left;
      list-style: none;
    }
  }

  .btn {
    margin: 1rem auto;
  }

  .btn-content {
    margin: 1rem auto;
    padding: 16px;
    display: flex;
    justify-content: center;
    gap: 32px;
  }

  .action-btn {
    border: solid 2px #bd625a;
    background-color: #bd625a;
    color: #fff;
  }

  .cancel-btn {
    border: solid 2px #bd625a;
    background-color: #ffffff;
    color: #bd625a;
  }
}

@media screen and (max-width: 639px) {
  .player-list {
    background: url("../assets/images/room-top-bg-sp.png") repeat center center;
  }

  .btn-content {
    flex-direction: column;
    .button-area {
      .default-btn {
        width: auto;
      }
    }
  }
}
</style>