<template>
  <div class="sample-page">
    <TUserList/>
  </div>
</template>

<script>
// @ is an alias to /src
import TUserList from '@/components/TUserList.vue'

export default {
  name: 'Home',
  components: {
    TUserList
  }
}
</script>
