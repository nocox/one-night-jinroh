import Vue from 'vue'
import Vuex from 'vuex'
import createPersistedState from 'vuex-persistedstate'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    userId: 0,
    roomId: 'xxxxxx',
    rolls: {}
  },
  mutations: {
    setUserId (state, payload) {
      state.userId = payload.userId
      state.roomId = payload.roomId
    },
    setRolls (state, rolls){
      state.rolls = rolls
    }
  },
  actions: {
  },
  modules: {
  },
  plugins: [createPersistedState({storage: window.sessionStorage})]
})
