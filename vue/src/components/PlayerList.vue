<template>
    <div>
        <Player v-for="player in PlayerList" :key="player.id"
        :isHost=player.isHost :id=player.id :name=player.name 
        />

    </div>
</template>

<script>
import Player from "@/components/Player.vue"

export default {
    name:"PlayerList",
    components:{
        Player
    },
    data(){
        return{
            PlayerList:[
                {id:1,
                isHost:false,
                name:"test"},
                {id:2,
                isHost:true,
                name:"test2"}
            ]
                
            
        }
    }
}
</script>