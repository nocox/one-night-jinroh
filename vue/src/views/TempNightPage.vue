<template>
  <div class="night_page">
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
    <div>
      <a>完了</a>
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "TempNightPage",
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
        this.$router.push("/temp-room");
      });
  },
};
</script>

<style scoped>
.night_page {
  text-align: left;
  margin: 20px;
}
</style>