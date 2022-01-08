<template>
  <section class="co-button-area">
    <h2>カミングアウト</h2>
    <div class="co-icons">
      <a :class="{ current: isActive['村人'] }" @click="toggleIcon('murabito')">
        <img src="../assets/images/chara-icon/murabito.png" alt="" />
      </a>
      <a :class="{ current: isActive['占い師'] }" @click="toggleIcon('uranaishi')">
        <img src="../assets/images/chara-icon/uranaishi.png" alt="" />
      </a>
      <a :class="{ current: isActive['怪盗'] }" @click="toggleIcon('kaito')">
        <img src="../assets/images/chara-icon/kaito.png" alt="" />
      </a>
      <a :class="{ current: isActive['人狼'] }" @click="toggleIcon('jinroh')">
        <img src="../assets/images/chara-icon/jinroh.png" alt="" />
      </a>
      <a :class="{ current: isActive['狂人'] }" @click="toggleIcon('kyojin')">
        <img src="../assets/images/chara-icon/kyojin.png" alt="" />
      </a>
      <a :class="{ current: isActive['吊り人'] }" @click="toggleIcon('turibito')">
        <img src="../assets/images/chara-icon/tsuribito.png" alt="" />
      </a>
    </div>
  </section>
</template>

<script>
import axios from "axios";
import { JINROH_API_BASE_URL } from "../Env";

export default {
  props: ["playerId"],
  data() {
    return {
      isActive: {
        村人: true,
        占い師: false,
        怪盗: false,
        人狼: false,
        狂人: false,
        吊り人: false,
      },
    };
  },
  methods: {
    toggleIcon: function (roleName) {
      Object.keys(this.isActive).forEach((key) => {
        this.isActive[key] = false;
      });
      this.isActive[roleName] = true;
      this.$emit("getActive", roleName);
      this.co(roleName);
    },
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
  display: grid;
  width: 100%;
  padding: 1rem 2rem;
  background-color: #eee;

  h2 {
    text-align: center;
  }

  .co-icons {
    display: grid;
    grid-template-columns: repeat(3, 80px);
    grid-template-rows: repeat(2, auto);
    column-gap: 1rem;
    justify-content: center;
    row-gap: 1rem;
    justify-items: center;
    align-items: center;

    a {
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

@media screen and (max-width: 639px) {
  .co-button-area {
    .co-icons {
      grid-template-columns: repeat(2, 80px);
    }
  }
}
</style>