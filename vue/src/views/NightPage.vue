<template>
  <main class="night-page">
    <h2>夜の行動を行ってください</h2>

    <RoleCardDisplay
      :playerRole="playerRole"
      :playerName="playerName"
      :otherPlayerList="otherPlayerList"
    />

    <myButton
      :class="{ btn_disabled: isCompleted }"
      class="btn"
      :method="doneNightAct"
      :text="'完了'"
    />
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
  name: "NightPage",
  data() {
    return {
      playerName: "xxxxx",
      playerRole: {
        roleId: -1,
        roleName: "不明",
      },
      otherPlayerList: [
        {
          id: 1,
          name: "xxxxx",
          role: "---",
        },
      ],
      isCompleted: false,
    };
  },
  components: {
    RoleCardDisplay,
    myButton,
  },
  mounted() {
    axios
      .get(JINROH_API_BASE_URL + "/night-index", { withCredentials: true })
      .then((response) => {
        console.log(response.data);
        this.playerName = response.data.gameIndex.playerName;
        this.playerRole = response.data.gameIndex.playerRole;
        this.otherPlayerList = response.data.gameIndex.otherPlayerList;
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
        console.log("Room name: " + gameId);
        this.stompClient.subscribe("/topic/" + gameId, () => {
          this.$router.push("/talk");
        });
      });
    },
    doneNightAct: function() {
      axios
        .get(JINROH_API_BASE_URL + "/done-night-act", { withCredentials: true })
        .then((response) => {
          console.log(response.data);
          this.isCompleted = true;
          console.log("夜の行動完了");
        })
        .catch(() => {
          console.log("夜の行動完了に失敗しました");
        });
    },
  },
};
</script>


<style lang="scss" scoped>
h2 {
  text-align: center;
}

.btn {
  text-align: center;
  display: block;
  width: 12rem;
  margin: 5rem auto;
}
.btn_disabled {
  pointer-events: none;
  border-color: gray;
  color: gray;
}
</style>
