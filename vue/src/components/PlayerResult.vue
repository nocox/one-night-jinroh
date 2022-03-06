<template>
  <article  class="result-player" >
    <figure class="player-icon">
      <img :src="RoleList[role]" alt="" />
    </figure>

    <figure class="co-icon">
      <img
        class="co-icon__img"
        :src="this.coRoleList[this.coRole]"
        :alt="''"
      />
    </figure>
    <span class="player-name" :class="{ me: this.myself }">
      {{ playerName }}</span>
    <span class="player-comment">
      {{ comment }}
    </span>
  </article>
</template>

<script>
export default {
  name: "Player",
  props: [
    "playerName",
    "role",
    "coRole",
    "selectedPlayers",
    "myself",
    "comment",
  ],
  data() {
    return {
      RoleList: {
        unknown: require("../assets/images/chara-icon/unknown.png"),
        jinroh: require("../assets/images/chara-icon/jinroh.png"),
        murabito: require("../assets/images/chara-icon/murabito.png"),
        uranaishi: require("../assets/images/chara-icon/uranaishi.png"),
        kaito: require("../assets/images/chara-icon/kaito.png"),
        kyojin: require("../assets/images/chara-icon/kyojin.png"),
        turibito: require("../assets/images/chara-icon/tsuribito.png"),
      },
      coRoleList: {
        不明: require("../assets/images/fukidashi/fumei.png"),
        人狼: require("../assets/images/fukidashi/jinroh.png"),
        村人: require("../assets/images/fukidashi/murabito.png"),
        占い師: require("../assets/images/fukidashi/uranaishi.png"),
        怪盗: require("../assets/images/fukidashi/kaito.png"),
        狂人: require("../assets/images/fukidashi/kyojin.png"),
        吊り人: require("../assets/images/fukidashi/tsuribito.png"),
      },
    };
  },
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

<style lang="scss" scoped>

.result-player {
  display: grid;
  grid-template-columns: minmax(80px, 100px) 1fr;
  column-gap: 0;
  align-items: center;
  width: 100%;
  margin: auto;
  margin-top: 1.5rem;

  .player-icon {
    grid-row: 1/3;
    grid-column: 1/2;
    width: 50px;
    height: 50px;
    display: flex;
    justify-self: center;

    img {
      width: 100%;
      height: auto;
      border-radius: 10px;
    }
  }

  .co-icon {
    grid-row: 1/2;
    grid-column: 2/3;
    width: 30px;
    height: 30px;

    .co-icon__img {
      width: 100%;
      height: auto;
    }
  }

  .player-name {
    grid-row: 2/3;
    grid-column: 2/3;
  }

  .me {
    text-decoration: underline;
  }

  .player-comment {
    grid-column: 1/3;
    text-align: center;
  }
}

@media screen and (max-width: 639px) {
  .result-player {
    width: 100%;
    max-width: 320px;

    .player-icon {
      width: 80px;
      height: 80px;
    }

    .co-icon {
      width: 50px;
      height: 50px;
    }
  }
}
</style>