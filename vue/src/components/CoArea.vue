<template>
  <section class="co-area">
    <div class="player-wrapper">
      <!-- 自分 -->
      <Player
        :playerName="player.playerName"
        :roleName="player.playerRole.roleName"
        :coRole="this.coRole"
        :selectedPlayers="selectedPlayers"
        :isMe="true"
      />
      <!-- 他のプレイヤー -->
      <Player
        :playerName="val.name"
        :roleName="val.role.roleName"
        :coRole="'村人'"
        :selectedPlayers="selectedPlayers"
        v-for="(val, key) in otherPlayerList"
        :key="key"
      />
    </div>
  </section>
</template>

<script>
import Player from "@/components/Player.vue";

export default {
  name: "CoArea",
  props: ["otherPlayerList", "player", "coRole", "selectedPlayers"],
  components: { Player },
};
</script>

<style lang="scss" scoped>
.co-area {
  padding: 1rem;
  background-color: #eee;

  .player-wrapper {
    display: flex;
    justify-content: space-between;
    padding: 0 1rem;
  }
}

@media screen and (max-width: 639px) {
  .co-area {
    row-gap: 16px;
    column-gap: 16px;
    justify-content: flex-start;

    .player-wrapper {
      flex-direction: column;
      row-gap: 1rem;
      align-items: center;
    }
  }
}
</style>