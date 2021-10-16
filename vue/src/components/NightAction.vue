<template>
  <div class="night-action-area">
    <div class="kaito" v-if="roleName == '怪盗'">
      <p>役職を入れ替える相手を選んでください。</p>
      <form action="">
        <ul>
          <li
            v-for="otherPlayer in otherPlayerList"
            v-bind:key="otherPlayer.id"
          >
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
        <myButton :text="'選んだ相手と入れ替える'" :method="''" />
      </form>
    </div>
    <div class="jinroh" v-else-if="roleName == '人狼'">
      <myButton :text="'仲間を確認する'" :method="''" />
    </div>
    <div class="uranai" v-else-if="roleName == '占い師'">
      <nightUranaiAction :canSelectedPlayers="otherPlayerList" />
    </div>
    <div class="no-action" v-else>村人・狂人・吊人はアクションなし</div>
  </div>
</template>

<script>
import myButton from "@/components/Button.vue";
import nightUranaiAction from "@/components/NightUranaiAction.vue"

export default {
  name: "RoleCardDisplay",
  components: { myButton, nightUranaiAction },
  data() {
    return {
      checkedPlayerID: 0,
    };
  },
  props: {
    roleName: {
      type: String,
      required: true,
    },
    otherPlayerList: {
      type: Array,
      required: true,
    },
  },
};
</script>

<style lang="scss" scoped>
.night-action-area{
    text-align: left;
}

li{
    list-style: none;
}
@media screen and (max-width: 639px) {
}
</style>