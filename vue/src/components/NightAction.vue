<template>
  <div class="night-action-area">
    <div class="kaito" v-if="roleName == '怪盗'">
      <nightKaitoAction :canSelectedPlayers="otherPlayerList" />
    </div>
    <div class="jinroh" v-else-if="roleName == '人狼'">
      <nightJinrohAction />
    </div>
    <div class="uranai" v-else-if="roleName == '占い師'">
      <nightUranaiAction :canSelectedPlayers="otherPlayerList" />
    </div>
    <div class="no-action" v-else>村人・狂人・吊人はアクションなし</div>
  </div>
</template>

<script>
import nightUranaiAction from "@/components/NightAction/NightUranaiAction.vue"
import nightKaitoAction from "@/components/NightAction/NightKaitoAction.vue"
import nightJinrohAction from "@/components/NightAction/NightJinrohAction.vue"

export default {
  name: "RoleCardDisplay",
  components: { nightUranaiAction, nightKaitoAction, nightJinrohAction },
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