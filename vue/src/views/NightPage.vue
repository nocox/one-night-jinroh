<template>
  <main class="night-page">
    <h2>{{ playerName }}さん、<br class="show-sp">夜の行動を行ってください</h2>

    <section class="role-card-display-area">
      <div class="role-description">
        <NightRoleDescription :roleName="playerRole.roleName" />
      </div>
      
      <div class="action">
        <NightAction :roleName="playerRole.roleName" :otherPlayerList="otherPlayerList" />
      </div>
    </section>

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

import NightAction from "@/components/NightAction.vue";
import NightRoleDescription from "@/components/NightRoleDescription";
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
      isSelected: false,
    };
  },
  components: {
    myButton,
    NightAction,
    NightRoleDescription
  },
  mounted() {
    axios
      .get(JINROH_API_BASE_URL + "/night-index", { withCredentials: true })
      .then((response) => {
        console.log(response.data);
        this.playerName = response.data.gameIndex.playerName;
        this.playerRole = response.data.gameIndex.playerRole;
        this.otherPlayerList = response.data.gameIndex.otherPlayerList;
        this.isCompleted = response.data.doneNightAct;
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

.show-sp{
  display: none;
}

.btn {
  display: block;
  width: 12rem;
  margin: 5rem auto;
  text-align: center;
}

.btn_disabled {
  color: gray;
  pointer-events: none;
  border-color: gray;
}

.role-card-display-area {
  display: grid;
  grid-template-columns: 1fr;
  justify-content: center;
  text-align: center;
}

.player {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;  
  padding: 1em;
  background-color: #eee;
}

.role-description{  
padding: 24px;
background-color: #eee;
}


.action{
grid-column: 1/3;  
height  :20rem;
  padding: 1em;
  margin-top: 16px;
  border: 3px solid #eee;
  border-radius: 8px;
}


@media screen and (max-width: 639px) {
  .show-sp{
    display: block;
  }

  .role-card-display-area {
    grid-template-columns: 1fr;
  }

  .action{
    grid-column: 1/2;
  }

  h3 {
    font-size: 16px;
  }

}
</style>
