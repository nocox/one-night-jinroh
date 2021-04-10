<template>
  <section class="news-field">
    <h2>お知らせ</h2>
    <ul>
      <li>20XX.XX.XX:〇〇の機能を追加しました。</li>
      <li>20XX.XX.XX:〇〇の機能を追加しました。</li>
      <li>20XX.XX.XX:〇〇の機能を追加しました。</li>
      <li>20XX.XX.XX:〇〇の機能を追加しました。</li>
      <li>20XX.XX.XX:〇〇の機能を追加しました。</li>
    </ul>
    <p><a href="">もっと見る</a></p>
  </section>
</template>

<script>
export default {
  name: "NewsField"
}
</script>

<style lang="scss" scoped>
ul{
  li{
    font-size: .8rem;
    list-style: none;
    margin-left: .5rem;
  }
}
p{
  margin-left: .5rem;
}
</style>
