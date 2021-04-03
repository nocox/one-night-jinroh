<template>
  <a v-on:click="method" >{{ text }} </a>
</template>

<script>
export default {
  name: "button",
  props:['text', 'method'],
};
</script>

<style lang="scss" scoped>
$color: #50a0f6;

a {
  display: inline-block;
  // max-width: 120px;
  margin-top: 10px;
  padding: 5px 40px;
  border-radius: 30px;
  border: 1px solid $color;
  background-color: white;
  cursor: pointer;

  text-decoration: none;
  color: $color;

  &:hover {
    background-color: $color;
    transition: 0.3s;
    color: white;
  }
  &:active {
    background-color: lighten($color, 20%);
    transition: 0.1s;
  }
}
</style>