<template>
  <div class="role-description__inner">
    <figure class="card-wrapper">
      <img :src="$getRole(roleName).img" :alt="roleName" />
      <figcaption v-if="roleName != '不明'">{{ roleName }}</figcaption>
    </figure>
    <div class="description">
      <p>あなたの役職は『{{ roleName }}』です。</p>
      <section>
        <p>夜の行動</p>
        <p>{{ $getRole(roleName).desc }}</p>
      </section>
    </div>
  </div>
</template>

<script>
export default {
  name: "NightRoleDescription",
  props: {
    roleName: {
      type: String,
      required: true,
    },
  },
};
</script>

<style lang="scss" scoped>
.role-description__inner {
  display: grid;
  grid-template-columns: 1fr 2fr;
  justify-items: center;
}

.description {
  width: 100%;
  padding: 1em;
  text-align: left;
  background-color: #fff;
  border-radius: 8px;
  box-shadow: 5px 5px 5px rgb(0 0 0 / 10%);
}

.card-wrapper {
  max-width: 160px;
}

img {
  width: 100%;
  height: auto;
}

@media screen and (max-width: 639px) {
  .role-description__inner {
    grid-template-columns: 1fr;
    row-gap: 1rem;
  }
}
</style>