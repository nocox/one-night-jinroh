<template>
  <div>
    <h2>役職紹介</h2>
    <div class="RoleList">
      <div class="Role" v-for="(role, index) in RoleList" v-bind:key=index>
        <div class="RoleImage">
          <img v-bind:src="role.img" />
        </div>
        <div>
          <div class="RoleName">
            <h3>{{ role.name }}</h3>
          </div>
          <div class="RoleDescription">
            {{ role.description }}
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "RoleDescription",
  data() {
    return {
      RoleList: [
        {
          name: "人狼",
          img: require("../assets/chara/chara1.png"),
          description: `夜行動で他の人狼プレイヤーを確認することができます。
                                夜行動：他の人狼プレイヤーの名前が表示されます。余っている役職カード2枚にいる人狼は確認できません。`,
        },
        {
          name: "村人",
          img: require("../assets/chara/chara2.png"),
          description: `特殊な能力はありません。                    
                                夜行動：なし`,
        },
        {
          name: "占い師",
          img: require("../assets/chara/chara3.png"),
          description: `夜行動でプレイヤーの誰か一人、もしくは余っている役職カード2枚の役職を確認することができます。占いは怪盗が役職を取り替える前に行われます。
                                夜行動：他プレイヤーまたは余っている役職カードをクリックまたはタップすることで、「選択したプレイヤーの役職」もしくは「余っている役職カード２枚の役職」を確認することができます`,
        },
        {
          name: "怪盗",
          img: require("../assets/chara/chara4.png"),
          description: `夜行動で自分の役職と他のプレイヤーの役職を交換することができます。
                                交換は各自の役職確認後に行われるため、交換されたプレイヤーは交換されたことを知らないままゲームがスタートします。
                                勝利条件は交換後の役職に準じます。
                                夜行動：他プレイヤーをクリックまたはタップすることで「自分の役職」と「選択したプレイヤーの役職」を入れ替えることができます（入れ替えなくても良い）`,
        },
        {
          name: "狂人",
          img: require("../assets/chara/chara5.png"),
          description: `特殊な能力はありません。
                                人狼サイドが勝利すれば勝利となります。
                                プレイヤーに人狼がいない平和村の場合、勝利条件は「人間 SIDE」となります（村人と同じ扱いとなります）。
                                夜行動：なし`,
        },
        {
          name: "吊り人",
          img: require("../assets/chara/chara6.png"),
          description: `吊られるのが目的です。吊られると一人勝ちとなります。
                                夜行動：なし`,
        },
      ],
    };
  },
};
</script>

<style>
img {
  width: 100px;
  height: 100px;
}

.RoleList {
  min-width: 360px;
  max-width: 720px;
  display: flex;
  justify-content: center;
  margin: 0 auto;
  flex-wrap: wrap;
}

.Role {
  width: 360px;
  display: flex;
}

@media screen and (max-width: 720px) {
  .Role {
    flex-direction: column;
  }
  .RoleImage img {
    width: 120px;
    height: 120px;
  }
  .RoleName h3 {
    text-align: center;
  }
}

.RoleName {
  text-align: left;
}

.RoleDescription {
  text-align: left;
  white-space: pre-line; /* ちょっと気持ち悪い */
  word-wrap: break-word;
}
</style>