<template>
  <div class="night-page">
    <h1>夜の行動を行ってください</h1>

    <div class="grid-container">
      <div class="board-cards">
        <h2>場のカード</h2>
        <ul>
          <li
            class="board-card"
            v-for="board_card in BoardCards"
            :key="board_card.index"
          >
            <RoleCard :roleName="'不明'" />
          </li>
        </ul>
      </div>

      <div class="me">
        <h2>あなたです</h2>
        <RoleCard :roleName="playerRole.roleName" />
        <p>{{ playerName }}</p>
        <p>役職：{{ playerRole.roleName }}</p>
      </div>
      <div class="others">
        <h2>他のプレイヤー</h2>
        <ul>
          <li v-for="player in otherPlayerList" :key="player.id">
            <RoleCard :roleName="player.role.roleName" />
            <p>{{ player.name }}</p>
            <p>役職：{{ player.role.roleName }}</p>
          </li>
        </ul>
      </div>
    </div>

    <div>
      <myButton :method="doneNightAct" :text="'完了'" />
    </div>
  </div>
</template>

<script>
import axios from "axios";
import SockJS from "sockjs-client";
import Stomp from "webstomp-client";
import { JINROH_API_BASE_URL} from "../Env";

import RoleCard from "@/components/RoleCard.vue";
import myButton from "@/components/Button.vue";

export default {
  name: "NightPage",
  data() {
    return {
      playerName: "xxxxx",
      playerRole: "xxxxx",
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
  components: {
    RoleCard,
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
        this.$router.push("/room-top");
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
          this.$router.push("/temp-talk");
        });
      });
    },
    doneNightAct: () => {
      axios
        .get(JINROH_API_BASE_URL + "/done-night-act", { withCredentials: true })
        .then((response) => {
          console.log(response.data);
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
ul {
  display: flex;
  flex-wrap: wrap;
  padding: 0;
  margin: 0;
}

li {
  list-style: none;
  margin: auto;
  text-align: center;
}

p {
  margin: 0;
}

.grid-container {
  max-width: 1200px;
  margin: auto;

  display: grid;
  grid-template-rows: 20rem auto;
  grid-template-columns: 20rem 1fr;
  align-items: center;

  .board-cards {
    grid-column: 2/3;
    grid-row: 1/2;
  }
  .me {
    grid-column: 1/2;
    grid-row: 1/3;
  }
  .others {
    grid-column: 2/3;
    grid-row: 2/3;
    ul li {
      margin-top: 2rem;
    }
  }
}
</style>
