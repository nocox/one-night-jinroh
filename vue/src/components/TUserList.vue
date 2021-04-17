<template>
  <div class="hello">
    <form v-on:submit.prevent="addTUser">
      <input v-model="userName" placeholder="edit me">
      <button>保存</button>
    </form>
    <ul>
      <li v-for="user in tUserList" v-bind:key="user.t_user_id">
        {{user.name}}
      </li>
    </ul>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: 'TUserList',
  data () {
    return {
      tUserList: [],
      userName: ""
    }
  },
  mounted() {
    axios.get('http://ec2-52-198-98-214.ap-northeast-1.compute.amazonaws.com/')
      .then((response) => {
        this.tUserList = response.data;
      });
  },
  methods: {
    addTUser: function () {
      axios.get('http://ec2-52-198-98-214.ap-northeast-1.compute.amazonaws.com/insert?name=' + this.userName)
      .then((response) => {
        this.tUserList = response.data;
      });
    }
  }

}
</script>

<style scoped>
ul {
  list-style: none;
}
</style>
