<template>
  <div>
    <p>役職を入れ替える相手を選んでください。</p>
    <ul>
      <li v-for="otherPlayer in canSelectedPlayers" v-bind:key="otherPlayer.id">
        <label>
          <input
            type="radio"
            v-model="checkedPlayerID"
            :value="otherPlayer.id"
            :disabled="is_votable"
          />
          {{ otherPlayer.name }}
        </label>
      </li>
    </ul>
    <myButton :text="'選んだ相手と入れ替える'" :method="kaito" />

    <div v-if="kaitoResult.actLog">
        <!-- FIXME: ここ手抜きしました．．フロント側で加工し表示するべき -->
        <p>{{ kaitoResult.actLog }}</p>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import myButton from "@/components/Button.vue";
import { JINROH_API_BASE_URL } from "../Env";

export default {
  name: "RoleCardDisplay",
  components: { myButton },
  data() {
    return {
      checkedPlayerID: 0,
      kaitoResult: {
          actLog: null
      }
    };
  },
  props: {
    canSelectedPlayers: {
      type: Array,
      required: true,
    },
  },
  methods: {
    kaito: function () {
      axios
        .post(
            JINROH_API_BASE_URL + "/night/kaito",
            JSON.stringify({ participantId: this.checkedPlayerID }),
          {
            withCredentials: true,
            headers: {
              "Content-Type": "application/json",
            },
          }
        )
        .then((response) => {
          this.kaitoResult = response.data;
          console.log(response.data);
        })
        .catch(() => {});
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