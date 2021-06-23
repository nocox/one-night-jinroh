<template>
  <main class="tally_page">
    <section class="vote_result">
      <h2>
        選ばれたのは,<br class="sp" />
        <span
          v-for="player in tallyResult.selectedPlayers"
          v-bind:key="player.id"
        >
          {{ player.name }}<br class="sp" />
        </span>
        です．
      </h2>
    </section>

    <RoleCardDisplay
      :playerRole="playerRole"
      :playerName="playerName"
      :otherPlayerList="otherPlayerList"
    />

    <section class="vote_detail">
      <h2>投票数</h2>
      <div class="table">
        <dl v-for="player in tallyResult.players" v-bind:key="player.id">
          <dt>
            {{ player.name }}
          </dt>
          <dd>
            {{ player.voteCount }}
          </dd>
        </dl>
      </div>
    </section>

    <myButton class="btn" :method="gotoResult" :text="'結果ページに移動する'" />

    <modal width="90%" name="done-tally-modal">
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

import RoleCardDisplay from "@/components/RoleCardDisplay";
import myButton from "@/components/Button";
import { JINROH_API_BASE_URL} from "../Env";

export default {
  name: "TallyTermPage",
  data() {
    return {
      playerName: "xxxxx",
      playerRole: {
        roleId: -1,
        roleName: "不明",
      },
      hostFlag: false,
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
    };
  },
  components: { RoleCardDisplay, myButton },
  mounted() {
    axios
      .get(JINROH_API_BASE_URL + "/tally-index", { withCredentials: true })
      .then((response) => {
        console.log(response.data);
        this.playerName = response.data.gameIndex.playerName;
        this.playerRole = response.data.gameIndex.playerRole;
        this.hostFlag = response.data.gameIndex.hostFlag;
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
h2 {
  text-align: center;
  font-weight: normal;
  span {
    font-size: 1.05em;
    font-weight: bold;
  }
}

.btn {
  text-align: center;
  display: block;
  width: 16rem;
  margin: 0 auto;
}

.vote_detail {
  text-align: center;
  h2 {
    display: inline-block;
    padding: 0 0.5em;
    font-size: 1.5em;
    border-bottom: 2px red solid;
  }
  .table {
    display: flex;
    justify-content: center;
    dl {
      display: flex;
      flex-direction: column;
      min-width: 20%;
      dt {
        padding: 0.5em;
      }
      dd {
        padding: 0.5em;
      }
    }
  }
}

.modal-header {
  display: flex;
  justify-content: center;
  flex-direction: column;

  height: 100%;
  width: 100%;
  text-align: center;

  h3 {
    margin: 0 auto;
  }
  .tally-modal-btn {
    width: 10rem;
    margin: 1em auto;
    padding: 0.5em 0;
  }
}

@media screen and (max-width: 1024px) {
  .vote_detail {
    .table {
      flex-direction: column;

      dl {
        flex-direction: row;
        justify-content: center;
        dt {
          min-width: 10rem;
        }
      }
    }
  }
}
</style>