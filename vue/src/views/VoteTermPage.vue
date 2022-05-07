<template>
  <main class="vote_page">
    <h2>話し合いが終了しました。投票を行ってください。</h2>
    <p class="action-result">{{ nightActLog }}</p>

    <VoteTarget
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
        <h2>投票対象</h2>
        <ul>
          <li v-for="player in canVotePlayers" v-bind:key="player.id">
            <input
              :id="player.id"
              class="vote-radio"
              type="radio"
              v-model="checkPlayerId"
              v-bind:value="player.id"
              :disabled="is_votable"
            />
            <label :for="player.id">
              {{ player.name }}
            </label>
          </li>
        </ul>
        <myButton
          class="vote-btn"
          :method="vote"
          :text="'確定'"
          :class="{ btn_disabled: is_votable }"
        />
        <p class="warn" :class="{ show: is_unvotable }">
          プレイヤーをえらんでね！
        </p>
        <p class="voted" :class="{ show: is_votable }">
          投票完了！他のプレイヤーが投票するまでまっててね！
        </p>
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
import VoteTarget from "@/components/VoteTarget.vue";
import DisplayRolls from "@/components/DisplayRolls.vue";

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
      is_votable: false,
      is_unvotable: false,
      nightActLog: "",
    };
  },
  components: { VoteTarget, myButton, DisplayRolls },
  mounted() {
    axios
      .get(JINROH_API_BASE_URL + "/vote-index", { withCredentials: true })
      .then((response) => {
        console.log(response.data);
        this.playerId = response.data.gameIndex.playerId;
        this.playerName = response.data.gameIndex.playerName;
        this.playerRole = response.data.gameIndex.playerRole;
        this.hostFlag = response.data.gameIndex.hostFlag;
        this.cos = response.data.cos;
        this.otherPlayerList = response.data.gameIndex.otherPlayerList;
        this.nightActLog = response.data.gameIndex.nightActLog;

        this.canVotePlayers = response.data.voteIndex.canVotePlayers;
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

    vote() {
      if (this.checkPlayerId == 0) {
        this.is_unvotable = true;
        this.is_votable = false;
        return;
      } else {
        this.is_unvotable = false;
        this.is_votable = true;
      }
      axios
        .post(
          JINROH_API_BASE_URL + "/vote",
          JSON.stringify({ gameParticipantId: this.checkPlayerId }),
          {
            withCredentials: true,
            headers: {
              "Content-Type": "application/json",
            },
          }
        )
        .then((response) => {
          console.log(response.data);
        })
        .catch(() => {
          this.$router.push("/room");
        });
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

input[type="radio"] {
  display: none;
}

label {
  display: block;
  padding: 0.5rem;
  background: #fff;
  border: 1px solid #50a0f6;
  border-radius: 0.5rem;

  &:hover {
    cursor: pointer;
  }
}

input[type="radio"]:checked + label {
  color: #fff;
  background: #50a0f6;
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
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: space-between;
  padding: 1rem;
  background-color: #eee;

  h2 {
    width: 100%;
    text-align: center;
  }

  ul {
    display: grid;
    row-gap: 1rem;
    column-gap: 1rem;
    grid-template-columns: 1fr 1fr;

    li {
      list-style: none;
    }
  }

  .vote-btn {
    margin: 1rem auto;
  }

  .warn {
    display: none;
    color: darken(red, 10%);
  }

  .voted {
    display: none;
  }

  .show {
    display: block;
  }

  .btn_disabled {
    color: gray;
    pointer-events: none;
    border-color: gray;
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
