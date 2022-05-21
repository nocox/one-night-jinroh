<template>
  <section class="co-button-area">
    <h2>カミングアウト</h2>
    <div class="co-icons">
      <CoButton v-for="coRole in coRoleList"
      :key="coRole.roleName"
      :myCoRole="myCoRole" 
      :coRole="coRole" 
      :playerId="playerId" />
    </div>
  </section>
</template>

<script>
import CoButton from "./CoButton.vue";

export default {
  props: ["playerId", "myCoRole"],
  components: { CoButton },
  data(){
    return {
      coRoleList:[
        {
          roleName: "murabito",
          imgSrc: require("../assets/images/chara-icon/murabito.png"),
        },
        {
          roleName: "uranaishi",
          imgSrc: require("../assets/images/chara-icon/uranaishi.png"),
        },
        {
          roleName: "kaito",
          imgSrc: require("../assets/images/chara-icon/kaito.png"),
        },
        {
          roleName: "jinroh",
          imgSrc: require("../assets/images/chara-icon/jinroh.png"),
        },
        {
          roleName: "kyojin",
          imgSrc: require("../assets/images/chara-icon/kyojin.png"),
        },
        {
          roleName: "turibito",
          imgSrc: require("../assets/images/chara-icon/tsuribito.png"),
        },
      ]
    }
  },
  mounted() {
    let nowRole = "村人";
    this.$emit("getActive", nowRole);
  },
};
</script>

<style lang="scss" scoped>
.co-button-area {
  display: grid;
  width: 100%;
  padding: 1rem 2rem;
  background-color: #eee;

  h2 {
    text-align: center;
  }

  .co-icons {
    display: grid;
    grid-template-rows: repeat(2, auto);
    grid-template-columns: repeat(3, 80px);
    row-gap: 1rem;
    column-gap: 1rem;
    align-items: center;
    justify-content: center;
    justify-items: center;
  }
}

@media screen and (max-width: 639px) {
  .co-button-area {
    .co-icons {
      grid-template-columns: repeat(2, 80px);
    }
  }
}
</style>