<template>
  <figure class="winner">
    <figcaption class="winner-text">
      {{ judgeResult[judge].text }}
    </figcaption>
    <img class="winner-image" :src="judgeResult[judge].imagePath" :alt="judgeResult[judge].text" />
  </figure>
</template>

<script>
import { JUDGE_RESULT } from "@/resultInfo.js";

export default {
  name: "ResultImage",
  props: ["judge"],
  computed: {
    judgeResult() {
      return JUDGE_RESULT;
    }
  }
};
</script>

<style lang="scss" scoped>
.winner {
  max-width: 21rem;
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
    .winner-text{
      font-size: 20px;
      word-break: keep-all;
    }
  }
}
</style>
