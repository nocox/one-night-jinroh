<template>
  <main class="vote_page">
    <h2>話し合いが終了しました。投票を行ってください。</h2>
    <p v-if="nightActLog" class="action-result">{{ nightActLog }}</p>

    <TargetPlayerArea
      :otherPlayerList="otherPlayerList"
      :player="{
        playerName: playerName,
        playerRole: playerRole,
        playerId: playerId,
      }"
      :coRole="this.coRole"
      :cos="this.cos"
      :checkPlayerId="checkPlayerId"
    />

    <div class="col2">
      <section class="display-rolls">
        <DisplayRolls />
      </section>
      <section class="vote_section">
        <VoteArea
          :canVotePlayers="canVotePlayers"
          :voting-destination="votingDestination"
          @emitCheckPlayerId="checkPlayerId = $event"
        />
      </section>
    </div>

    <modal :width="'90%'" :height="'auto'" name="vote-start-modal">
      <div class="modal-header">
        <h3>話し合いが終了しました。<br />投票してください。</h3>
        <myButton class="vote-modal-btn" :method="closeModal" :text="'OK'" />
      </div>
    </modal>
  </main>
</template>

<script>
import axios from "axios";
import SockJS from "sockjs-client";
import Stomp from "webstomp-client";

import myButton from "@/components/Button";
import TargetPlayerArea from "@/components/TargetPlayerArea.vue";
import DisplayRolls from "@/components/DisplayRolls.vue";
import VoteArea from "@/components/VoteArea.vue";
import CoPlayers from "@/CoPlayers";

import { JINROH_API_BASE_URL } from "../Env";

export default {
  name: "TempVotePage",
  data() {
    return {
      playerId: -1,
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
      canVotePlayers: [
        {
          id: 1,
          name: "xxxxx",
          role: "---",
        },
      ],
      checkPlayerId: 0,
      nightActLog: "",
      votingDestination: null
    };
  },
  components: { TargetPlayerArea, VoteArea, myButton, DisplayRolls },
  mounted() {
    axios
      .get(JINROH_API_BASE_URL + "/vote-index", { withCredentials: true })
      .then((response) => {
        console.log(response.data);
        this.playerId = response.data.gameIndex.playerId;
        this.playerName = response.data.gameIndex.playerName;
        this.playerRole = response.data.gameIndex.playerRole;
        this.hostFlag = response.data.gameIndex.hostFlag;
        this.cos = new CoPlayers(response.data.cos);
        this.otherPlayerList = response.data.gameIndex.otherPlayerList;
        this.nightActLog = response.data.gameIndex.nightActLog;

        this.canVotePlayers = response.data.voteIndex.canVotePlayers;
        this.votingDestination = response.data.voteIndex.votingDestination;
        this.checkPlayerId = this.votingDestination;
        this.$modal.show("vote-start-modal");
        this.configWebSocket(response.data.gameId);
      })
      .catch(() => {
        this.$router.push("/room");
      });
  },
  methods: {
    closeModal() {
      this.$modal.hide("vote-start-modal");
    },

    configWebSocket: function (gameId) {
      this.socket = new SockJS(JINROH_API_BASE_URL + "/jinroh-websocket");
      this.stompClient = Stomp.over(this.socket);
      this.stompClient.connect({}, (frame) => {
        console.log("Connected: " + frame);
        this.stompClient.subscribe("/topic/done-tally/" + gameId, () => {
          this.$router.push("/tally");
        });
      });
    },
  },
};
</script>

<style lang="scss" scoped>
section {
  margin: 2rem auto 0;
}

h2 {
  text-align: center;
}

.action-result {
  padding: 1rem;
  background-color: #eee;
}

.col2 {
  display: flex;
  column-gap: 32px;

  section {
    width: 100%;
  }

  .display-rolls {
    max-width: 335px;
  }
}

.vote_section {
  padding: 1rem;
  background-color: #eee;
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

  .vote-modal-btn {
    width: 10rem;
    padding: 0.5em 0;
    margin: 2rem auto;
  }
}

@media screen and (max-width: 639px) {
  .col2 {
    flex-direction: column-reverse;
    flex-wrap: wrap;

    .display-rolls {
      max-width: 100%;
    }
  }

  .vote_section {
    ul {
      grid-template-columns: 1fr;
    }
  }
}
</style>