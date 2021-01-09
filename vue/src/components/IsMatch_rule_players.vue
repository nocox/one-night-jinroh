<template>
    <div>
        <div class="alertUnmatch" v-show="IsMatch">
            ルールと人数が一致していません！
        </div>
        <div> 
            <button v-on:click="IsMatch=!IsMatch" >
                表示切り替え
            </button>
            現在のステータス：{{ IsMatch }} 
        </div>
    </div>
</template>

<script>
export default {
    name: "IsMatch_rule_players",
    data(){
        return{
            IsMatch: false
        }
    }
}
</script>

<style>
    .alertUnmatch{
        color:red;
        margin: 1rem auto;
        width: 40rem;
        border: solid 1px red;
    }
</style>