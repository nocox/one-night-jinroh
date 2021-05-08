<template>
  <main class="result_page">
    <input type="text" v-model="judgeText">
    <h2>{{ judgeText }}</h2>
    <!-- <img v-if="judgeText == '平和村成功'" src="../assets/images/result1.png" alt="">
    <img v-if="judgeText == '人狼の勝利'" src="../assets/images/result1.png" alt=""> -->

    <resultImage :result="judgeText" />    
    

    <div>
      <span>自分:</span>
      <span>{{ playerName }}</span>
      <span>({{ playerRole.roleName }})</span>
    </div>
    <hr />
    <div>
      他のプレイヤー
      <ul>
        <li v-for="player in otherPlayerList" v-bind:key="player.id">
          <span>{{ player.name }}</span>
          <span>({{ player.role.roleName }})</span>
        </li>
      </ul>
    </div>

    <button v-on:click="returnRoom">ルームに戻る</button>
  </main>
</template>

<script>
import axios from "axios";
// import SockJS from "sockjs-client";
// import Stomp from "webstomp-client";

import resultImage from "@/components/resultImage.vue";

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
  components:{ resultImage},
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
      this.$router.push("/temp-room");
    },
  },
};
</script>

<style scoped>
.result_page {
  text-align: left;
  margin: 20px;
}
</style>