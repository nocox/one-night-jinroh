<template>
  <section class="title">
    <h1>
      <img
        class="title-logo"
        src="../assets/images/logo.png"
        alt="ゆるふわ人狼"
      />
    </h1>
    <div class="title_btn-wrapper">
      <a @click="createRoom" class="btn">
        <img
          class="swing"
          src="../assets/images/make_room.png"
          alt="へやをつくる"
        />
      </a>
      <a @click="show" class="btn">
        <img src="../assets/images/join_room.png" alt="へやにはいる" />
      </a>
    </div>
    <modal
      name="join-to-room"
      :classes="'join-modal'"
      :width="'90%'"
      :height="'auto'"
    >
      <h2>ルームに参加</h2>
      <div class="form">
        <input v-model="roomNum" placeholder="ルーム番号" />
        <p v-if="this.errorMessage" class="error">
          {{ this.errorMessage }}
        </p>
        <div class="modal_btn-wrapper">
          <a class="join btn" v-on:click="joinRoom">参加</a>
          <a class="cancel btn" v-on:click="hide">戻る</a>
        </div>
      </div>
    </modal>

    <div class="characters">
      <img class="pc" src="../assets/images/chara/chara_sort_pc.png" alt="" />
      <img class="sp" src="../assets/images/chara/chara_sort_sp.png" alt="" />
    </div>
  </section>
</template>

<script>
import axios from "axios";
import { JINROH_API_BASE_URL } from "../Env";

export default {
  name: "Title",
  data() {
    return {
      roomNum: "",
      joinRoomResult: "",
      errorMessage: "",
    };
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
        .get(JINROH_API_BASE_URL + "/create-room", { withCredentials: true })
        .then((response) => {
          console.log(response.data);
          this.$router.push("/room");
        });
    },
    joinRoom: function () {
      axios
        .get(JINROH_API_BASE_URL + "/join-room?uuid=" + this.roomNum, {
          withCredentials: true,
        })
        .then((response) => {
          console.log(response.data);
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
h1 {
  text-align: center;
}

.title-logo {
  width: 100%;
  max-width: 40rem;
  height: auto;
  filter: drop-shadow(0.1rem 0.1rem 0.1rem #956967);
}

.title_btn-wrapper {
  display: flex;
  max-width: 40rem;
  margin: auto;

  .btn {
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
  }
}

.join-modal {
  h2 {
    margin-top: 2rem;
    text-align: center;
  }

  .form {
    .error {
      color: red;
      text-align: center;
    }

    input {
      display: block;
      width: 75%;
      height: 2rem;
      margin: auto;
    }

    .modal_btn-wrapper {
      display: flex;
      justify-content: center;
      margin: 2rem auto;

      .btn {
        display: inline-block;
        max-width: 120px;
        padding: 5px 40px;
        color: #50a0f6;
        cursor: pointer;
        background-color: white;
        border-radius: 30px;
      }

      .join {
        color: white;
        background-color: #50a0f6;
        border: solid 1px;
        border-color: #50a0f6;
      }

      .cancel {
        margin-left: 2rem;
        border: solid 1px;
        border-color: #50a0f6;
      }
    }
  }
}

.characters img {
  width: 100%;
}

@media screen and (max-width: 639px) {
  .title-logo {
    max-width: 20rem;
  }

  .title_btn-wrapper {
    width: 20rem;

    .btn {
      width: auto;
      max-width: 8rem;
      margin: 2rem auto;
    }
  }

  .join-modal {
    .form {
      .modal_btn-wrapper {
        flex-direction: column;
        margin-top: 0.4rem;

        .btn {
          margin: 0.8rem auto 0.4rem;
        }
      }
    }
  }

  .characters {
    display: flex;
    justify-content: center;

    img {
      width: 20rem;
    }
  }
}
</style>
