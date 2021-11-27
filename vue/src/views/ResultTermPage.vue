<template>
  <main class="result_page">

    <modal :width="'90%'" :height="'auto'" name="result-modal">
      <div class="result-modal">
      <resultImage :judge="judge" @getJudgeText="judgeText=$event" />
      <myButton class="btn" :method="closeModal" :text="'OK'" />
      </div>
    </modal>

    <h2>
      {{ judgeText }}
    </h2>
    <div class="result grid-container">
      <div class="result_winners grid-item">
        <h3>かち</h3>
        <PlayerResult
          :playerName="val.playerName"
          :role="val.role"
          :coRole="val.coRole"
          :myself="val.myself"
          :comment="val.comment"
          v-for="(val, key) in winPlayerList"
          :key="key"
        />
      </div>
      <div class="result_losers grid-item">
        <h3>まけ</h3>
        <PlayerResult
          :playerName="val.playerName"
          :role="val.role"
          :coRole="val.coRole"
          :myself="val.myself"
          :comment="val.comment"
          v-for="(val, key) in losePlayerList"
          :key="key"
        />
      </div>
      <div class="holiday-roles grid-item">
        <h3>場のカード</h3>
        <img
          :src="RoleList[val]"
          :alt="val"
          v-for="(val, key) in holidayRoles"
          :key="key"
        />
      </div>
    </div>

    <myButton class="btn" :method="returnRoom" :text="'ルームに戻る'" />
  </main>
</template>

<script>
import axios from "axios";

import resultImage from "@/components/resultImage.vue";
import PlayerResult from "@/components/PlayerResult.vue";
import myButton from "@/components/Button.vue";
import { JINROH_API_BASE_URL } from "../Env";

export default {
  name: "TempResultTermPage",
  data() {
    return {
      judge: "",
      judgeText: "",
      holidayRoles: ["", ""],
      playerList: [
        {
          playerName: "",
          role: "",
          coRole: "",
          judge: "lose",
          myself: false,
          comment: "",
        },
      ],
      hostFlag: false,
      winPlayers: [],
      losePlayers: [],
      RoleList: {
        不明: require("../assets/images/card.png"),
        人狼: require("../assets/images/chara/chara1.png"),
        村人: require("../assets/images/chara/chara2.png"),
        占い師: require("../assets/images/chara/chara3.png"),
        怪盗: require("../assets/images/chara/chara4.png"),
        狂人: require("../assets/images/chara/chara5.png"),
        吊り人: require("../assets/images/chara/chara6.png"),
      },
    };
  },
  components: { resultImage, myButton, PlayerResult },
  computed: {
    // playerListを勝者と敗者に振り分ける
    winPlayerList: function () {
      return this.playerList.filter((player) => player.judge == "win");
    },
    losePlayerList: function () {
      return this.playerList.filter((player) => player.judge == "lose");
    },
  },
  async mounted() {
    await axios
      .get(JINROH_API_BASE_URL + "/result-index", { withCredentials: true })
      .then((response) => {
        console.log(response.data);
        // 新パラメータ(this datas are available from backend)
        this.judge = "SIMPLE_VILLAGE_WIN";
        this.holidayRoles = ["人狼", "村人"];
        this.playerList = [
          {
            playerName: "プレイヤー1",
            role: "人狼",
            coRole: "占い師",
            judge: "lose",
            myself: true,
            comment: "",
          },
          {
            playerName: "プレイヤー2",
            role: "人狼",
            coRole: "村人",
            judge: "lose",
            myself: false,
            comment: "",
          },
          {
            playerName: "プレイヤー3",
            role: "怪盗",
            coRole: "占い師",
            judge: "win",
            myself: false,
            comment: "占い師🔁怪盗",
          },
          {
            playerName: "プレイヤー4",
            role: "占い師",
            coRole: "怪盗",
            judge: "win",
            myself: false,
            comment: "怪盗🔁占い師",
          },
          {
            playerName: "プレイヤー5",
            role: "村人",
            coRole: "村人",
            judge: "win",
            myself: false,
            comment: "",
          },
          {
            playerName: "プレイヤー6",
            role: "村人",
            coRole: "村人",
            judge: "win",
            myself: false,
            comment: "",
          },
        ];
        this.$modal.show("result-modal");
      })
      .catch(() => {
        this.$router.push("/room");
      });
  },
  methods: {
    returnRoom() {
      this.$router.push("/room");
    },
    closeModal: function () {
      this.$modal.hide("result-modal");
    },
    getJudgeText: function(judgeText){
      this.judgeText = judgeText;
    },
  },
};
</script>

<style lang="scss" scoped>
.result_page {
  text-align: left;
  margin: 20px auto;
}

h2,
h3 {
  text-align: center;
}

.btn {
  display: block;
  width: 16rem;
  margin: 0 auto;
  margin-top: 3rem;
  text-align: center;
}

.grid-container {
  display: grid;
  justify-content: center;
  row-gap: 2rem;
  column-gap: 2rem;

  .grid-item {
    padding: 3rem;
    padding-top: 2rem;
    background-color: #eee;
    border-radius: 8px;
  }
}

.result {
  max-width: 800px;
  margin: auto;

  .result_winners {
    grid-column: 1/2;
  }

  .reslut_losers {
    grid-column: 2/3;
  }

  .holiday-roles {
    grid-column: 1/3;
    display: flex;
    flex-wrap: wrap;
    justify-content: center;
    h3 {
      width: 100%;
    }
    img {
      max-width: 8rem;
    }
  }
}

.result-modal{
  padding: 1rem 0;
}

@media screen and (max-width: 639px) {
  .result {
    .result_losers {
      grid-column: 1/2;
    }

    .holiday-roles {
      grid-column: 1/2;
    }
  }
}
</style>
