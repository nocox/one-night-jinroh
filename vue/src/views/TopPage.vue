<template>
  <div class="TopPage">
    <div class="Top">
      <div class="TopTitle">
        <div class="TitleText">ワンナイト人狼</div>  
        <div class="SubTitleText">たのしくてかわいい人狼です</div>
      </div>
      <div class="TopButton">
        <a>ルームを作成</a>
        <a v-on:click="show">ルームに参加</a>
          <modal name="hello-world" >
            <div class="modal-header">
              <h2>ルームに参加</h2>
            </div>
            <div class="modal-body">
              <p>you're reading this text in a modal!</p>
              <button v-on:click="hide">閉じる</button>
            </div>  
          </modal>

      </div>    
    </div>

    <div>
      <p>
        テキストテキストテキストテキストテキスト
      </p>
    </div>
    <div>
      <h2>
        ワンナイト人狼とは？
      </h2>  
      <p>
        テキストテキストテキストテキストテキスト
      </p>
    </div>

    <RoleDescription/>

  </div>
</template>

<script>
// @ is an alias to /src
import RoleDescription from '@/components/RoleDescription.vue'

export default {
  name: 'TopPage',
  components:{
    RoleDescription
  },
  methods: {
    show : function() {
      this.$modal.show('hello-world');
    },
    hide : function () {
      this.$modal.hide('hello-world');
    },
  }  
}
</script>

<style>
.Top{
  height:300px;  
  background: linear-gradient(108.54deg, rgb(220, 255, 203), rgb(225, 255, 237), rgb(184, 229, 255));
  padding: 40px 0px 40px 0px;
}

.TitleText{
  font-size: 60px;
  font-weight: bold;
}

.SubTitleText{
  font-size: 24px;
  font-weight: bold;
  text-align: center;
  color: rgb(102, 102, 102);
}

.TopButton{
  width: 100%;
  margin: 30px auto;    
  display: flex;
  justify-content: space-around;
  flex-wrap: wrap;
}

.TopButton a{
  margin-top:10px;  
  display: grid;
  -webkit-box-pack: center;
  justify-content: center;
  -webkit-box-align: center;
  align-items: center;
  width: 280px;
  height: 60px;
  background-color: rgb(108, 204, 235);
  font-size: 16px;
  font-weight: bold;
  color: rgb(255, 255, 255);
  backface-visibility: hidden;
  box-shadow: rgba(108, 204, 235, 0.8) 0px 10px 20px;
  border-radius: 30px;
  transition: box-shadow 0.3s cubic-bezier(0.85, 1.97, 0.46, 0.4) 0s, transform;
}


</style>