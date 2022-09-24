<template>
  <div>
    <PlayerResult
        :playerName="val.playerName"
        :role="val.role"
        :coRole="val.coRole"
        :myself="val.myself"
        :comment="val.comment"
        v-for="(val, key) in playerList"
        :key="key"
    />
  </div>
</template>
<script>
import PlayerResult from "@/components/PlayerResult.vue"

export default {
  name: 'ResultPlayerArea',
  components: {PlayerResult},
  props: {
    playerList: {}
  }
}
</script>
<style lang="scss" scoped>

h2,
h3 {
  text-align: center;
}

@media screen and (max-width: 639px) {

}
</style>