<template>
  <div id="app">
    <div class="scroll-hint-top" v-show="is_scrollableUp"></div>
    <router-view />
    <div class="scroll-hint-bottom" v-show="is_scrollableDown"></div>
  </div>
</template>

<script>
export default {
  name: "App",
  components: {
    
  },data() {
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
    addEventListener("scroll", this.check_scrollableUp);
    addEventListener("scroll", this.check_scrollableDown);
  },
  destoryed() {
    removeEventListener("scroll", this.check_scrollableUp);
    removeEventListener("scroll", this.check_scrollableDown);
  },
};
</script>

<style lang="scss">
@font-face {
  font-family: "Mamelon";
  font-weight: 400;
  src: url("./assets/fonts/Mamelon-3-Hi-Regular.woff2") format("woff2");
}

@font-face {
  font-family: "Mamelon";
  font-weight: 700;
  src: url("./assets/fonts/Mamelon-5-Hi-Regular.woff2") format("woff2");
}

.pc {
  display: block;
}

.sp {
  display: none;
}

#app {
  font-family: Mamelon, Avenir, Helvetica, Arial, sans-serif;
  color: #212121;
  text-align: left;
  letter-spacing: 0.05em;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
}

* {
  box-sizing: border-box;
  padding: 0;
  margin: 0;
}

main {
  max-width: 1200px;
  padding: 0 1.5rem;
  margin: auto;
}

section {
  margin: 2rem auto;
}

h2 {
  margin: 0.5rem auto;
  font-size: 1.25rem;
}

#nav {
  padding: 30px;
}

#nav a {
  font-weight: bold;
  color: #2c3e50;
}

#nav a.router-link-exact-active {
  color: #42b983;
}

.scroll-hint-top {
  position: fixed;
  top: 0;
  z-index: 1;
  width: 100vw;
  height: 24px;
  background: linear-gradient(rgb(0 0 0 / 40%), rgb(0 0 0 / 0%));
}

.scroll-hint-bottom {
  position: fixed;
  bottom: 0;
  z-index: 1;
  width: 100vw;
  height: 24px;
  background: linear-gradient(rgb(0 0 0 / 0%), rgb(0 0 0 / 40%));
}

@media screen and (max-width: 639px) {
  main{
    max-width: 639px;
  }

  .pc {
    display: none;
  }

  .sp {
    display: block;
  }
}
</style>
