<template>
  <section class="co-area">
    <div class="player-wrapper">
      <!-- 自分 -->
      <Player
        :playerName="player.playerName"
        :roleName="player.playerRole.roleName"
        :coRole="this.coRole"
        :selectedPlayers="selectedPlayers"
        :myself="true"
      />
      <!-- 他のプレイヤー -->
      <Player
        :playerName="val.name"
        :roleName="val.role.roleName"
        :coRole="'村人'"
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
  name: "CoArea",
  props: ["otherPlayerList", "player", "coRole", "cos", "selectedPlayers"],
  components: { Player },
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
      const roleName = this.cos.find(co => co.id == playerId)

      if(roleName){
        return roleName.role;
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
    flex-wrap:wrap;
    row-gap: 1rem;
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