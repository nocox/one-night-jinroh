<template>
  <main class="not-found-page">
    <h1>ページが見つかりません</h1>
    <myButton :text="'戻る'" :method="toPrev" />
  </main>
</template>

<script>
import myButton from "@/components/Button.vue";
export default {
  name: "NotFoundPage",
  data() {
    return {};
  },
  components: {
    myButton,
  },
  mounted() {},
  methods: {
    toPrev: function () {
      this.$router.go(-1);
    },
  },
};
</script>

<style lang="scss" scoped>
.not-found-page {
  display: grid;
  grid-template-columns: 1fr;
  justify-content: center;
  justify-items: center;
  padding: 1rem;

  @media screen and (max-width: 639px) {
    h1 {
      font-size: 1.25rem;
    }
  }
}
</style>
