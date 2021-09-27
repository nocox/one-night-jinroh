<template>
  <main class="vote_page">
    <h2>話し合いが終了しました。投票を行ってください。</h2>
    <p class="action-result">夜の行動結果：UserName2は『人狼』でした。</p>

    <coArea
      :otherPlayerList="otherPlayerList"
      :player="{ playerName: playerName, playerRole: playerRole }"
      :coRole="this.coRole"
    />

    <section class="vote_section">
      <p>投票してください。</p>
      <ul>
        <li v-for="player in canVotePlayers" v-bind:key="player.id">
          <label>
            <input
              class="vote-radio"
              type="radio"
              v-model="checkPlayerId"
              v-bind:value="player.id"
              :disabled="is_votable"
            />
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
import coArea from "@/components/CoArea.vue";
import { JINROH_API_BASE_URL} from "../Env";

export default {
  name: "TempVotePage",
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
    };
  },
  components: {  coArea,myButton },
  computed:{
    coRole:{
      get(){return this.$store.state.coRole},
      set(coRole){this.$store.commit('setCoRole', coRole)},
    }
  },
  mounted() {
    axios
      .get(JINROH_API_BASE_URL + "/vote-index", { withCredentials: true })
      .then((response) => {
        console.log(response.data);
        this.playerName = response.data.gameIndex.playerName;
        this.playerRole = response.data.gameIndex.playerRole;
        this.hostFlag = response.data.gameIndex.hostFlag;
        this.otherPlayerList = response.data.gameIndex.otherPlayerList;

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
h2 {
  text-align: center;
}

.vote_section {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;

  ul li {
    list-style: none;
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
</style>