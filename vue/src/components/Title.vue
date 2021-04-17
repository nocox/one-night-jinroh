<template>
  <section class="title">
    <h1>
      <img
        class="title-logo pc"
        src="../assets/images/logo.png"
        alt="ゆるふわ人狼"
      />
      <img
        class="title-logo sp"
        src="../assets/images/logo-sp.png"
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
      :width="'75%'"
      :height="'240px'"
    >
      <h2>ルームに参加</h2>
      <div class="form">
        <input v-model="roomNum" placeholder="ルーム番号" />
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
export default {
  name: "Title",
  data() {
    return {
      roomNum: "",
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
        .get("http://ec2-52-198-98-214.ap-northeast-1.compute.amazonaws.com:8080/create-room", { withCredentials: true })
        .then((response) => {
          console.log(response.data);
          this.$router.push("/room-top");
        });
    },
    joinRoom: function () {
      axios
        .get("http://ec2-52-198-98-214.ap-northeast-1.compute.amazonaws.com:8080/join-room?uuid=" + this.roomNum, {
          withCredentials: true,
        })
        .then((response) => {
          console.log(response.data);
          this.$router.push("/room-top");
        });
    },
  },
};
</script>

<style lang="scss" scoped>
h1 {
  text-align: center;
  .title-logo {
    width: 100%;
    height: auto;
    margin: 1rem auto 0 -0.2rem;
    filter: drop-shadow(0.1rem 0.1rem 0.1rem #956967);
  }
}

.title_btn-wrapper {
  width: 100%;
  margin: auto;
  display: flex;
  justify-content: space-evenly;
  .btn {
    display: block;
    width: 25%;
    margin: 1rem auto 0 auto;

    filter: drop-shadow(0px 0px 8px rgba(0, 0, 0, 0.2));
    transform: translateZ(0);
    transition: 0.2s;

    &:hover {
      cursor: pointer;
      transform: scale(1.1, 1.1);
      filter: drop-shadow(0px 0px 10px rgba(0, 0, 0, 0.4));
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
    input {
      display: block;
      width: 75%;
      height: 2rem;
      margin: auto;
    }
    .modal_btn-wrapper {
      margin-top: 2rem;
      display: flex;
      justify-content: center;
      .btn {
        display: inline-block;
        max-width: 120px;
        padding: 5px 40px;
        background-color: white;
        color: #50a0f6;
        border-radius: 30px;
        cursor: pointer;
      }
      .join {
        color: white;
        border: solid 1px;
        border-color: #50a0f6;
        background-color: #50a0f6;
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
@media screen and (max-width: 1024px) {
  .title_btn-wrapper {
    flex-direction: column;
    .btn {
      width: 70%;
    }
  }
  .join-modal {
    .form {
      .modal_btn-wrapper {
        margin-top: 0.4rem;
        flex-direction: column;
        .btn {
          margin: 0.8rem auto 0.4rem auto;
        }
      }
    }
  }
}
</style>