<template>
  <div class="vote-area">
    <h2>投票対象</h2>
    <ul>
      <li v-for="player in canVotePlayers" v-bind:key="player.id">
        <input
          :id="player.id"
          class="vote-radio"
          type="radio"
          v-model="checkPlayerId"
          @change="emitCheckPlayerId"
          v-bind:value="player.id"
          :disabled="is_votable"
        />
        <label class="vote-label" :for="player.id">
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
    <div class="vote-text">
      <p class="warn" :class="{ show: is_unvotable }">
        プレイヤーをえらんでね！
      </p>
      <p class="voted" :class="{ show: is_votable }">
        投票完了！他のプレイヤーが投票するまでまっててね！
      </p>
    </div>
  </div>
</template>


<script>
import axios from "axios";

import myButton from "@/components/Button";
import { JINROH_API_BASE_URL } from "../Env";

export default {
  name: "VoteArea",
  data() {
    return {
      checkPlayerId: null,
      is_unvotable: false,
      is_votable: false,
    };
  },
  components: { myButton },
  props: {
    canVotePlayers: { required: true },
    votingDestination: {}
  },
  methods: {
    emitCheckPlayerId() {
      this.$emit("emitCheckPlayerId", this.checkPlayerId);
    },
    vote() {
      if (!this.checkPlayerId) {
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
        .then((response) => {})
        .catch(() => {
          this.$router.push("/room");
        });
    },
  },
  watch: {
    votingDestination: {
      immediate: true,
      handler: function () {
        this.checkPlayerId = this.votingDestination
        this.is_votable = this.votingDestination
      }
    }
  }
};
</script>

<style lang="scss" scoped>
.vote-area {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: space-between;
  height: 100%;

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

  .vote-label {
    display: block;
    padding: 0.5rem;
    background: #fff;
    border: 1px solid #50a0f6;
    border-radius: 0.5rem;
    text-align: center;

    &:hover {
      cursor: pointer;
    }
  }

  .vote-radio {
    display: none;

    &:checked + label {
      color: #fff;
      background: #50a0f6;
    }
  }

  .vote-text{
    min-height: 2rem;
  }
}

@media screen and (max-width: 639px) {
  .vote_section {
    ul {
      grid-template-columns: 1fr;
    }
  }
}
</style>