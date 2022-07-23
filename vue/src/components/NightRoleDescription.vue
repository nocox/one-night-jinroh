<template>
  <div class="role-description__inner">
    <figure class="card-wrapper">
      <img :src="RoleImage[roleName]" :alt="RoleImage[roleName]" />
      <figcaption v-if="roleName != '不明'">{{ roleName }}</figcaption>
    </figure>
    <div class="description">
      <p>あなたの役職は『{{ roleName }}』です。</p>
      <section>
        <p>夜の行動</p>
        <p>{{ RoleDescription[roleName] }}</p>
      </section>
    </div>
  </div>
</template>

<script>
export default {
  name: "NightRoleDescription",
  components: {},
  props: {
    roleName: {
      type: String,
      required: true,
    },
  },
  data() {
    return {
      RoleCard: {
        id: 0,
        name: "---",
        img: "---",
      },
      RoleImage: {
        不明: require("../assets/images/card.png"),
        人狼: require("../assets/images/chara/chara1.png"),
        村人: require("../assets/images/chara/chara2.png"),
        占い師: require("../assets/images/chara/chara3.png"),
        怪盗: require("../assets/images/chara/chara4.png"),
        狂人: require("../assets/images/chara/chara5.png"),
        吊り人: require("../assets/images/chara/chara6.png"),
      },
      RoleDescription: {
        人狼: `他のプレイヤーに人狼がいるかどうか確認できます。`,
        村人: `夜の行動はありません。
                OKボタンを押して夜が明けるのを待ちましょう。`,
        占い師: `『他のプレイヤー』ひとりまたは『おやすみ中のカード』の役職を確認できます。`,
        怪盗: `『他のプレイヤー』ひとりと役職を交換できます。
                勝利条件は交換後の役職に準じます。`,
        狂人: `夜の行動はありません。
                OKボタンを押して夜が明けるのを待ちましょう。`,
        吊り人: `夜の行動はありません。
                OKボタンを押して夜が明けるのを待ちましょう。`,
      },
    };
  },
};
</script>

<style lang="scss" scoped>
.role-description__inner{
  display: grid;
  grid-template-columns: 1fr 2fr;
  justify-items: center;
}

.description{
  width: 100%;
  padding: 1em;
  text-align: left;
  background-color: #fff;
  border-radius: 8px;
  box-shadow: 5px 5px 5px rgb(0 0 0 / 10%);
}

.card-wrapper{
  max-width: 160px;
  
}

img {
  width: 100%;
  height: auto;
}

@media screen and (max-width: 639px) {
  .role-description__inner {
    grid-template-columns: 1fr;
    row-gap: 1rem;
  }
}
</style>