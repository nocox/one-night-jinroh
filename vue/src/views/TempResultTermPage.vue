<template>
<div class="result_page">
    <h1>結果ページ</h1>
    <h2>{{judgeText}}</h2>

    <button v-on:click="returnRoom">ルームに戻る</button>
</div>
</template>

<script>
import axios from "axios";
// import SockJS from "sockjs-client";
// import Stomp from "webstomp-client";

export default {
    name: "TempResultTermPage",
    data() {
        return{
            judgeText: ""
        }
    },
    mounted() {
        axios.get('http://localhost:8080/result-index', {withCredentials: true})
        .then((response) => {
            console.log(response.data);
            this.judgeText = response.data.judgeText;
        }).catch(() => {
            // this.$router.push('/temp-room');
        });
    },
    methods: {
        returnRoom() {
            this.$router.push('/temp-room');
        }
    }
}
</script>

<style scoped>
.result_page {
    text-align: left;
    margin: 20px;
}

</style>