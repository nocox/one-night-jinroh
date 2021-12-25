<template>
  <section class="co-area">
    <!-- 自分 -->
    <div class="player-wrapper">
      <article class="player">
        <figure class="player-icon">
          <img :src="RoleList[player.playerRole.roleName]" alt="" />
        </figure>
        <figure class="co-icon">
          <img
            class="co-icon__img"
            :src="coRoleList[coMap(player.playerId)]"
            :alt="coRole"
          />
        </figure>
        <span class="player-name me">{{ player.playerName }}</span>
      </article>

      <!-- 自分以外のプレイヤー -->
      <article class="player" v-for="(player, key) in otherPlayerList" :key="key">
        <figure class="player-icon">
          <img :src="RoleList[player.role.roleName]" :alt="player.role.roleName" />
        </figure>
        <figure class="co-icon">
          <img class="co-icon__img" :src="coRoleList[coMap(player.id)]" alt="" />
        </figure>
        <span class="player-name">{{ player.name }}</span>
      </article>
    </div>
  </section>
</template>

<script>
export default {
  name: "CoArea",
  props: ["otherPlayerList", "player", "coRole", "cos"],
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
        // 不明: require("../assets/images/fukidashi/fumei.png"),
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
    coMap: function (playerId) {
      let roleName;
      this.cos.forEach((co) => {
        if (playerId === co.id) {
          roleName = co.role;
        }
      });

      if(roleName){
        return roleName;
      }else{
        return "murabito";
      }
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
    justify-content: space-between;
    padding: 0 1rem;

    .player {
      display: grid;
      grid-template-columns: minmax(80px, 100px) 1fr;
      column-gap: 16px;
      align-items: center;
      width: 30%;

      .player-icon {
        grid-row: 1/3;
        grid-column: 1/2;
        width: 100px;
        height: 100px;

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
        grid-row: 2/3;
        grid-column: 2/3;
      }

      .me {
        text-decoration: underline;
      }
    }
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
  }
}
</style>