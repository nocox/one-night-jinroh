<template>
  <article>
    <Player
      :playerName="playerName"
      :roleName="roleName"
      :coRole="coRole"
      :myself="true"
      :isSelected=isSelected(this.playerName)
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

      return selectedPlayersName.includes(name);
    },
  },
};
</script>

<style lang="scss" scoped>
</style>
