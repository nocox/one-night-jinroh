<template>
    <div>
        <Player v-for="player in PlayerList" :key="player.id"
        :isHost=player.isHost :id=player.id
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
                },
                {id:2,
                isHost:true,
                }
            ]
                
            
        }
    }
}
</script>