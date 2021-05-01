<template>
  <div class="result_page">
    <h1>結果ページ</h1>
    <h2>{{ judgeText }}</h2>

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
  </div>
</template>

<script>
import axios from "axios";
// import SockJS from "sockjs-client";
// import Stomp from "webstomp-client";

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