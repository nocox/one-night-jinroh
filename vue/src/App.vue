<template>
  <div id="app">
    <div class="scroll-hint-top" v-show="is_scrollableUp"></div>
    <router-view />
    <div id="nav">
      <router-link to="/">Home</router-link> |
      <router-link to="/sample">Sample</router-link> |
      <router-link to="/top">Top Page</router-link> |
      <router-link to="/room">Room Top Page</router-link> |
      <router-link to="/night">Night Page</router-link> |
      <router-link to="/talk">Talk Page</router-link> |
      <router-link to="/vote">Vote Page</router-link> |
      <router-link to="/tally">Tally Page</router-link> |
      <router-link to="/result">Result Page</router-link> |
    </div>
    <div class="scroll-hint-bottom" v-show="is_scrollableDown"></div>
  </div>
</template>

<script>
// import ScrollHint from "@/components/ScrollHint.vue";
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
  src: url("./assets/fonts/Mamelon-3-Hi-Regular.woff2") format("woff2");
  font-weight: 400;
}

@font-face {
  font-family: "Mamelon";
  src: url("./assets/fonts/Mamelon-5-Hi-Regular.woff2") format("woff2");
  font-weight: 700;
}

.pc {
  display: block;
}
.sp {
  display: none;
}

#app {
  font-family: Mamelon, Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: left;
  letter-spacing: 0.05em;
  color: #212121;
}

* {
  box-sizing: border-box;
  padding: 0;
  margin: 0;
}

main {
  max-width: 1200px;
  margin: auto;
  padding: 0 1.5rem;
}

section {
  margin: 2rem auto 2rem auto;
}

h2 {
  margin: 0.5rem auto 0.5rem auto;
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

@media screen and (max-width: 1024px) {
  main{
    max-width: 24rem;
  }
  .pc {
    display: none;
  }
  .sp {
    display: block;
  }
}
</style>
