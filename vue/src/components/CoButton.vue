<template>
  <a :class="{ current: myCoRole === coRole }" @click="co(coRole)">
    <img :src="$getRole(coRole).icon" :alt="coRole"  draggable="false" ondragstart="return false;" />
  </a>
</template>

<script>
import axios from "axios";
import { JINROH_API_BASE_URL } from "../Env";

export default {
  name: "CoButton",
  props: ["myCoRole", "coRole", "playerId"],
  methods: {
    co: function (roleName) {
      axios
        .post(
          JINROH_API_BASE_URL + "/co",
          JSON.stringify({ playerId: this.playerId, role: roleName }),
          {
            withCredentials: true,
            headers: {
              "Content-Type": "application/json",
            },
          }
        )
        .then(() => {});
    },
  },
};
</script>

<style lang="scss" scoped>
a {
  cursor: pointer;
  user-select: none;

  img {
    width: 100%;
    height: auto;
    border: 5px solid transparent;
    border-radius: 50%;
    opacity: 0.5;
  }
}

.current img {
  border: 5px solid lighten(lightgreen, 0);
  box-shadow: 0 0 10px rgb(128 128 128 / 50%);
  opacity: 1;
}
</style>