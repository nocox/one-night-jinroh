<template>
<div class="result_page">
    <h1>結果ページ</h1>
</div>
</template>

<script>
// import axios from "axios";
// import SockJS from "sockjs-client";
// import Stomp from "webstomp-client";

export default {
    name: "TempResultTermPage",
    data() {
        return{
            playerName: "xxxxx",
            playerRole: "xxxxx",
            hostFlag: false,
            otherPlayerList: [{
                id: 1,
                name: "xxxxx",
                role: "---"
            }],
            tallyResult: {
                selectedPlayer: {
                    id: 1,
                    name: "xxxxx",
                    voteCount: 0
                },
                players: [{
                    id: 1,
                    name: "xxxxx",
                    voteCount: 0
                }]
            }
        }
    },
    mounted() {
        console.log('結果ページ');
    },
    methods: {
    }
}
</script>

<style scoped>
.result_page {
    text-align: left;
    margin: 20px;
}

</style>