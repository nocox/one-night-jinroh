<template>
  <main class="result_page">

    <h2>{{ judgeText }}</h2>

    <resultImage :result="judgeText" />

    <myButton class="btn" :method="returnRoom" :text="'ルームに戻る'" />

    <RoleCardDisplay
      :playerRole="playerRole"
      :playerName="playerName"
      :otherPlayerList="otherPlayerList"
    />

  </main>
</template>

<script>
import axios from "axios";

import RoleCardDisplay from "@/components/RoleCardDisplay";
import resultImage from "@/components/resultImage.vue";
import myButton from "@/components/Button.vue";

export default {
  name: "TempResultTermPage",
  data() {
    return {
      judgeText: "",
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
    };
  },
  components: { resultImage, myButton,RoleCardDisplay },
  mounted() {
    axios
      .get("http://localhost:8080/result-index", { withCredentials: true })
      .then((response) => {
        console.log(response.data);
        this.judgeText = response.data.judgeText;
        this.playerName = response.data.gameIndex.playerName;
        this.playerRole = response.data.gameIndex.playerRole;
        this.hostFlag = response.data.gameIndex.hostFlag;
        this.otherPlayerList = response.data.gameIndex.otherPlayerList;
      })
      .catch(() => {
        this.$router.push("/temp-room");
      });
  },
  methods: {
    returnRoom() {
      this.$router.push("/room-top");
    },
  },
};
</script>

<style scoped>
.result_page {
  text-align: left;
  margin: 20px;
}

h2 {
  text-align: center;
}

.btn {
  text-align: center;
  display: block;
  width: 16rem;
  margin: 0 auto;
}
</style>