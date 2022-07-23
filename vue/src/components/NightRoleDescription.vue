<template>
  <div class="role-description__inner">
    <figure class="card-wrapper">
      <img :src="getRole.img" :alt="roleName" />
      <figcaption v-if="roleName != '不明'">{{ roleName }}</figcaption>
    </figure>
    <div class="description">
      <p>あなたの役職は『{{ roleName }}』です。</p>
      <section>
        <p>夜の行動</p>
        <p>{{ getRole.desc }}</p>
      </section>
    </div>
  </div>
</template>

<script>
export default {
  name: "NightRoleDescription",
  props: {
    roleName: {
      type: String,
      required: true,
    },
  },
  computed: {
    getRole() {
      return ROLE_INFO[this.roleName]
    }
  },
};

// FIX ME：KEYを日本語から英語に直す
// FIX ME：ROLE_INFOの定義をJSファイルに切り出して、ライブラリとして参照できるようにする
const ROLE_INFO = {
  不明: { img: require("../assets/images/card.png"), desc: "不明" },
  人狼: {
    img: require("../assets/images/chara/chara1.png"),
    desc: `他のプレイヤーに人狼がいるかどうか確認できます。`,
  },
  村人: {
    img: require("../assets/images/chara/chara2.png"),
    desc: `夜の行動はありません。`,
  },
  占い師: {
    img: require("../assets/images/chara/chara3.png"),
    desc: `『他のプレイヤー』ひとりまたは『おやすみ中のカード』の役職を確認できます。`,
  },
  怪盗: {
    img: require("../assets/images/chara/chara4.png"),
    desc: `『他のプレイヤー』ひとりと役職を交換できます。
            勝利条件は交換後の役職に準じます。`,
  },
  狂人: {
    img: require("../assets/images/chara/chara5.png"),
    desc: `夜の行動はありません。
            OKボタンを押して夜が明けるのを待ちましょう。`,
  },
  吊り人: {
    img: require("../assets/images/chara/chara6.png"),
    desc: `夜の行動はありません。
            OKボタンを押して夜が明けるのを待ちましょう。`,
  },
};
</script>

<style lang="scss" scoped>
.role-description__inner {
  display: grid;
  grid-template-columns: 1fr 2fr;
  justify-items: center;
}

.description {
  width: 100%;
  padding: 1em;
  text-align: left;
  background-color: #fff;
  border-radius: 8px;
  box-shadow: 5px 5px 5px rgb(0 0 0 / 10%);
}

.card-wrapper {
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