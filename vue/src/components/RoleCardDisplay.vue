<template>
  <section class="role-card-display-area">
    <div class="me">
      <h3>あなたです</h3>
      <RoleCard :roleName="playerRole.roleName" />
      <p>{{ playerName }}</p>
      <p>役職：{{ playerRole.roleName }}</p>
    </div>
    <div class="others">
      <h3>他のプレイヤー</h3>
      <ul>
        <li v-for="player in otherPlayerList" :key="player.id">
          <RoleCard :roleName="player.role.roleName" />
          <p>{{ player.name }}</p>
          <p>役職： {{ player.role.roleName }}</p>
        </li>
      </ul>
    </div>
    <div class="board-cards">
      <h3>おやすみ中のカード</h3>
      <ul>
        <li
          class="board-card"
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
      required: true
    },
    playerName: {
      type: String,
      required: true
    },
    otherPlayerList: {
      type: Array,
      required: true
    },
    boardCards: {
      type: Array,
      default: () => {
        return [
          {
            roleId: -1,
            roleName: "不明"
          },
          {
            roleId: -1,
            roleName: "不明"
          },
        ]
      }
    }
  }
};
</script>

<style lang="scss" scoped>
h3 {
  margin: 0.5em;
}

ul {
  display: flex;
  flex-wrap: wrap;
  justify-content: center;
  li {
    list-style: none;
    text-align: center;
    margin: 0 1.2rem;
  }
}

p {
  margin: 0;
}

.role-card-display-area {
  margin: auto;
  text-align: center;

  display: grid;
  align-items: center;

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
}

@media screen and (max-width: 639px) {
  .role-card-display-area {
    .me {
      grid-column: 1/2;
      grid-row: 1/2;
    }
    .others {
      grid-column: 1/2;
      grid-row: 2/3;
    }
    .board-cards {
      margin-top: 2.4rem;
      grid-column: 1/2;
      grid-row: 3/4;
    }
  }
}
</style>