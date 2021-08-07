<template>
  <div class="card-wrapper">
    <img :src="RoleList[roleName]" :alt="RoleList[roleName]" />

    <p v-if="playerName">{{ playerName }}</p>
    <p v-if="roleName != '不明'">{{ roleName }}</p>
  </div>
</template>

<script>
export default {
  name: "rolecard",
  props: {
    roleName: String,
    playerName: String,
  },
  data() {
    return {
      RoleCard: {
        id: 0,
        name: "---",
        img: "---",
      },
      RoleList: {
        不明: require("../assets/images/card.png"),
        人狼: require("../assets/images/chara/chara1.png"),
        村人: require("../assets/images/chara/chara2.png"),
        占い師: require("../assets/images/chara/chara3.png"),
        怪盗: require("../assets/images/chara/chara4.png"),
        狂人: require("../assets/images/chara/chara5.png"),
        吊り人: require("../assets/images/chara/chara6.png"),
      },
    };
  },
};
</script>

<style lang="scss" scoped>
.card-wrapper{
  max-width: 160px;
  
}

img {
  width: 100%;
  height: auto;
}
</style>