<template>
  <main class="TopPage">
    <GameTitle />

    <NewsField />

    <section class="game-description">
      <h2>ゆるふわじんろうとは?</h2>
      <p>
        殺伐とした心理戦を楽しむ「人狼ゲーム」をかわいいキャラ、シンプルなルールにアレンジしたゲームです。
      </p>
      <p>3～7人でふわっと簡単に短時間で遊べます。</p>
    </section>

    <section class="world-description">
      <h2>世界観</h2>
      <p>
        人間と人狼が仲良く暮らすゆるふわ村。
        この村では夜な夜なこっそり誰かにイタズラしては騒動を起こすいたずらオオカミ娘がおりました。
        毎晩のイタズラに困り果てた住人たちは、イタズラの犯人を探し出すために会議を開くことになります。
      </p>
    </section>

    <section class="rule">
      <h2>あそびかた</h2>
      <p>
        本ゲームはPC、スマートフォン両ブラウザで遊ぶことができます。通常の人狼ゲームとは異なり、襲撃や処刑による途中脱落はなく、一晩で1ゲームが決着するシンプルルールになっています。
      </p>
      <p>
        各プレイヤーは6つの役職のうち、ひとつがランダムに割り振られ、各役職に対応する陣営のプレイヤーとして、それぞれの陣営ごとの勝利条件を目指します。
      </p>
      <p>各役職と対応する陣営は以下の通りです。</p>
    </section>

    <RoleDescription />
    <p>ver 1.0.1</p>
  </main>
</template>

<script>
// @ is an alias to /src
import RoleDescription from "@/components/RoleDescription.vue";
import GameTitle from "@/components/GameTitle";
import NewsField from "@/components/NewsField";

export default {
  name: "TopPage",
  data() {
    return {
      roomNum: "",
    };
  },
  beforeRouteEnter(to, from, next) {
    if (to.query["flg"] === "kaisan") {
      next((vm) => {
        vm.$router.replace("/");
      });
    } else {
      next();
    }
  },
  components: {
    RoleDescription,
    GameTitle,
    NewsField,
  },
  methods: {},
};
</script>
