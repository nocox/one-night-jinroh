<template>
  <main class="talk_page">
    <h2>朝になりました。話し合いを行ってください。</h2>
    <div class="grid-container">
      <div class="me">
        <h3>あなたです</h3>
        <RoleCard :roleName="playerRole.roleName" />
        <p>{{ playerName }}</p>
        <p>役職：{{ playerRole.roleName }}</p>
      </div>
      <div class="others">
        <h3>他のプレイヤー</h3>
        <ul>
          <li v-for="player in otherPlayerList" :key="player.id">
            <RoleCard :roleName="'不明'" />
            <p>{{ player.name }}</p>
            <p>役職： 不明</p>
          </li>
        </ul>
      </div>
      <div class="board-cards">
        <h3>おやすみ中のカード</h3>
        <ul>
          <li
            class="board-card"
            v-for="board_card in BoardCards"
            :key="board_card.index"
          >
            <RoleCard :roleName="'不明'" />
          </li>
        </ul>
      </div>
    </div>

    <div class="btn-area">
      <myButton
        v-if="hostFlag"
        :method="endTalk"
        :text="'話し合いを終了する'"
      />
    </div>

    <modal name="talk-start-modal">
      <div class="modal-header">
        <h2>話し合いを始めてください</h2>
      </div>
      <div class="btn-area">
        <myButton :method="closeModal" :text="'OK'"></myButton>
      </div>
    </modal>
  </main>
</template>

<script>
import axios from "axios";
import SockJS from "sockjs-client";
import Stomp from "webstomp-client";

import RoleCard from "@/components/RoleCard.vue";
import myButton from "@/components/Button.vue";

export default {
  name: "TempTalkPage",
  components: {
    RoleCard,
    myButton,
  },
  data() {
    return {
      playerName: "xxxxx",
      playerRole: "xxxxx",
      hostFlag: false,
      otherPlayerList: [
        {
          id: 1,
          name: "xxxxx",
          role: "---",
        },
      ],
      BoardCards: [
        {
          role: "---",
        },
        {
          role: "---",
        },
      ],
    };
  },
  mounted() {
    axios
      .get("http://localhost:8080/talk-index", { withCredentials: true })
      .then((response) => {
        console.log(response.data);
        this.playerName = response.data.gameIndex.playerName;
        this.playerRole = response.data.gameIndex.playerRole;
        this.hostFlag = response.data.gameIndex.hostFlag;
        this.otherPlayerList = response.data.gameIndex.otherPlayerList;
        this.$modal.show("talk-start-modal");
        this.configWebSocket(response.data.gameId);
      })
      .catch(() => {
        this.$router.push("/temp-room");
      });
  },
  methods: {
    configWebSocket: function (gameId) {
      this.socket = new SockJS("http://localhost:8080/jinroh-websocket");
      this.stompClient = Stomp.over(this.socket);
      this.stompClient.connect({}, (frame) => {
        console.log("Connected: " + frame);
        this.stompClient.subscribe("/topic/end-talk/" + gameId, () => {
          this.$router.push("/temp-vote");
        });
      });
    },
    closeModal() {
      this.$modal.hide("talk-start-modal");
    },
    endTalk() {
      axios
        .get("http://localhost:8080/end-talk", { withCredentials: true })
        .then((response) => {
          console.log(response.data);
        })
        .catch(() => {
          this.$router.push("/temp-room");
        });
    },
  },
};
</script>

<style lang="scss" scoped>
.btn-area{
  margin:2.4rem auto;
  text-align: center;
}

h2 {
  text-align: center;
}

// 以下はプレイヤー、場のカードのスタイル

h3 {
  margin: 0.5em;
}

ul {
  display: flex;
  flex-wrap: wrap;
  justify-content: center;
  li {
    list-style: none;
    text-align: center;
    margin: 0 1.2rem;
  }
}

p {
  margin: 0;
}

.grid-container {
  margin: auto;
  text-align: center;

  display: grid;
  align-items: center;

  .board-cards {
    grid-column: 2/3;
    grid-row: 1/2;
  }
  .me {
    grid-column: 1/2;
    grid-row: 1/3;
  }
  .others {
    margin-top: 2.4rem;
    grid-column: 2/3;
    grid-row: 2/3;
  }
}

@media screen and (max-width: 1024px) {
  .grid-container {
    .me {
      grid-column: 1/2;
      grid-row: 1/2;
    }
    .others {
      grid-column: 1/2;
      grid-row: 2/3;
    }
    .board-cards {
      margin-top: 2.4rem;
      grid-column: 1/2;
      grid-row: 3/4;
    }
  }
}
</style>


