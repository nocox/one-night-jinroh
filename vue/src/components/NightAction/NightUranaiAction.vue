<template>
  <div>
    <p>占うプレイヤーを選んでください</p>
    <ul>
      <li v-for="player in canSelectedPlayers" v-bind:key="player.id">
        <label>
          <input
            type="radio"
            v-model="selectedUranai"
            v-bind:value="'PLAYER:' + player.id"
            :disabled="isSelected"
          />
          {{ player.name }}
        </label>
      </li>
      <li>
        <label>
          <input
            type="radio"
            v-model="selectedUranai"
            value="HOLIDAY_ROLES"
            :disabled="isSelected"
          />
          お休み中のロール
        </label>
      </li>
    </ul>

    <myButton class="btn" :method="uranai" :text="'占う'" />

    <p>占い結果</p>
    <div v-if="uranaiResult.status == 'PLAYER'">
      <p>
        {{ uranaiResult.user.userName }}は，{{
          uranaiResult.roles[0].roleName
        }}でした
      </p>
    </div>
    <div v-if="uranaiResult.status == 'HOLIDAY_ROLES'">
      <p>
        お休み中のカードは，{{ uranaiResult.roles[0].roleName }}と{{
          uranaiResult.roles[1].roleName
        }}でした
      </p>
    </div>
    <div v-if="uranaiResult.status == 'NOT_CHOOSE'">
      <p>占いを実行しませんでした</p>
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
      checkedPlayerID: 0,
      selectedUranai: "",
      uranaiResult: {
        status: "",
        user: {
          userId: null,
          userName: "",
        },
        participantId: null,
        roles: [
          {
            roleId: null,
            roleName: "",
          },
        ],
      },
    };
  },
  props: {
    canSelectedPlayers: {
      type: Array,
      required: true,
    },
  },
  methods: {
    uranai: function () {
      var uranaiStatus =
        this.selectedUranai != ""
          ? this.selectedUranai.indexOf("PLAYER") != -1
            ? "PLAYER"
            : "HOLIDAY_ROLES"
          : "NOT_CHOOSE";
      var playerId =
        this.selectedUranai.indexOf("PLAYER:") != -1
          ? Number(this.selectedUranai.split(":")[1])
          : null;

      axios
        .post(
          JINROH_API_BASE_URL + "/night/uranai",
          JSON.stringify({ status: uranaiStatus, participantId: playerId }),
          {
            withCredentials: true,
            headers: {
              "Content-Type": "application/json",
            },
          }
        )
        .then((response) => {
          this.uranaiResult = response.data;
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