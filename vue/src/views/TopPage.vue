<template>
  <div class="TopPage">
    <div>
    <h1>ワンナイト人狼</h1>  
    </div>

    <div>
      <button>ルームを作成</button>
      <button>ルームに参加</button>
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
  }
  }
</script>
