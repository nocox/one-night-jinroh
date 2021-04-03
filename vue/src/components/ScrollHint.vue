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
  height: 24px;
  width: 100vw;
  background-color: white;
  background-size: 100% 50px;
  background: linear-gradient(rgba(0, 0, 0, 0.5), rgba(0, 0, 0, 0));

  background-repeat: no-repeat;
  z-index: 1;
}

.scroll-hint-bottom {
  position: fixed;
  bottom: 0;
  height: 24px;
  width: 100vw;
  background-color: white;

  background-size: 100% 50px;
  background: linear-gradient(rgba(0, 0, 0, 0), rgba(0, 0, 0, 0.5));
  background-repeat: no-repeat;
  z-index: 1;
}
</style>