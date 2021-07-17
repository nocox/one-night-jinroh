<template>
  <section class="winner">
    <form action="">
      <h3>デバッグ用 勝者選択リスト</h3>
      <label v-for="(val, key) in result_image_path" :key="key">
        <input type="radio" :value="key" v-model="result" />{{ key }} <br />
      </label>
    </form>
    <div>
      <img class="winner-image" :src="result_image_path[result]" alt="" />
    </div>
  </section>
</template>

<script>


export default {
  name: "resultImage",
  props: ["result"],
  data() {
    return {
      result_image_path: {
        平和村成功: require("../assets/images/result1.png"),
        村人側の勝利: require("../assets/images/result1.png"),
        人狼側の勝利: require("../assets/images/result2.png"),
        "人狼側の勝利(潜伏成功)": require("../assets/images/result2.png"),
        吊人の勝利: require("../assets/images/result3.png"),
      },
    };
  },
};
</script>

<style lang="scss" scoped>
div {
  text-align: center;
  .winner-image {
    width: 100%;
    height: auto;
    max-width: 40rem;
  }
}

form {
  text-align: center;
}

@media screen and (max-width: 639px) {
  div {
    .winner-image {
      width: 15rem;
    }
  }
}
</style>