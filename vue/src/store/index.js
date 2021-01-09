import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    userId: 0,
    roomId: 'xxxxxx'
  },
  mutations: {
    setUserId (state, payload) {
      state.userId = payload.userId
      state.roomId = payload.roomId
    }
  },
  actions: {
  },
  modules: {
  }
})
