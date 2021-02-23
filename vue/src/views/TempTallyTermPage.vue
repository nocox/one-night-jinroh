<template>
<div class="tally_page">
    <h1>集計ページ</h1>
    <modal name="done-tally-modal">
        <div class="modal-header">
            <h2>全員投票が完了しました．集計結果を表示します．</h2>
        </div>
        <div>
            <a v-on:click="closeModal">OK</a>
        </div>
    </modal>
</div>
</template>

<script>
// import axios from "axios";
// import SockJS from "sockjs-client";
// import Stomp from "webstomp-client";

export default {
    name: "TempTalkPage",
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
        }
    },
    mounted() {
        this.$modal.show("done-tally-modal");
    },
    methods: {
        closeModal() {
            this.$modal.hide("done-tally-modal");
        },
    }
}
</script>

<style scoped>
.tally_page {
    text-align: left;
    margin: 20px;
}

</style>