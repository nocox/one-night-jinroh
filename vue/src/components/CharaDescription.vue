<template>
  <div class="chara-description">
    <figure>
      <img :src="role.img" :alt="role.name" />
      <figcaption> <h3>{{ role.name }}</h3></figcaption>
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

  figure {
    text-align: center;

    img {
      width: 8rem;
      height: auto;
    }
  }
}

@media screen and (max-width: 639px) {
  .chara-description {
    grid-template-columns: 20rem;
  }
}
</style>