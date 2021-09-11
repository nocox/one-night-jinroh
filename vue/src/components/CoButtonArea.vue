<template>
  <section class="co-button-area">
    <h2>COボタン</h2>
    <div class="co-icons">
      <a
        :class="{ current: isActive['村人'] }"
        @click="toggleIcon('村人')"
      >
        <img src="../assets/images/chara-icon/murabito.png" alt="" />
      </a>
      <a
        :class="{ current: isActive['占い師'] }"
        @click="toggleIcon('占い師')"
      >
        <img src="../assets/images/chara-icon/uranaishi.png" alt="" />
      </a>
      <a :class="{ current: isActive['怪盗'] }" @click="toggleIcon('怪盗')">
        <img src="../assets/images/chara-icon/kaito.png" alt="" />
      </a>
      <a :class="{ current: isActive['人狼'] }" @click="toggleIcon('人狼')">
        <img src="../assets/images/chara-icon/jinroh.png" alt="" />
      </a>
      <a :class="{ current: isActive['狂人'] }" @click="toggleIcon('狂人')">
        <img src="../assets/images/chara-icon/kyojin.png" alt="" />
      </a>
      <a
        :class="{ current: isActive['吊り人'] }"
        @click="toggleIcon('吊り人')"
      >
        <img src="../assets/images/chara-icon/tsuribito.png" alt="" />
      </a>
    </div>
  </section>
</template>

<script>
export default {
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
      this.$emit('getActive', roleName);
    },
  },
  mounted(){
    let nowRole = "村人";
    // for(let role in this.isActive){
    //   console.log(this.isActive[role]);
    // }
    this.$emit('getActive', nowRole);
    
  },
};
</script>

<style lang="scss" scoped>
.co-button-area {
  display: flex;
  flex-wrap: wrap;
  justify-content: center;
  padding: 24px;
  background-color: #eee;

  h2 {
    width: 100%;
  }

  .co-icons {
    display: flex;
    flex-wrap: wrap;
    justify-content: center;
    width: 100%;

    a {
      width: 33%;
      margin-top: 24px;
      text-align: center;
      cursor: pointer;
    }

    a img {
      // filter:grayscale(100%);
      border: 5px solid transparent;
      border-radius: 50%;
      opacity: 0.5;
    }

    .current img {
      border: 5px solid lighten(lightgreen, 0);
      box-shadow: 0 0 10px rgba(128, 128, 128, 0.5);
      opacity: 1; // filter:grayscale(0%);
    }
  }
}
</style>
