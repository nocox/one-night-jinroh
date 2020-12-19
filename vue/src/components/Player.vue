<template>
    <div>
        <div class="player">
                <div v-show="isHost" class="isHost">ホスト</div>
                <div v-show="!isHost" class="NotHost"></div>
                <div class="playerNum">PL{{ id }}</div>
                <div class="playerName">{{ name }}</div>
        </div>
    </div>
</template>

<script>
export default {
    name: "Player",
    // props:['id','isHost','name'],
    props:['id','isHost',]
    
}
</script>

<style>
    /* btn{
        background-color: grey;
        cursor: pointer;
    } */
    .player{
        border: solid 1px blue;
        margin: 1rem auto;
        padding: 1rem 1rem;
        display: flex;
        align-items: center;
        max-width: 20rem;
    }

    .isHost{
        background-color: cornflowerblue;
        padding: 1rem;
        width:5rem;
    }
    .NotHost{
        width: 5rem;
        padding: 1rem;
    }

    .playerNum{
        padding: 1rem;
    }

    .playerName{
        padding: 1rem;
    }   

    .btn{
        border: solid 1px blue;
        padding:.5rem;
        cursor: pointer;
    }

</style>