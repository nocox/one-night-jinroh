<template>
    <div>
        <h2>役職紹介</h2>  
        <div class='RoleList'>
            <div class='Role' v-for="role in RoleList" v-bind:key=role>     
                <div class='RoleImage'>
                    <img v-bind:src='role.img'>
                </div> 
                <div>
                    <div class='RoleName'>
                        <h3>{{ role.name }}</h3> 
                    </div>
                    <div class='RoleDescription'>
                        {{ role.description }}
                    </div>
                </div> 
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
                    description: "人狼です人狼です人狼です人狼です人狼です人狼です人狼です人狼です"
                },
                {
                    name: "村人",
                    img: require("../assets/chara/chara2.png"),
                    description: "村人です村人です村人です村人です村人です村人です村人です村人です"
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

.RoleList{
    width:720px;
    display: flex;
    margin:0 auto;
    flex-wrap: wrap;
    
}

.Role{
    width:50%;
    display:flex;
}

.RoleName{
    text-align: left;
}

.RoleDescription{
    text-align: left;
}


</style>