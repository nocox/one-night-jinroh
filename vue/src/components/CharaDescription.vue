<template>
  <div class="chara-description">
    <figure>
      <img :src="role.img" :alt="role.name" />
      <figcaption>{{ role.name }}</figcaption>
    </figure>
    <div class="description" v-html="role.description"></div>
  </div>
</template>

<script>
export default {
  name: "CharaDescription",
  props: ["role"],
};
</script>

<style lang="scss" scoped>
.chara-description {
  display: grid;
  grid-template-columns: 10rem 1fr;
  align-items: center;
  justify-content: center;
  max-width: 640px;
  margin: 1rem auto 0;

  @media screen and (max-width: 639px) {
    grid-template-columns: 20rem;
  }
}

figure {
  text-align: center;

  img {
    width: 8rem;
    height: auto;
  }
}
</style>
