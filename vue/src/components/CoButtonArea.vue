<template>
  <section class="co-button-area">
    <h2>カミングアウト</h2>
    <div class="co-icons">
      <a :class="{ current: myCoRole==='murabito' }" @click="co('murabito')">
        <img src="../assets/images/chara-icon/murabito.png" alt="" />
      </a>
      <a :class="{ current: myCoRole==='uranaishi'}" @click="co('uranaishi')">
        <img src="../assets/images/chara-icon/uranaishi.png" alt="" />
      </a>
      <a :class="{ current: myCoRole==='kaito' }" @click="co('kaito')">
        <img src="../assets/images/chara-icon/kaito.png" alt="" />
      </a>
      <a :class="{ current: myCoRole==='jinroh' }" @click="co('jinroh')">
        <img src="../assets/images/chara-icon/jinroh.png" alt="" />
      </a>
      <a :class="{ current: myCoRole==='kyojin' }" @click="co('kyojin')">
        <img src="../assets/images/chara-icon/kyojin.png" alt="" />
      </a>
      <a :class="{ current: myCoRole==='turibito' }" @click="co('turibito')">
        <img src="../assets/images/chara-icon/tsuribito.png" alt="" />
      </a>
    </div>
  </section>
</template>

<script>
import axios from "axios";
import { JINROH_API_BASE_URL } from "../Env";

export default {
  props: ["playerId", "myCoRole"],
  methods: {
    co: function(roleName) {
      axios
      .post(
        JINROH_API_BASE_URL + "/co", 
        JSON.stringify({ playerId: this.playerId, role: roleName}),
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
      
    }
  },
  mounted() {
    let nowRole = "村人";
    this.$emit("getActive", nowRole);
  },
};
</script>

<style lang="scss" scoped>
.co-button-area {
  display: flex;
  flex-wrap: wrap;
  justify-content: center;
  padding: 1rem;
  background-color: #eee;

  h2 {
    width: 100%;
    text-align: center;
  }

  .co-icons {
    display: flex;
    flex-wrap: wrap;
    justify-content: center;
    width: 66%;

    a {
      width: 33%;
      margin-top: 24px;
      text-align: center;
      cursor: pointer;
    }

    a img {
      width: 100%;
      height: auto;
      border: 5px solid transparent;
      border-radius: 50%;
      opacity: 0.5;
    }

    .current img {
      border: 5px solid lighten(lightgreen, 0);
      box-shadow: 0 0 10px rgba(128, 128, 128, 0.5);
      opacity: 1;
    }
  }
}
</style>