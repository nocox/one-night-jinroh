<template>
  <main class="talk_page">
    <h2>朝になりました。話し合いを行ってください。</h2>
    <p class="action-result" v-if="nightActLog">{{ nightActLog }}</p>

    <PlayerArea
      :otherPlayerList="otherPlayerList"
      :player="{ playerId:playerId ,playerName: playerName, playerRole: playerRole }"
      :cos="cos"
    />

    <div class="col2">
      <DisplayRolls class="display-rolls" />

      <CoButtonArea 
        :playerId="playerId"
        :myCoRole="cos.find(playerId)"
      />
    </div>

    <div class="btn-area">
      <myButton
        v-if="hostFlag"
        :method="endTalk"
        :text="'話し合いを終了する'"
      />
    </div>

    <modal :width="'90%'" :height="'auto'" name="talk-start-modal">
      <div class="modal-header">
        <h2>話し合いを始めてください</h2>
      </div>
      <div class="btn-area">
        <myButton :method="closeModal" :text="'OK'"></myButton>
      </div>
    </modal>
  </main>
</template>

<script>
import axios from "axios";
import SockJS from "sockjs-client";
import Stomp from "webstomp-client";

import { JINROH_API_BASE_URL } from "../Env";
import myButton from "@/components/Button.vue";
import CoButtonArea from "@/components/CoButtonArea.vue";
import PlayerArea from "@/components/PlayerArea.vue";
import DisplayRolls from "@/components/DisplayRolls.vue";
import CoPlayers from "@/CoPlayers";

export default {
  name: "TempTalkPage",
  components: {
    myButton,
    CoButtonArea,
    PlayerArea,
    DisplayRolls,
  },
  data() {
    return {
      playerId : -1,
      playerName: "xxxxx",
      playerRole: {
        roleId: -1,
        roleName: "不明",
      },
      nightActLog: "",
      hostFlag: false,
      cos: new CoPlayers([]),
      otherPlayerList: [
        {
          id: 1,
          name: "xxxxx",
          role: "---",
        },
      ],
    };
  },
  mounted() {
    axios
      .get(JINROH_API_BASE_URL + "/talk-index", { withCredentials: true })
      .then((response) => {
        this.playerId = response.data.gameIndex.playerId;
        this.playerName = response.data.gameIndex.playerName;
        this.playerRole = response.data.gameIndex.playerRole;
        this.hostFlag = response.data.gameIndex.hostFlag;
        this.cos = new CoPlayers(response.data.cos);
        this.otherPlayerList = response.data.gameIndex.otherPlayerList;
        this.nightActLog = response.data.gameIndex.nightActLog;
        this.$modal.show("talk-start-modal");
        this.configWebSocket(response.data.gameId);
      })
      .catch(() => {
        this.$router.push("/room");
      });
  },
  methods: {
    configWebSocket: function (gameId) {
      this.socket = new SockJS(JINROH_API_BASE_URL + "/jinroh-websocket");
      this.stompClient = Stomp.over(this.socket);
      this.stompClient.connect({}, (frame) => {
        this.stompClient.subscribe("/topic/end-talk/" + gameId, () => {
          this.$router.push("/vote");
        });
        this.stompClient.subscribe("/topic/receive-co/" + gameId, (value) => {
          const coState = JSON.parse(value.body).coBeans;
          this.cos = new CoPlayers(coState);
        });
      });
    },
    closeModal() {
      this.$modal.hide("talk-start-modal");
    },
    endTalk() {
      axios
        .get(JINROH_API_BASE_URL + "/end-talk", { withCredentials: true })
        .then((response) => {})
        .catch(() => {
          this.$router.push("/room");
        });
    },
  },
};
</script>

<style lang="scss" scoped>
section {
  margin: 2rem auto 0;
}

h2 {
  text-align: center;
}

.btn-area {
  margin: 2.4rem auto;
  text-align: center;
}

.action-result {
  padding: 1rem;
  background-color: #eee;
}

.modal-header {
  h2 {
    margin-top: 2rem;
  }
}

.col2 {
  display: flex;
  column-gap: 32px;

  .display-rolls {
    width: 100%;
    max-width: 335px;
    margin: 32px auto;
    margin-bottom: 0;
  }
}

@media screen and (max-width: 639px) {
  .col2 {
    flex-direction: column-reverse;
    flex-wrap: wrap;

    .display-rolls {
      max-width: 100%;
      margin: 32px auto;
    }
  }
}
</style>
