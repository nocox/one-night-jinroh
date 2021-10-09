<template>
  <main class="result_page">
    <h2>{{ judgeText }}</h2>

    <resultImage :result="judgeText" />

    <div>
      <div>
        <h3>かち</h3>
        <Player
          :playerName="val.playerName"
          :roleName="val.roleName"
          :coRole="'村人'"
          :isMe="val.isMe"
          v-for="(val, key) in winPlayers"
          :key="key"
        />
      </div>
      <div>
        <h3>まけ</h3>
        <Player
          :playerName="val.playerName"
          :roleName="val.roleName"
          :isMe="val.isMe"
          :coRole="'村人'"
          v-for="(val, key) in losePlayers"
          :key="key"
        />
      </div>
    </div>
    <myButton class="btn" :method="returnRoom" :text="'ルームに戻る'" />
  </main>
</template>

<script>
import axios from "axios";

import resultImage from "@/components/resultImage.vue";
import Player from "@/components/Player.vue";
import myButton from "@/components/Button.vue";
import { JINROH_API_BASE_URL } from "../Env";

export default {
  name: "TempResultTermPage",
  data() {
    return {
      judgeText: "",
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
      holidayRoles: [
        {
          roleId: -1,
          roleName: "不明",
        },
        {
          roleId: -1,
          roleName: "不明",
        },
      ],
      winTeam: [],
      winPlayers: [],
      losePlayers: [],
    };
  },
  components: { resultImage, myButton, Player },
  async mounted() {
    await axios
      .get(JINROH_API_BASE_URL + "/result-index", { withCredentials: true })
      .then((response) => {
        console.log(response.data);
        this.judgeText = response.data.judgeText;
        this.playerName = response.data.gameIndex.playerName;
        this.playerRole = response.data.gameIndex.playerRole;
        this.hostFlag = response.data.gameIndex.hostFlag;
        this.otherPlayerList = response.data.gameIndex.otherPlayerList;
        this.holidayRoles = response.data.holidayRoles;
      })
      .catch(() => {
        this.$router.push("/room");
      });
    this.divideWinLosePlayers();
  },
  methods: {
    returnRoom() {
      this.$router.push("/room");
    },
    divideWinLosePlayers() {
      if (this.judgeText.indexOf("人狼") !== -1) {
        this.winTeam = ["人狼", "狂人"];
      } else if (this.judgeText.indexOf("吊り人") !== -1) {
        this.winTeam = ["吊り人"];
      } else {
        this.winTeam = ["村人", "怪盗", "占い師"];
      }

      this.otherPlayerList.forEach((otherPlayer) => {
        if (this.winTeam.indexOf(otherPlayer.role.roleName) !== -1) {
          this.winPlayers.push({
            playerName: otherPlayer.name,
            roleName: otherPlayer.role.roleName,
            coRole: "不明",
            isMe: false,
          });
        } else {
          this.losePlayers.push({
            playerName: otherPlayer.name,
            roleName: otherPlayer.role.roleName,
            coRole: "不明",
            isMe: false,
          });
        }
      });

      if (this.winTeam.indexOf(this.playerRole.roleName) !== -1) {
        this.winPlayers.push({
          playerName: this.playerName,
          roleName: this.playerRole.roleName,
          coRole: "不明",
          isMe: true,
        });
      } else {
        this.losePlayers.push({
          playerName: this.playerName,
          roleName: this.playerRole.roleName,
          coRole: "不明",
          isMe: true,
        });
      }
    },
  },
};
</script>

<style scoped>
.result_page {
  margin: 20px auto;
  text-align: left;
}

h2 {
  text-align: center;
}

.btn {
  display: block;
  width: 16rem;
  margin: 0 auto;
  text-align: center;
}
</style>