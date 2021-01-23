<template>
  <div class="night-page">
    <h1>夜の行動ページ</h1>

    <div class="player-info me">
      <h2>自分：{{ playerName }} ({{ playerRole.roleName }})</h2>
    </div>

    <div class="plyer-info other">
      <h2>他のプレイヤー</h2>
      <ul>
        <li v-for="player in otherPlayerList" v-bind:key="player.id">
          <span>{{ player.name }}</span>
          <span>{{ player.role.roleName }}</span>
        </li>
      </ul>
    </div>

    <div>
      <a>完了</a>
    </div>
  </div>
</template>

<script>
import axios from "axios";

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
    };
  },
  mounted() {
    axios
      .get("http://localhost:8080/night-index", { withCredentials: true })
      .then((response) => {
        console.log(response.data);
        this.playerName = response.data.playerName;
        this.playerRole = response.data.playerRole;
        this.otherPlayerList = response.data.otherPlayerList;
      })
      .catch(() => {
        this.$router.push("/room-top");
      });
  },
};
</script>

<style scoped>
</style>