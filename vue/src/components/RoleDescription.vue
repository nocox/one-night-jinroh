<template>
    <div>
      <h2>役職紹介</h2>  
      <div v-for="role in RoleList" v-bind:key=role>     
          <div>
            <img v-bind:src='role.img'>
            
          </div>  
          <div>
            <h3>{{ role.name }}</h3> 
          </div>
          <div>
            {{ role.description }}
          </div>
      </div>
    </div>
    
</template>

<script>
export default {
    name:"RoleDescription",
    data(){
        return{
            RoleList:[
                {
                    name: "人狼",
                    img: require("../assets/chara/chara1.png"),
                    description: "人狼です"
                },
                {
                    name: "村人",
                    img: require("../assets/chara/chara2.png"),
                    description: "村人です"
                },
                {
                    name: "占い師",
                    img: require("../assets/chara/chara3.png"),
                    description: "占い師です"
                },
                {
                    name: "怪盗",
                    img: require("../assets/chara/chara4.png"),
                    description: "怪盗です"
                },
                {
                    name: "狂人",
                    img: require("../assets/chara/chara5.png"),
                    description: "狂人です"
                },
                {
                    name: "吊り人",
                    img: require("../assets/chara/chara6.png"),
                    description: "釣り人です"
                }
            ]
        }
    },
}
</script>

<style>
img {
  width: 100px;
  height: 100px;
}
</style>