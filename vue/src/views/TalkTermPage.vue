<template>
  <main class="talk_page">
    <h2>朝になりました。話し合いを行ってください。</h2>

    <RoleCardDisplay
      :playerRole="playerRole"
      :playerName="playerName"
      :otherPlayerList="otherPlayerList"
    />

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
import RoleCardDisplay from "@/components/RoleCardDisplay.vue";
import myButton from "@/components/Button.vue";

export default {
  name: "TempTalkPage",
  components: {
    RoleCardDisplay,
    myButton,
  },
  data() {
    return {
      playerName: "xxxxx",
      playerRole: {
        roleId: -1,
        roleName: "不明",
      },
      hostFlag: false,
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
        console.log(response.data);
        this.playerName = response.data.gameIndex.playerName;
        this.playerRole = response.data.gameIndex.playerRole;
        this.hostFlag = response.data.gameIndex.hostFlag;
        this.otherPlayerList = response.data.gameIndex.otherPlayerList;
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
        console.log("Connected: " + frame);
        this.stompClient.subscribe("/topic/end-talk/" + gameId, () => {
          this.$router.push("/vote");
        });
      });
    },
    closeModal() {
      this.$modal.hide("talk-start-modal");
    },
    endTalk() {
      axios
        .get(JINROH_API_BASE_URL + "/end-talk", { withCredentials: true })
        .then((response) => {
          console.log(response.data);
        })
        .catch(() => {
          this.$router.push("/room");
        });
    },
  },
};
</script>

<style lang="scss" scoped>
.btn-area {
  margin: 2.4rem auto;
  text-align: center;
}

h2 {
  text-align: center;
}

.modal-header {
  h2{
    margin-top: 2rem;
  }
}
</style>
