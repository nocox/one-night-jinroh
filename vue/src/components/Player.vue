<template>
  <article class="player" :class="{ selected: isSelected(this.playerName) }">
    <figure class="player-icon">
      <img :src="RoleList[roleName]" alt="" />
    </figure>

    <figure class="co-icon">
      <img
        class="co-icon__img"
        :src="this.coRoleList[this.coRole]"
        :alt="this.coRole"
      />
    </figure>
    <span class="player-name" :class="{ me: this.myself }">
      {{ playerName }}
    </span>
  </article>
</template>

<script>
export default {
  name: "Player",
  props: [
    "playerName",
    "roleName",
    "coRole",
    "selectedPlayers",
    "myself",
  ],
  data() {
    return {
      RoleList: {
        不明: require("../assets/images/chara-icon/unknown.png"),
        人狼: require("../assets/images/chara-icon/jinroh.png"),
        村人: require("../assets/images/chara-icon/murabito.png"),
        占い師: require("../assets/images/chara-icon/uranaishi.png"),
        怪盗: require("../assets/images/chara-icon/kaito.png"),
        狂人: require("../assets/images/chara-icon/kyojin.png"),
        吊り人: require("../assets/images/chara-icon/tsuribito.png"),
      },
      coRoleList: {
        jinroh: require("../assets/images/fukidashi/jinroh.png"),
        murabito: require("../assets/images/fukidashi/murabito.png"),
        uranaishi: require("../assets/images/fukidashi/uranaishi.png"),
        kaito: require("../assets/images/fukidashi/kaito.png"),
        kyojin: require("../assets/images/fukidashi/kyojin.png"),
        turibito: require("../assets/images/fukidashi/tsuribito.png"),
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
.player {
  display: grid;
  grid-template-columns: minmax(80px, 100px) 1fr;
  column-gap: 16px;
  align-items: center;
  width: 30%;

  .player-icon {
    grid-row: 1/3;
    grid-column: 1/2;
    width: 80px;
    height: 80px;

    img {
      width: 100%;
      height: auto;
      border-radius: 10px;
    }
  }

  .co-icon {
    grid-row: 1/2;
    grid-column: 2/3;
    width: 50px;
    height: 50px;

    .co-icon__img {
      width: 100%;
      height: auto;
    }
  }

  .player-name {
    font-size:14px;
    grid-row: 2/3;
    grid-column: 2/3;
  }

  .me {
    text-decoration: underline;
  }
}

.player.selected {
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

@media screen and (max-width: 639px) {
  .player {
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