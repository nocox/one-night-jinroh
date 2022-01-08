<template>
  <main class="tally_page">
  
    <section class="vote_result" >
      <h2 v-if="peacefulFlag === false">
        選ばれたのは,<br />
        <span
          v-for="player in tallyResult.selectedPlayers"
          v-bind:key="player.id"
        >
          {{ player.name }}<br />
        </span>
        です．
      </h2>
      <h2 v-else>
        平和村が選ばれました。
      </h2>
    </section>

    <coArea
      :otherPlayerList="otherPlayerList"
      :player="{ playerId:playerId ,playerName: playerName, playerRole: playerRole }"
      :coRole="this.coRole"
      :selectedPlayers="this.tallyResult.selectedPlayers"
      :cos="cos"
      v-if="peacefulFlag===false"
    />
    <coArea
      :otherPlayerList="otherPlayerList"
      :player="{ playerId:playerId ,playerName: playerName, playerRole: playerRole }"
      :coRole="this.coRole"
      :cos="cos"
      v-else
    />

    <div class="col2">
      <section class="display-rolls">
        <DisplayRolls />
      </section>
      <section class="vote_detail">
        <h2>得票数</h2>
        <ul>
          <li v-for="player in tallyResult.players" v-bind:key="player.id">
            <span>{{ player.name }}</span>
            <span>：</span>
            <span>{{ player.voteCount }} </span>
          </li>
        </ul>

        <myButton
          class="btn"
          :method="gotoResult"
          :text="'結果ページに移動する'"
          v-if="hostFlag"
        />
      </section>
    </div>

    <modal :width="'90%'" :height="'auto'" name="done-tally-modal">
      <div class="modal-header">
        <h3>全員投票が完了しました．<br />集計結果を表示します．</h3>
        <myButton class="tally-modal-btn" :method="closeModal" :text="'OK'" />
      </div>
    </modal>
  </main>
</template>

<script>
import axios from "axios";
import SockJS from "sockjs-client";
import Stomp from "webstomp-client";

import myButton from "@/components/Button";
import coArea from "@/components/CoArea.vue";
import DisplayRolls from "@/components/DisplayRolls.vue";

import { JINROH_API_BASE_URL } from "../Env";

export default {
  name: "TallyTermPage",
  data() {
    return {
      playerId : -1,
      playerName: "xxxxx",
      playerRole: {
        roleId: -1,
        roleName: "不明",
      },
      hostFlag: false,
      cos: [],
      otherPlayerList: [
        {
          id: 1,
          name: "xxxxx",
          role: "---",
        },
      ],
      tallyResult: {
        selectedPlayer: [
          {
            id: 1,
            name: "xxxxx",
            voteCount: 0,
          },
        ],
        players: [
          {
            id: 1,
            name: "xxxxx",
            voteCount: 0,
          },
        ],
      },
      peacefulFlag: false,
    };
  },
  components: { myButton, coArea, DisplayRolls },
  computed: {
    coRole: {
      get() {
        return this.$store.state.coRole;
      },
      set(coRole) {
        this.$store.commit("setCoRole", coRole);
      },
    },
  },
  mounted() {
    axios
      .get(JINROH_API_BASE_URL + "/tally-index", { withCredentials: true })
      .then((response) => {
        console.log(response.data);
        this.playerId = response.data.gameIndex.playerId;
        this.playerName = response.data.gameIndex.playerName;
        this.playerRole = response.data.gameIndex.playerRole;
        this.hostFlag = response.data.gameIndex.hostFlag;
        this.cos = response.data.cos;
        this.otherPlayerList = response.data.gameIndex.otherPlayerList;

        this.tallyResult = response.data.tallyResult;
        this.$modal.show("done-tally-modal");
        this.configWebSocket(response.data.gameId);
      })
      .catch(() => {
        this.$router.push("/room");
      });
  },
  methods: {
    closeModal() {
      this.$modal.hide("done-tally-modal");
    },
    configWebSocket: function (gameId) {
      this.socket = new SockJS(JINROH_API_BASE_URL + "/jinroh-websocket");
      this.stompClient = Stomp.over(this.socket);
      this.stompClient.connect({}, (frame) => {
        console.log("Connected: " + frame);
        this.stompClient.subscribe("/topic/result/" + gameId, () => {
          this.$router.push("/result");
        });
      });
    },
    addClassToSelectedPlayer: function () {},
    gotoResult: function () {
      axios
        .get(JINROH_API_BASE_URL + "/show-result", { withCredentials: true })
        .then((response) => {
          console.log(response.data);
        })
        .catch(() => {
          this.$router.push("/room");
        });
    },
  },
};
</script>

<style lang="scss" scoped>
.vote_result {
  span {
    color: red;
  }
}

h2 {
  font-weight: normal;
  text-align: center;

  span {
    font-size: 1.05em;
    font-weight: bold;
  }
}

.col2 {
  display: flex;
  column-gap: 32px;

  .display-rolls {
    width: 100%;
    max-width: 335px;
    margin: 0;
  }
}

.btn {
  display: block;
  width: 16rem;
  margin: 16px auto;
  text-align: center;
}

.vote_detail {
  width: 100%;
  padding: 1em;
  margin: 0;
  text-align: center;
  background-color: #eee;

  h2 {
    font-weight: bold;
    text-align: center;
  }

  ul {
    display: flex;
    flex-direction: column;
    align-items: center;
    padding: 1rem 0;

    li {
      display: grid;
      grid-template-columns: 10rem 1rem 1rem;
      justify-content: center;
      width: 100%;
      text-align: left;
      list-style: none;
    }
  }
}

.modal-header {
  display: flex;
  flex-direction: column;
  justify-content: center;
  width: 100%;
  height: 100%;
  text-align: center;

  h3 {
    margin-top: 2rem;
  }

  .tally-modal-btn {
    width: 10rem;
    padding: 0.5em 0;
    margin: 2rem auto;
  }
}

@media screen and (max-width: 639px) {
  .col2 {
    flex-direction: column-reverse;
    flex-wrap: wrap;
    row-gap: 32px;

    .display-rolls {
      max-width: 100%;
    }
  }
}
</style>