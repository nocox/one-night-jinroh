<template>
  <div class="night-page">
    <h1>夜の行動を行ってください</h1>

    <div class="board-cards">
      <ul>
        <li
          class="board-card"
          v-for="board_card in BoardCards"
          :key="board_card.index"
        >
          <RoleCard />
        </li>
      </ul>
    </div>

    <hr />

    <div class="player-cards">
      <div class="me">
        <RoleCard/>
        <span> {{ playerName }} </span>
        <span> {{ playerRole.roleName }} </span>
      </div>
      <div class="others">
        <ul>
          <li v-for="player in otherPlayerList" :key="player.id">
            <RoleCard />
            <span> {{ player.name }} </span>
            <span> {{ player.role.roleName }} </span>
          </li>
        </ul>
      </div>
    </div>

    <div>
      <myButton :text="'完了'" />
    </div>
  </div>
</template>

<script>
import axios from "axios";

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


<style lang="scss" scoped>

ul{
  display:flex;
  padding: 0;
  margin: 0;
}

li{
  list-style: none;
}
.player-cards{
  display: flex;
}
</style>
