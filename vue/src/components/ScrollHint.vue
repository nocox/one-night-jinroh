<template>
  <div>
    <div class="scroll-hint-top" v-show="is_scrollableUp"></div>
    <div class="scroll-hint-bottom" v-show="is_scrollableDown"></div>
  </div>
</template>

<script>
export default {
  name: "ScrollHint",
  data() {
    return {
      is_scrollableUp: false,
      is_scrollableDown: false,
    };
  },
  methods: {
    check_scrollableUp: function () {
      if (window.pageYOffset > 0) {
        this.is_scrollableUp = true;
      } else {
        this.is_scrollableUp = false;
      }
    },
    check_scrollableDown: function () {
      const scrollHeight = Math.max(
        document.body.scrollHeight,
        document.documentElement.scrollHeight,
        document.body.offsetHeight,
        document.documentElement.offsetHeight,
        document.body.clientHeight,
        document.documentElement.clientHeight
      );
      const pageMostBottom = scrollHeight - window.innerHeight;
      const scrollTop = window.pageYOffset || document.documentElement.scrollTop;
      if (scrollTop < pageMostBottom) {
        this.is_scrollableDown = true;
      } else {
        this.is_scrollableDown = false;
      }
    },
  },
  mounted: function () {
      this.check_scrollableUp();
      this.check_scrollableDown();
  },
  created() {
    addEventListener("scroll load resize", this.check_scrollableUp);
    addEventListener("scroll load resize", this.check_scrollableDown);
  },
  destoryed() {
    removeEventListener("scroll load resize", this.check_scrollableUp);
    removeEventListener("scroll load resize", this.check_scrollableDown);
  },
};
</script>

<style lang="scss" scoped>
.scroll-hint-top {
  position: fixed;
  top: 0;
  z-index: 1;
  width: 100vw;
  height: 24px;
  background: linear-gradient(rgb(0 0 0 / 50%), rgb(0 0 0 / 0%));
  background-color: white;
  background-repeat: no-repeat;
  background-size: 100% 50px;
}

.scroll-hint-bottom {
  position: fixed;
  bottom: 0;
  z-index: 1;
  width: 100vw;
  height: 24px;
  background: linear-gradient(rgb(0 0 0 / 0%), rgb(0 0 0 / 50%));
  background-color: white;
  background-repeat: no-repeat;
  background-size: 100% 50px;
}
</style>