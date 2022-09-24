<template>
  <article  class="result-player" >
    <figure class="player-icon">
      <img :src="$getRole(role).icon" alt="" />
    </figure>

    <figure class="co-icon">
      <img
        class="co-icon__img"
        :src="coRole.trim() ? $getRole(coRole).co : ''"
        :alt="''"
      />
    </figure>
    <span class="player-name" :class="{ me: this.myself }">
      {{ playerName }}</span>
    <span class="player-comment">
      {{ comment }}
    </span>
  </article>
</template>

<script>
export default {
  name: "PlayerResult",
  props: [
    "playerName",
    "role",
    "coRole",
    "selectedPlayers",
    "myself",
    "comment",
  ]
};
</script>

<style lang="scss" scoped>

.result-player {
  display: grid;
  grid-template-columns: auto 1fr;
  column-gap: 1rem;
  align-items: center;
  width: 100%;
  margin: auto;
  margin-top: 1.5rem;

  .player-icon {
    display: flex;
    grid-row: 1/3;
    grid-column: 1/2;
    justify-self: center;
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
    width: 30px;
    height: 30px;

    .co-icon__img {
      width: 100%;
      height: auto;
    }
  }

  .player-name {
    grid-row: 3/4;
    grid-column: 1/3;
    padding-top: 0.5rem;
    font-size: 18px;
  }

  .me {
    text-decoration: underline;
  }

  .player-comment {
    grid-row:2/3;
    grid-column: 2/3;
  }
}

@media screen and (max-width: 639px) {
  .result-player {
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