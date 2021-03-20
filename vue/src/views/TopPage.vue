<template>
  <main class="TopPage">
    <Title />

    <section class="chara-image-list">
      <div>
        <img class="pc" src="../assets/images/chara/chara_sort_pc.png" alt="" />
        <img class="sp" src="../assets/images/chara/chara_sort_sp.png" alt="" />
      </div>
    </section>

    <NewsField />

    <section class="game-description">
      <h2>ゆるふわ人狼とは</h2>
      <p>
        殺伐とした心理戦を楽しむ「人狼ゲーム」をかわいいキャラ、シンプルなルールにアレンジしたゲームです。
      </p>
      <p>3～7人でふわっと簡単に短時間で遊べます。</p>
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
  </main>
</template>

<script>
// @ is an alias to /src
import RoleDescription from "@/components/RoleDescription.vue";

import axios from "axios";

import Title from "@/components/Title";
import NewsField from "@/components/NewsField";

export default {
  name: "TopPage",
  data() {
    return {
      roomNum: "",
    };
  },
  components: {
    RoleDescription,

    Title,
    NewsField,
  },
  methods: {
    show: function () {
      this.$modal.show("join-to-room");
    },
    hide: function () {
      this.$modal.hide("join-to-room");
    },
    createRoom: function () {
      axios
        .get("http://localhost:8080/create-room", { withCredentials: true })
        .then((response) => {
          console.log(response.data);
          this.$router.push("/room-top");
        });
    },
    joinRoom: function () {
      axios
        .get("http://localhost:8080/join-room?uuid=" + this.roomNum, {
          withCredentials: true,
        })
        .then((response) => {
          console.log(response.data);
          this.$router.push("/room-top");
        });
    },
  },
};
</script>

<style lang="scss" scoped>
.Top {
  margin-top: 100rem;
  height: 300px;
  background: linear-gradient(
    108.54deg,
    rgb(220, 255, 203),
    rgb(225, 255, 237),
    rgb(184, 229, 255)
  );
  padding: 40px 0px 40px 0px;
}

.TitleText {
  font-size: 60px;
  font-weight: bold;
}

.SubTitleText {
  font-size: 24px;
  font-weight: bold;
  text-align: center;
  color: rgb(102, 102, 102);
}

.TopButton-layout {
  width: 100%;
  margin: 30px auto;
  display: flex;
  justify-content: space-evenly;
  flex-wrap: wrap;
}

.TopButton-layout .TopButton {
  margin-top: 10px;
  display: grid;
  -webkit-box-pack: center;
  justify-content: center;
  -webkit-box-align: center;
  align-items: center;
  width: 280px;
  height: 60px;
  background-color: rgb(108, 204, 235);
  font-size: 16px;
  font-weight: bold;
  color: rgb(255, 255, 255);
  backface-visibility: hidden;
  box-shadow: rgba(108, 204, 235, 0.8) 0px 10px 20px;
  border-radius: 30px;
  transition: box-shadow 0.3s cubic-bezier(0.85, 1.97, 0.46, 0.4) 0s, transform;
  transition: 0.3s;
  cursor: pointer;
}

.TopButton-layout .TopButton:hover {
  transform: scale(1.2, 1.2);
  transition: 0.3s;
}

.modal-body {
  display: flex;
  flex-direction: column;
}

.modal-body input {
  max-width: 160px;
}

.modal-body a {
  display: inline-block;
  max-width: 120px;
  margin-top: 10px;
  padding: 5px 40px;
  background-color: white;
  color: #50a0f6;
  border-radius: 30px;
  cursor: pointer;
}

.join {
  border: solid 1px;
  border-color: #50a0f6;
}

.modal-body a:hover {
  background-color: #50a0f6;
  transition: 0.3s;
  color: white;
}

.modal-body a:active {
  background-color: #9dcafa;
  transition: 0.1s;
}

.cancel-btn a {
  border: none;
}

.chara-image-list {
  div img {
    width: 100%;
  }
}
</style>