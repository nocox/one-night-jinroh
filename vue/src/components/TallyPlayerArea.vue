<template>
  <section class="co-area">
    <div class="player-wrapper">
      <!-- 自分 -->
      <Player
          :playerName="player.playerName"
          :roleName="player.playerRole.roleName"
          :coRole="coMap(player.playerId)"
          :selectedPlayers="selectedPlayers"
          :myself="true"
          :isSelected=isSelected(player.playerName)
      />
      <!-- 他のプレイヤー -->
      <Player
          v-for="(val, key) in otherPlayerList"
          :key="key"
          :playerName="val.name"
          :roleName="val.role.roleName"
          :coRole="coMap(val.id)"
          :selectedPlayers="selectedPlayers"
          :myself="false"
          :isSelected=isSelected(val.name)
      />
    </div>
  </section>
</template>

<script>
import Player from "@/components/Player";

export default {
  name: "TallyPlayerArea",
  props: ["otherPlayerList", "player", "cos", "selectedPlayers"],// TODO: selectedPlayersに必須をつけたい
  components: {Player},
  methods: {
    coMap: function (playerId) {
      const roleName = this.cos.find(co => co.id === playerId)

      if (roleName) {
        return roleName.role;
      } else {
        return "murabito";
      }
    },
    isSelected: function (name) {
      return this.selectedPlayers.map(it => it.name).includes(name);
    },
  },
};
</script>

<style lang="scss" scoped>
.co-area {
  padding: 1rem;
  background-color: #eee;

  .player-wrapper {
    display: flex;
    flex-wrap: wrap;
    row-gap: 1rem;
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
      align-items: center;
    }
  }
}
</style>