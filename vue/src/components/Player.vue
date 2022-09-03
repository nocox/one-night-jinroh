<template>
  <div class="player">
    <figure class="player-icon">
      <img :src="$getRole(roleName).icon" alt="" />
    </figure>

    <figure class="co-icon">
      <img
        class="co-icon__img"
        :src="$getRole(coRole).co"
        :alt="coRole"
      />
    </figure>
    <span class="player-name" :class="{ me: this.myself }">
      {{ playerName }}
    </span>
  </div>
</template>

<script>
export default {
  name: "Player",
  props: [
    "playerName",
    "roleName",
    "coRole",
    "myself",
  ],
};
</script>

<style lang="scss" scoped>
.player {
  display: grid;
  grid-template-columns: minmax(80px, 100px) 1fr;
  column-gap: 16px;
  align-items: center;
  width: 30%;

  .player-icon {
    grid-row: 1/3;
    grid-column: 1/2;
    width: 80px;
    height: 80px;

    img {
      width: 100%;
      height: auto;
      border-radius: 10px;
    }
  }

  .co-icon {
    grid-row: 1/2;
    grid-column: 2/3;
    width: 50px;
    height: 50px;

    .co-icon__img {
      width: 100%;
      height: auto;
    }
  }

  .player-name {
    grid-row: 2/3;
    grid-column: 2/3;
    font-size:14px;
  }

  .me {
    text-decoration: underline;
  }
}

@media screen and (max-width: 639px) {
  .player {
    width: 100%;
    max-width: 320px;

    .player-icon {
      width: 80px;
      height: 80px;
    }

    .co-icon {
      width: 50px;
      height: 50px;
    }
  }
}
</style>