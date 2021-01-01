<template>
  <div class="TopPage">
    <div class="Top">
      <div class="TopTitle">
        <div class="TitleText">ワンナイト人狼</div>
        <div class="SubTitleText">たのしくてかわいい人狼です</div>
      </div>
      <div class="TopButton-layout">
        <a class="TopButton" v-on:click="createRoom">ルームを作成</a>
        <a class="TopButton" v-on:click="show">ルームに参加</a>
        <modal name="join-to-room">
          <div class="modal-header">
            <h2>ルームに参加</h2>
          </div>
          <div class="modal-body">
            <div>
              <input v-model="roomNum" placeholder="ルーム番号" />
            </div>
            <div>
              <a class="join" v-on:click="joinRoom">参加</a>
            </div>
            <div>
              <a v-on:click="hide">戻る</a>
            </div>
          </div>
        </modal>
      </div>
    </div>

    <div>
      <p>テキストテキストテキストテキストテキスト</p>
    </div>
    <div>
      <h2>ワンナイト人狼とは？</h2>
      <p>テキストテキストテキストテキストテキスト</p>
    </div>

    <RoleDescription />
  </div>
</template>

<script>
// @ is an alias to /src
import RoleDescription from "@/components/RoleDescription.vue";
import axios from "axios";

export default {
  name: "TopPage",
  data(){
      return{
          roomNum: ""
      }
  },
  components: {
    RoleDescription,
  },
  methods: {
    show: function () {
      this.$modal.show("join-to-room");
    },
    hide: function () {
      this.$modal.hide("join-to-room");
    },
    createRoom: function () {
      axios.get('http://localhost:8080/create-room')
      .then((response) => {
        console.log(response.data);
      });
    },
    joinRoom: function () {
      axios.get('http://localhost:8080/join-room?uuid=' + this.roomNum)
      .then((response) => {
        console.log(response.data);
      });
    }
  },
};
</script>

<style>
.Top {
  height: 300px;
  background: linear-gradient(
    108.54deg,
    rgb(220, 255, 203),
    rgb(225, 255, 237),
    rgb(184, 229, 255)
  );
  padding: 40px 0px 40px 0px;
}

.TitleText {
  font-size: 60px;
  font-weight: bold;
}

.SubTitleText {
  font-size: 24px;
  font-weight: bold;
  text-align: center;
  color: rgb(102, 102, 102);
}

.TopButton-layout {
  width: 100%;
  margin: 30px auto;
  display: flex;
  justify-content: space-evenly;
  flex-wrap: wrap;
}

.TopButton-layout .TopButton {
  margin-top: 10px;
  display: grid;
  -webkit-box-pack: center;
  justify-content: center;
  -webkit-box-align: center;
  align-items: center;
  width: 280px;
  height: 60px;
  background-color: rgb(108, 204, 235);
  font-size: 16px;
  font-weight: bold;
  color: rgb(255, 255, 255);
  backface-visibility: hidden;
  box-shadow: rgba(108, 204, 235, 0.8) 0px 10px 20px;
  border-radius: 30px;
  transition: box-shadow 0.3s cubic-bezier(0.85, 1.97, 0.46, 0.4) 0s, transform;
  transition: 0.3s;
  cursor: pointer;
}

.TopButton-layout .TopButton:hover {
  transform: scale(1.2, 1.2);
  transition: 0.3s;
}

.modal-body {
  display: flex;
  flex-direction: column;
}

.modal-body input {
  max-width: 160px;
}

.modal-body a {
  display: inline-block;
  max-width: 120px;
  margin-top: 10px;
  padding: 5px 40px;
  background-color: white;
  color: #50A0F6;
  border-radius: 30px;
  cursor: pointer;
}

.join {
  border: solid 1px;
  border-color: #50A0F6;
}

.modal-body a:hover {
  background-color: #50A0F6;
  transition: 0.3s;
  color: white;
}

.modal-body a:active {
  background-color: #9dcafa;
  transition: 0.1s;
}
</style>