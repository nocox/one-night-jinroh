<template>
  <div>
    <p>あなたの役職は『{{ roleName }}』です。</p>
    <section>
      <p>夜の行動</p>
      <p>{{ RoleList[roleName] }}</p>
    </section>
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
      RoleList: {
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
</style>