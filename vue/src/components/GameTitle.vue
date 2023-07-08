<template>
  <article>
    <h1 class="title">
      <img :src="require('@/assets/images/logo.png')" alt="ゆるふわ人狼" />
    </h1>

    <div class="room-button">
      <a @click="createRoom">
        <img
          :src="require('@/assets/images/make_room.png')"
          alt="へやをつくる"
        />
      </a>
      <a @click="$modal.show('join-to-room')">
        <img
          :src="require('@/assets/images/join_room.png')"
          alt="へやにはいる"
        />
      </a>
    </div>

    <modal
      name="join-to-room"
      :classes="'modal'"
      :width="'90%'"
      :height="'auto'"
    >
      <h2 class="modal__title">ルームに参加</h2>
      <form class="modal__form">
        <input v-model="roomNum" placeholder="ルーム番号" />
        <p v-if="errorMessage" class="error-message">
          {{ errorMessage }}
        </p>
        <div class="modal__button-box">
          <a class="modal__button join" @click="joinRoom">参加</a>
          <a class="modal__button cancel" @click="$modal.hide('join-to-room')">
            戻る
          </a>
        </div>
      </form>
    </modal>

    <picture class="characters">
      <source
        :srcset="require('@/assets/images/chara/chara_sort_sp.png')"
        media="(max-width:639px)"
      />
      <img :src="require('@/assets/images/chara/chara_sort_pc.png')" />
    </picture>
  </article>
</template>

<script>
import axios from "axios";
import { JINROH_API_BASE_URL } from "../Env";

export default {
  name: "GameTitle",
  data() {
    return {
      roomNum: "",
      joinRoomResult: "",
      errorMessage: "",
    };
  },
  methods: {
    createRoom: function () {
      axios
        .get(JINROH_API_BASE_URL + "/create-room", { withCredentials: true })
        .then((response) => {
          this.$router.push("/room");
        });
    },
    joinRoom: function () {
      axios
        .get(JINROH_API_BASE_URL + "/join-room?uuid=" + this.roomNum, {
          withCredentials: true,
        })
        .then((response) => {
          this.joinRoomResult = response.data;
          if (this.joinRoomResult === "JOIN_SUCCESS") {
            this.errorMessage = "";
            this.$router.push("/room");
          } else if (this.joinRoomResult === "PARTICPANT_LIMIT") {
            this.errorMessage = "ルームの参加者上限に達しています";
          } else if (this.joinRoomResult === "ROOM_NOT_EXIST") {
            this.errorMessage = "ルームが見つかりません";
          }
        })
        .catch(() => {
          this.$router.push("/top");
        });
    },
  },
};
</script>

<style lang="scss" scoped>
@mixin mq() {
  @media screen and (max-width: 639px) {
    @content;
  }
}

.title {
  text-align: center;

  img {
    width: 100%;
    max-width: 40rem;
    height: auto;
    filter: drop-shadow(0.1rem 0.1rem 0.1rem #956967);

    @include mq() {
      max-width: 20rem;
    }
  }
}

.room-button {
  display: flex;
  max-width: 40rem;
  margin: auto;

  @include mq() {
    width: 20rem;
  }

  a {
    display: block;
    width: 25%;
    margin: 2rem auto;
    filter: drop-shadow(0 0 8px rgb(0 0 0 / 20%));
    transition: 0.2s;
    transform: translateZ(0);

    &:hover {
      cursor: pointer;
      filter: drop-shadow(0 0 10px rgb(0 0 0 / 40%));
      transform: scale(1.1, 1.1);
    }

    img {
      width: 100%;
      height: 100%;
    }

    @include mq() {
      width: auto;
      max-width: 8rem;
      margin: 2rem auto;
    }
  }
}

.characters {
  display: flex;
  justify-content: center;
  align-items: center;
  img {
    width: 100%;
    @include mq() {
      max-width: 20rem;
    }
  }
}

.modal {
  &__title {
    margin-top: 2rem;
    text-align: center;
  }

  &__form {
    .error-message {
      color: red;
      text-align: center;
    }

    input {
      display: block;
      padding: 0.2em;
      width: 75%;
      height: 2rem;
      margin: auto;
    }
  }

  &__button-box {
    margin: 2rem auto;
    display: grid;
    grid-template-columns: repeat(2, 120px);
    gap: 1rem;
    justify-items: center;
    justify-content: center;

    @include mq() {
      margin: 1rem auto;
      grid-template-columns: 120px;
      gap: 0.5rem;
    }
  }

  &__button {
    width: 100%;
    text-align: center;
    padding: 0.5rem 2.25rem;
    cursor: pointer;
    border-radius: 30px;

    &.join {
      color: #fff;
      background-color: #50a0f6;
      border: 1px solid #50a0f6;
    }

    &.cancel {
      color: #50a0f6;
      background-color: #fff;
      border: 1px solid #50a0f6;
    }
  }
}
</style>
