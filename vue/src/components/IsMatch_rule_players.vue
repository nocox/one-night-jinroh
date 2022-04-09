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
        width: 40rem;
        margin: 1rem auto;
        color:red;
        border: solid 1px red;
    }
</style>