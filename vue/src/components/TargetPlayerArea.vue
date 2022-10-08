<template>
  <section class="co-area">
    <div class="player-wrapper">
      <!-- 自分 -->
      <Player
        :playerName="player.playerName"
        :roleName="player.playerRole.roleName"
        :coRole="cos.find(player.playerId)"
        :selectedPlayers="selectedPlayers"
        :myself="true"
      />
      <!-- 他のプレイヤー -->
      <Player
        :class="{'is-checked': val.id===checkPlayerId}"
        :playerName="val.name"
        :roleName="val.role.roleName"
        :coRole="cos.find(val.id)"
        :selectedPlayers="selectedPlayers"
        v-for="(val, key) in otherPlayerList"
        :myself="false"
        :key="key"
      />
    </div>
  </section>
</template>

<script>
import Player from "@/components/Player.vue";

export default {
  name: "TargetPlayerArea",
  props: ["checkPlayerId","otherPlayerList", "player", "cos", "coRole", "selectedPlayers"],
  components: { Player },
};
</script>

<style lang="scss" scoped>
.co-area {
  padding: 1rem;
  background-color: #eee;

  .player-wrapper {
    display: flex;
    flex-wrap:wrap;
    row-gap: 1rem;
    justify-content: space-between;
    padding: 0 1rem;
  }
}

.is-checked{
    border: 1px solid red;
}

@media screen and (max-width: 639px) {
  .co-area {
    row-gap: 16px;
    column-gap: 16px;
    justify-content: flex-start;

    .player-wrapper {
      flex-direction: column;
      align-items: center;
    }
  }
}
</style>