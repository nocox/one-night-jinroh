import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import VModal from 'vue-js-modal'
import { getRoleInfo } from "@/roleInfo.js";
// require('@/assets/scss/_variables.scss')

Vue.config.productionTip = false

Vue.use(VModal);

Vue.prototype.$getRole = (name) => {
  return getRoleInfo(name)
};

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
