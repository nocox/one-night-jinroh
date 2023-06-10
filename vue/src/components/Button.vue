<template>
  <a v-on:click="method" >{{ text }} </a>
</template>

<script>
export default {
  name: "myButton",
  props:['text', 'method'],
};
</script>

<style lang="scss" scoped>
$color: #50a0f6;

a {
  display: inline-block;
  padding: 5px 40px;
  margin-top: 10px;
  color: $color;
  text-decoration: none;
  cursor: pointer;
  background-color: white;
  border: 1px solid $color;
  border-radius: 30px;

  &:hover {
    color: white;
    background-color: $color;
    transition: 0.3s;
  }

  &:active {
    background-color: lighten($color, 20%);
    transition: 0.1s;
  }
}

a.disabled {
    color: gray;
    border: 1px solid gray;
    pointer-events: none;
  
}
</style>