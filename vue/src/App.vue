<template>
  <div id="app">
    <router-view />
    <div id="nav">
      <router-link to="/">Home</router-link> |
      <router-link to="/about">About</router-link> |
      <router-link to="/sample">Sample</router-link> |
      <router-link to="/top">Top Page</router-link> |
      <router-link to="/room-top">Room Top Page</router-link> |
      <router-link to="/night-page">Night Page</router-link> |
      <router-link to="/temp-room">Temp Room Page</router-link> |
      <router-link to="/temp-night">Temp Night Page</router-link>
    </div>
  </div>
</template>

<script>
export default {
  name: "App",
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

@media screen and (max-width: 1024px) {
  .pc {
    display: none;
  }
  .sp {
    display: block;
  }
}
</style>
