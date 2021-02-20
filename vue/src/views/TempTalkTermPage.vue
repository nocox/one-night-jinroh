<template>
<div class="talk_page">
    <div>
        話し合いページ
    </div>
    <modal name="talk-start-modal">
        <div class="modal-header">
            <h2>話し合いを始めてください</h2>
        </div>
        <div>
            <a v-on:click="closeModal">OK</a>
        </div>
    </modal>
</div>
</template>

<script>
export default {
    name: "TempTalkPage",
    data() {
        return{
            playerName: "xxxxx",
            playerRole: "xxxxx",
            otherPlayerList: [{
                id: 1,
                name: "xxxxx",
                role: "---"
            }],
        }
    },
    mounted() {
        console.log("test");
        this.$modal.show("talk-start-modal");
    },
    methods: {
        closeModal() {
            this.$modal.hide("talk-start-modal");
        }
    }
}
</script>

<style scoped>
.talk_page {
    text-align: left;
    margin: 20px;
}

</style>