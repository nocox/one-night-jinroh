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
    <div class="btn-wrapper">
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
  </section>
</template>

<script>
export default {
  name: "Title",
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
.btn-wrapper {
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



@media screen and (max-width:1024px) {
  .btn-wrapper{
    flex-direction: column;  
    .btn{
      width:70%;
    }
  }
}


</style>