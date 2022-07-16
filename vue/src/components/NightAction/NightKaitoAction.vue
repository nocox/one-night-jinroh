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
            :disabled="isDisabled"
          />
          {{ otherPlayer.name }}
        </label>
      </li>
    </ul>
    <myButton :text="'選んだ相手と入れ替える'" :method="kaito" :class="{'disabled': isDisabled}" />
    <p style="color:red" v-if="errorMessage">{{ errorMessage }}</p>

    <div v-if="kaitoResult.actLog">
        <!-- FIXME: ここ手抜きしました．．フロント側で加工し表示するべき -->
        <p>{{ kaitoResult.actLog }}</p>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import myButton from "@/components/Button.vue";
import { JINROH_API_BASE_URL } from "../../Env";

export default {
  name: "RoleCardDisplay",
  components: { myButton },
  data() {
    return {
      checkedPlayerID: null,
      kaitoResult: {
          actLog: null
      },
      isDisabled: false,
      errorMessage: "",
    };
  },
  props: {
    canSelectedPlayers: {
      type: Array,
      required: true,
    },
  },
  mounted() {
    axios
        .get(
          JINROH_API_BASE_URL + "/night/kaito", { withCredentials: true })
        .then((response) => {
            const data = response.data;
            console.log({data});
            if (data !== null) {
              this.checkedPlayerID = response.data.selectedParticipantId;
              this.kaitoResult.actLog = response.data.actLog;
              this.isDisabled = true;
            }
        });
  },
  methods: {
    kaito: function () {
      
      this.errorMessage = this.checkedPlayerID
      ? "" : "プレイヤーが選ばれていません";
      if(this.errorMessage) return;

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
          this.isDisabled = true;
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