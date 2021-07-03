<template>
  <section class="role-card-display-area">
    <div class="me">
      <h3 class="area-title">あなたです</h3>
      <RoleCard :roleName="playerRole.roleName" />
      <p class="player-name">{{ playerName }}</p>
      <p class="player-role">役職：{{ playerRole.roleName }}</p>
    </div>
    <div class="others">
      <h3 class="area-title">他のプレイヤー</h3>
      <ul class="card-list">
        <li
          class="card-list-item"
          v-for="player in otherPlayerList"
          :key="player.id"
        >
          <RoleCard :roleName="player.role.roleName" />
          <p class="player-name">{{ player.name }}</p>
          <p class="player-role">役職： {{ player.role.roleName }}</p>
        </li>
      </ul>
    </div>
    <div class="board-cards">
      <h3 class="area-title">おやすみ中のカード</h3>
      <ul class="card-list">
        <li
          class="board-card card-list-item"
          v-for="boardCard in boardCards"
          :key="boardCard.index"
        >
          <RoleCard :roleName="boardCard.roleName" />
        </li>
      </ul>
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
  margin: auto;
  text-align: center;

  display: grid;
  align-items: center;

  .area-title {
    margin: 0.5em;
  }

  .card-list {
    display: flex;
    flex-wrap: wrap;
    justify-content: center;
    .card-list-item {
      list-style: none;
      text-align: center;
      margin: 0 1.2rem;
    }
  }

  .board-cards {
    grid-column: 2/3;
    grid-row: 1/2;
  }
  .me {
    grid-column: 1/2;
    grid-row: 1/3;
  }
  .others {
    margin-top: 2.4rem;
    grid-column: 2/3;
    grid-row: 2/3;
  }
  .player-name,
  .player-role {
    margin: 0;
  }
}

@media screen and (max-width: 639px) {
  .role-card-display-area {
    .area-title {
      font-size: 12px;
    }
    .player-name,
    .player-role {
      font-size: 12px;
    }

    .card-list {
      .card-list-item {
        margin: 0 auto;
      }
    }
    .me {
      grid-column: 1/2;
      grid-row: 1/2;
    }
    .others {
      grid-column: 1/2;
      grid-row: 2/3;
    }
    .board-cards {
      // margin-top: 2.4rem;
      grid-column: 1/2;
      grid-row: 3/4;
    }
  }
}
</style>