<template>
  <div>
    <h2>役職一覧</h2>
    <ul>
      <li v-for="(count, name) in $store.state.rolls" v-bind:key="name">
        <img :src="getRole(name).icon" alt="" />
        <span> {{ name }}</span>
        <span> × </span>
        <span>{{ count }} </span>
      </li>
    </ul>
  </div>
</template>

<script>
import { getRoleInfo } from "@/roleInfo.js";

export default {
  name: "DisplayRolls.vue",
  methods: {
    getRole(name) {
      return getRoleInfo(name)
    }
  },
};
</script>

<style lang="scss" scoped>
div {
  display: flex;
  flex-wrap: wrap;
  justify-content: center;
  width: 100%;
  padding: 1rem;
  background-color: #eee;

  h2 {
    width: 100%;
    text-align: center;
  }

  ul {
    list-style-type: none;
  }

  li {
    display: flex;
    column-gap: 8px;
    align-items: center;
    justify-content: space-between;
    margin-top: 16px;
  }

  img {
    width: 100%;
    max-width: 25px;
    height: auto;
    max-height: 25px;
    border-radius: 5px;
  }
}
</style>