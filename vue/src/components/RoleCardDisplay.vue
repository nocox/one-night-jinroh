<template>
  <section class="role-card-display-area">
    <div class="player">
      <h3>あなたです</h3>
      <RoleCard :roleName="playerRole.roleName" :playerName="playerName" />
    </div>

    <div class="not-player">
      <h3>おやすみ中のカード</h3>
      <RoleCard
        v-for="boardCard in boardCards"
        :key="boardCard.index"
        :roleName="boardCard.roleName"
      />
    </div>

    <div class="other-player">
      <h3>他のプレイヤー</h3>
      <RoleCard
        v-for="player in otherPlayerList"
        :key="player.id"
        :roleName="'不明'"
        :playerName="player.name"
      />
      <!-- :roleName="player.role.roleName" -->
    </div>
  </section>
</template>

<script>
import RoleCard from "@/components/RoleCard.vue";

export default {
  name: "RoleCardDisplay",
  components: { RoleCard },
  props: {
    playerRole: {
      type: Object,
      required: true,
    },
    playerName: {
      type: String,
      required: true,
    },
    otherPlayerList: {
      type: Array,
      required: true,
    },
    boardCards: {
      type: Array,
      default: () => {
        return [
          {
            roleId: -1,
            roleName: "不明",
          },
          {
            roleId: -1,
            roleName: "不明",
          },
        ];
      },
    },
  },
};
</script>

<style lang="scss" scoped>
.role-card-display-area {
  justify-content: center;
  text-align: center;
  display: flex;
  flex-wrap: wrap;
}

.player {
  display: flex;
  width: 33%;
  justify-content: center;
  flex-wrap: wrap;

  h3 {
    width: 100%;
  }
  .card-wrapper {
    padding-top: 16px;
    width: 100%;
  }
}

.not-player {
  width: 66%;
  display: flex;
  flex-wrap: wrap;
  justify-content: space-evenly;
  h3 {
    width: 100%;
  }
  .card-wrapper {
    padding-top: 16px;
    width: 50%;
  }
}

.other-player {
  padding-top: 24px;
  width: 100%;
  display: flex;
  flex-wrap: wrap;

  h3 {
  text-align: left;  
    width: 100%;
  }
  .card-wrapper {
    padding-top: 16px;
  }
}

@media screen and (max-width: 639px) {
  h3 {
    font-size: 16px;
  }

.other-player {
  
  
  display: grid;
  grid-template-columns: 1fr 1fr 1fr;

  h3 {
    width: 100%;
    grid-column: 1/4;
  }
  .card-wrapper {
    padding-top: 16px;
  }
}
}
</style>