<template>
  <section>
    <img :src="result_image_path[result]" alt="" />
  </section>
</template>

<script>
export default {
  name: "resultImage",
  props: ["result"],
  data() {
    return {
      result_image_path:{
          "平和村成功": require("../assets/images/result1.png")
      }
    };
  },
};
</script>

<style lang="scss" scoped>
img{
    width:20rem;
}
</style>