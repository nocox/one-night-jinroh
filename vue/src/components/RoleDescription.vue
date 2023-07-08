<template>
  <section>
    <section>
      <h3 class="murabito">村人陣営</h3>
      <p>人狼プレイヤーを一人でも吊ることができたら村人陣営の勝利です。</p>
      <p>
        人狼プレイヤーが一人もいない平和村の場合は誰も吊らない(全員の得票数が1票以下になる)ことが勝利条件となります。
      </p>
      <p>(吊人プレイヤーが勝利した場合は強制で敗北となります)</p>
      <CharaDescription :role="roleMap.murabito" />
      <CharaDescription :role="roleMap.uranaishi" />
      <CharaDescription :role="roleMap.kaito" />
    </section>

    <section>
      <h3 class="jinroh">人狼陣営</h3>
      <p>人狼プレイヤーが誰も吊られなければ人狼陣営の勝利です。</p>
      <p>(吊人プレイヤーが勝利した場合は強制で敗北となります)</p>
      <CharaDescription :role="roleMap.jinroh" />
      <CharaDescription :role="roleMap.kyojin" />
    </section>

    <section>
      <h3 class="turibito">吊人陣営</h3>
      <p>
        吊人プレイヤーが吊られた場合、吊られたプレイヤーの勝利となり、他の陣営はすべて敗北となります。
        <CharaDescription :role="roleMap.tsuribito" />
      </p>
    </section>
  </section>
</template>

<script>
import CharaDescription from "./CharaDescription";

export default {
  name: "RoleDescription",
  components: {
    CharaDescription,
  },
  data() {
    return {
      roleMap: {
        jinroh: {
          name: "人狼",
          img: this.$getRole("jinroh").img,
          description: `<p>イタズラ好きなかわいい人狼さん。
                        夜の間に他の人狼プレイヤーを確認することができます。</p>`,
        },
        murabito: {
          name: "村人",
          img: this.$getRole("murabito").img,
          description: `<p>平和な村に住む無垢でかわいい村人さん。特殊な能力はありません。
                        他プレイヤーの言葉だけをヒントに、誰が人狼か推理しましょう。</p>`,
        },
        uranaishi: {
          name: "占い師",
          img: this.$getRole("uranaishi").img,
          description: `<p>ミステリアスでクールな雰囲気の占い師さん。
                        夜の間に、他プレイヤーまたは場のカードを選択します。他プレイヤーのカードを選択した場合、そのプレイヤーの役職を確認できます。
                        場のカードを選択した場合、場にあるカード2枚の役職を確認できます。この行動は怪盗が役職を取り替える前に行われます。</p>`,
        },
        kaito: {
          name: "怪盗",
          img: this.$getRole("kaito").img,
          description: `<p>狙った獲物のハートを盗む怪盗さん。夜の間に、他プレイヤーのカードを選択すると「自分の役職」と「選択したプレイヤーの役職」を
                        入れ替えることができます。（入れ替えなくても良い）カードの交換は各自の役職確認後に行われるため、交換されたプレイヤーは
                        交換されたことを知らないままゲームがスタートします。勝利条件は交換後の役職に準じます。</p>`,
        },
        kyojin: {
          name: "狂人",
          img: this.$getRole("kyojin").img,
          description: `<p>人狼さんにひそかな思いを寄せる狂人さん。
                        特殊な能力はありませんが、村人とは陣営が異なり、人狼陣営に所属します。
                        狂人が吊られても人狼陣営の敗北にはなりません。
                        プレイヤーに人狼がいない平和村の場合、勝利条件は「人間 SIDE」となります(村人と同じ扱いとなります)。</p>`,
        },
        tsuribito: {
          name: "吊り人",
          img: this.$getRole("turibito").img,
          description: `<p>丈夫な縄を自分の首にかけてぶら下がる不思議な遊びが大好きな吊人さん。
                        吊られるのが目的です。吊られると一人勝ちとなります。</p>`,
        },
      },
    };
  },
  mounted() {},
};
</script>

<style lang="scss" scoped>
h3 {
  padding: 0.5rem;
}

.murabito {
  background: #b7ecb9;
}

.jinroh {
  background: #ecd7b7;
}

.turibito {
  background: #b7e2ec;
}
</style>
