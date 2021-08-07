<template>
  <main class="night-page">
    <h2>夜の行動を行ってください</h2>

    <RoleCardDisplay
      :playerRole="playerRole"
      :playerName="playerName"
      :otherPlayerList="otherPlayerList"
    />

    <div v-if="playerRole.roleName == '占い師'">
      <p>占うプレイヤーを選んでください</p>
      <ul>
        <li v-for="player in canSelectedPlayers" v-bind:key="player.id">
          <label>
            <input
              type="radio"
              v-model="selectedUranai"
              v-bind:value="'PLAYER:' + player.id"
              :disabled="isSelected"
            />
            {{ player.name }}
          </label>
        </li>
        <li>
          <label>
            <input
              type="radio"
              v-model="selectedUranai"
              value="HOLIDAY_ROLES"
              :disabled="isSelected"
            />
            お休み中のロール
          </label>
        </li>
      </ul>

      <myButton
        class="btn"
        :method="uranai"
        :text="'占う'"
      />

      <p>占い結果</p>
      <div v-if="uranaiResult.status == 'PLAYER'">
        <p>{{ uranaiResult.user.userName }}は，{{ uranaiResult.roles[0].roleName }}でした</p>
      </div>
      <div v-if="uranaiResult.status == 'HOLIDAY_ROLES'">
        <p>お休み中のカードは，{{ uranaiResult.roles[0].roleName }}と{{ uranaiResult.roles[1].roleName }}でした</p>
      </div>
      <div v-if="uranaiResult.status == 'NOT_CHOOSE'">
        <p>占いを実行しませんでした</p>
      </div>
    </div>

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
      canSelectedPlayers: [
        {
          id: 1,
          name: "xxxxx",
        }
      ],
      selectedUranai: "",
      uranaiResult: {
        status: "",
        user: {
          userId: null,
          userName: ""
        },
        participantId: null,
        roles: [
          {
            roleId: null,
            roleName: "",
          }
        ],
      },
      isSelected: false,
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
        this.canSelectedPlayers = this.otherPlayerList;
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
    uranai: function() {
      var uranaiStatus = this.selectedUranai != "" ? (this.selectedUranai.indexOf("PLAYER") != -1 ? "PLAYER" : "HOLIDAY_ROLES")  : "NOT_CHOOSE";
      var playerId = this.selectedUranai.indexOf("PLAYER:") != -1 ? Number(this.selectedUranai.split(":")[1]) : null;

      axios
        .post(
          JINROH_API_BASE_URL + "/night/uranai",
          JSON.stringify({ status: uranaiStatus, participantId: playerId }),
          {
            withCredentials: true,
            headers: {
              "Content-Type": "application/json",
            },
          }
        )
        .then((response) => {
          this.uranaiResult = response.data
          console.log(response.data);
        })
        .catch(() => {
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
