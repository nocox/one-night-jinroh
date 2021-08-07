<template>
  <section class="role-card-display-area">
    <div class="player">
      <RoleCard :roleName="playerRole.roleName" :playerName="playerName" />
    </div>

    <div class="role-description">
      <div class="role-description__inner">
        <NightRoleDescription :roleName="playerRole.roleName" />
      </div>
    </div>

    <div class="action">
      <NightAction :roleName="playerRole.roleName" :otherPlayerList="otherPlayerList" />
    </div>
    
  </section>
</template>

<script>
import RoleCard from "@/components/RoleCard.vue";
import NightAction from "@/components/NightAction.vue";
import NightRoleDescription from "@/components/NightRoleDescription";

export default {
  name: "RoleCardDisplay",
  components: { RoleCard, NightAction, NightRoleDescription },
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
  display: grid;
  grid-template-columns: 1fr 2fr;
}

.player {
  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: column;
  background-color: #eee;  
  padding: 1em;
}

.role-description{
background-color: #eee;  
padding: 24px;
  .role-description__inner{
    background-color: #fff;
    padding: 1em;
    text-align: left;
    border-radius: 8px;
    box-shadow: 5px 5px 5px rgba(0,0,0,.1);
    
  }
}


.action{
  margin-top: 16px;
  padding: 1em;
  border: 3px solid #eee;
grid-column: 1/3;  
height  :20rem;
  border-radius: 8px;
}


@media screen and (max-width: 639px) {
  .role-card-display-area {
    grid-template-columns: 1fr;
  }
  .action{
    grid-column: 1/2;
  }
  h3 {
    font-size: 16px;
  }

}
</style>