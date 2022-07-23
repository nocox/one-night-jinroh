<template>
  <div>
    <div v-if="participants.length">
        <p>以下のプレイヤーが人狼です</p>
        <ul>
            <li v-for="playerName in participants" v-bind:key="playerName">
                {{playerName}}
            </li>
        </ul>
    </div>
    <div v-else>
        <p>他の参加者に人狼はいません</p>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import { JINROH_API_BASE_URL } from "../../Env";

export default {
  name: "NightJinrohAction",
  data() {
    return {
        participants: []
    };
  },
  mounted() {
      this.searchJinroh()
  },
  methods: {
    searchJinroh: function () {
      axios
        .get(JINROH_API_BASE_URL + "/night/jinroh/index", { withCredentials: true })
        .then((response) => {
          this.participants = response.data.playerNames;
          console.log(response.data);
        })
        .catch(() => {
            // TODO: ユーザにエラー通知しても良いかも
        });
    },
  },
};
</script>

<style lang="scss" scoped>
.night-action-area {
  text-align: left;
}

li {
  list-style: none;
}
@media screen and (max-width: 639px) {
}
</style>