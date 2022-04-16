<template>
  <figure class="winner">
    <figcaption class="winner-text">
      {{ judgeResults[judge].text }}
    </figcaption>
    <img class="winner-image" :src="judgeResults[judge].imagePath" :alt="judgeResults[judge].text" />
  </figure>
</template>

<script>
export default {
  name: "resultImage",
  props: ["judge"],
  mounted() {
    this.$emit("getJudgeText", this.judgeResults[this.judge].text);
  },
  data() {
    return {
      judgeResults: {
        FAIL_PEACE_VILLAGE: {
          text: "平和村失敗",
          imagePath: require("../assets/images/result2.png"),
        },
        SIMPLE_JINROH_WIN: {
          text: "人狼陣営の勝利",
          imagePath: require("../assets/images/result2.png"),
        },
        SIMPLE_VILLAGE_WIN: {
          text: "村人陣営の勝利",
          imagePath: require("../assets/images/result1.png"),
        },
        SUCCESS_HIDE_JINROH_WIN: {
          text: "人狼陣営の勝利（潜伏成功）",
          imagePath: require("../assets/images/result2.png"),
        },
        SUCCESS_PEACE_VILLAGE: {
          text: "平和村成功",
          imagePath: require("../assets/images/result1.png"),
        },
        TURIBITO_WIN: {
          text: "吊り人の勝利",
          imagePath: require("../assets/images/result3.png"),
        }
      },
    };
  },
};
</script>

<style lang="scss" scoped>
.winner {
  max-width: 20rem;
  margin: auto;
  text-align: center;

  .winner-text {
    font-size: 24px;
    font-weight: bold;
  }

  .winner-image {
    width: 100%;
    height: auto;
  }
}

@media screen and (max-width: 639px) {
  .winner {
    max-width: 15rem;
  }
}
</style>
