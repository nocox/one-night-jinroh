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

    <modal name="talk-start-modal">
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

import RoleCardDisplay from "@/components/RoleCardDisplay.vue";
// import RoleCard from "@/components/RoleCard.vue";
import myButton from "@/components/Button.vue";

export default {
  name: "TempTalkPage",
  components: {
    RoleCardDisplay,
    // RoleCard,
    myButton,
  },
  data() {
    return {
      playerName: "xxxxx",
      playerRole: "xxxxx",
      hostFlag: false,
      otherPlayerList: [
        {
          id: 1,
          name: "xxxxx",
          role: "---",
        },
      ],
      BoardCards: [
        {
          role: "---",
        },
        {
          role: "---",
        },
      ],
    };
  },
  mounted() {
    axios
      .get("http://localhost:8080/talk-index", { withCredentials: true })
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
        this.$router.push("/temp-room");
      });
  },
  methods: {
    configWebSocket: function (gameId) {
      this.socket = new SockJS("http://localhost:8080/jinroh-websocket");
      this.stompClient = Stomp.over(this.socket);
      this.stompClient.connect({}, (frame) => {
        console.log("Connected: " + frame);
        this.stompClient.subscribe("/topic/end-talk/" + gameId, () => {
          this.$router.push("/temp-vote");
        });
      });
    },
    closeModal() {
      this.$modal.hide("talk-start-modal");
    },
    endTalk() {
      axios
        .get("http://localhost:8080/end-talk", { withCredentials: true })
        .then((response) => {
          console.log(response.data);
        })
        .catch(() => {
          this.$router.push("/temp-room");
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

</style>


