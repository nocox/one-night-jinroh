<template>
  <main class="result_page">
    <modal :width="'90%'" :height="'auto'" name="result-modal">
      <div class="result-modal">
        <ResultImage :judge="judge" @getJudgeText="judgeText = $event"/>
        <myButton class="btn" :method="closeModal" :text="'OK'"/>
      </div>
    </modal>

    <h2>
      {{ judgeText }}
    </h2>
    <div class="result grid-container">
      <div class="result_winners grid-item">
        <h3>かち</h3>
        <ResultPlayerArea :player-list="winPlayerList"/>
      </div>
      <div class="result_losers grid-item">
        <h3>まけ</h3>
        <ResultPlayerArea :player-list="losePlayerList"/>
      </div>
      <div class="holiday-roles grid-item">
        <h3>場のカード</h3>
        <img :src="$getRole(role).img" :alt="role" v-for="(role, key) in holidayRoles" :key="key"/>
      </div>
    </div>

    <myButton v-if="hostFlg" class="btn" :method="returnRoom" :text="'全員ルームに戻す'"/>
    <p v-else class="waiting-text">ホストがルームに戻るを選択するまでおまちください</p>
  </main>
</template>

<script>
import axios from "axios";
import SockJS from "sockjs-client";
import Stomp from "webstomp-client";

import ResultImage from "@/components/ResultImage.vue";
import myButton from "@/components/Button.vue";
import {JINROH_API_BASE_URL} from "../Env";
import ResultPlayerArea from "@/views/ResultPlayerArea";

export default {
  name: "TempResultTermPage",
  data() {
    return {
      judge: "",
      judgeText: "",
      holidayRoles: ["", ""],
      playerList: [
        {
          playerName: "",
          role: "",
          coRole: "",
          winOrLose: "lose",
          myself: false,
          comment: "",
        },
      ],
      hostFlg: false,
      winPlayers: [],
      losePlayers: [],
    };
  },
  components: {ResultPlayerArea, ResultImage, myButton},
  computed: {
    // playerListを勝者と敗者に振り分ける
    winPlayerList: function () {
      return this.playerList.filter((player) => player.winOrLose == "win");
    },
    losePlayerList: function () {
      return this.playerList.filter((player) => player.winOrLose == "lose");
    },
  },
  async mounted() {
    await axios
      .get(JINROH_API_BASE_URL + "/result-index", { withCredentials: true })
      .then((response) => {
        console.log(response.data);
        // 新パラメータ(this datas are available from backend)
        this.judge = response.data.judge;
        this.holidayRoles = response.data.holidayRoles;
        this.playerList = response.data.participants
        this.hostFlg = response.data.hostFlg
        this.configWebSocket(response.data.gameId);
        this.$modal.show("result-modal");
      })
      .catch(() => {
        this.$router.push("/room");
      });
  },
  methods: {
    returnRoom() {
      axios.get(JINROH_API_BASE_URL + "/return-room", { withCredentials: true })
      .catch(() => {
        console.log("サーバとの通信に失敗しました．もう一度お試しください")
      })
    },
    closeModal: function () {
      this.$modal.hide("result-modal");
    },
    getJudgeText: function (judgeText) {
      this.judgeText = judgeText;
    },
    configWebSocket: function (gameId) {
      this.socket = new SockJS(JINROH_API_BASE_URL + "/jinroh-websocket");
      this.stompClient = Stomp.over(this.socket);
      this.stompClient.connect({}, (frame) => {
        console.log("Connected: " + frame);
        this.stompClient.subscribe("/topic/return-room/" + gameId, () => {
          this.$router.push("/room");
        });
      });
    },
  },
};
</script>

<style lang="scss" scoped>
.result_page {
  margin: 20px auto;
  text-align: left;
}

h2,
h3 {
  text-align: center;
}

.btn {
  display: block;
  width: 16rem;
  margin: 0 auto;
  margin-top: 3rem;
  text-align: center;
}

.grid-container {
  display: grid;
  grid-template-columns: 50% 50%;
  row-gap: 2rem;
  column-gap: 2rem;
  justify-content: center;

  .grid-item {
    padding: 2rem 3rem 3rem;
    background-color: #eee;
    border-radius: 8px;
  }
}

.result {
  max-width: 600px;
  margin: auto;

  .result_winners {
    grid-column: 1/2;
  }

  .reslut_losers {
    grid-column: 2/3;
  }

  .holiday-roles {
    display: flex;
    flex-wrap: wrap;
    grid-column: 1/3;
    justify-content: center;

    h3 {
      width: 100%;
    }

    img {
      max-width: 8rem;
    }
  }
}

.waiting-text {
  margin-top: 3rem;
  text-align: center;
}

.result-modal {
  padding: 1rem 0;
}

@media screen and (max-width: 639px) {
  .result {
    .result_losers {
      grid-column: 1/2;
    }

    .holiday-roles {
      grid-column: 1/2;
    }
  }

  .grid-container {
    grid-template-columns: 100%;
  }
}
</style>
