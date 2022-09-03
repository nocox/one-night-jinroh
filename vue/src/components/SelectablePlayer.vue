<template>
  <article :class="{ selected: isSelected(this.playerName) }">
    <Player
      :playerName="playerName"
      :roleName="roleName"
      :coRole="coRole"
      :myself="true"
    />
  </article>
</template>

<script>
import Player from "@/components/Player.vue";

export default {
  name: "SelectablePlayer",
  props: ["selectedPlayers", "playerName", "roleName", "coRole", "myself"],
  components: { Player },
  methods: {
    isSelected: function (name) {
      // 投票ページ以外ではselectedPlayersは存在しないのでfalseを返す
      if (!this.selectedPlayers) {
        return false;
      }

      let selectedPlayersName = [];
      this.selectedPlayers.forEach((player) => {
        selectedPlayersName.push(player.name);
      });

      if (selectedPlayersName.includes(name)) {
        return true;
      } else {
        return false;
      }
    },
  },
};
</script>

<style lang="scss">
.selected {
  .player-icon {
    position: relative;

    img {
      filter: grayscale(100%);
    }

    &::after {
      position: absolute;
      left: 0;
      width: calc(100% * 1.414);
      height: 2px;
      content: "";
      background-color: red;
      transform: rotateZ(45deg);
      transform-origin: left top;
    }

    &::before {
      position: absolute;
      bottom: 0;
      left: 0;
      z-index: 1;
      width: calc(100% * 1.414);
      height: 2px;
      content: "";
      background-color: red;
      transform: rotateZ(-45deg);
      transform-origin: left top;
    }
  }

  .co-icon__img {
    filter: grayscale(100%);
  }
}
</style>
