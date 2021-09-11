<template>
  
<div class="display-rolls">
      <h2>この村にいる役職</h2>
      <ul>
        <li v-for="(count, name) in $store.state.rolls" v-bind:key="name">
          <img :src="RoleList[name]" alt="">
          <span> {{ name }}</span>
          <span> × </span>
          <span>{{ count }} </span>
        </li>
      </ul>
    </div>
</template>

<script>
export default {
  name:'DisplayRolls.vue',
  data(){
    return{
      RoleList: {
        不明: require("../assets/images/chara-icon/unknown.png"),
        人狼: require("../assets/images/chara-icon/jinroh.png"),
        村人: require("../assets/images/chara-icon/murabito.png"),
        占い師: require("../assets/images/chara-icon/uranaishi.png"),
        怪盗: require("../assets/images/chara-icon/kaito.png"),
        狂人: require("../assets/images/chara-icon/kyojin.png"),
        吊り人: require("../assets/images/chara-icon/tsuribito.png"),
      },
    }
  }
}
</script>

<style lang="scss" scoped>
.display-rolls {
  display: flex;
  flex-wrap: wrap;
  padding: 24px;
  background-color: #eee;

  h2 {
    width: 100%;
    text-align: left;
  }

  ul {
    list-style-type: none;
  }

  li {
    display: flex;
    column-gap: 8px;
    align-items: center;
    margin-top: 16px;
  }

  img {
    width: 100%;
    max-width: 25px;
    height: auto;
    max-height: 25px;
    border-radius: 5px;
  }
}
</style>