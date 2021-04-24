import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'
import SamplePage from '../pages/SamplePage.vue'
import TopPage from '../views/TopPage.vue'
import RoomTopPage from '../views/RoomTopPage.vue'
import NightPage from '../views/NightPage.vue'
import TalkTermPage from '../views/TalkTermPage.vue'
import TempRoomPage from '../views/TempRoomPage.vue'
import TempNightTermPage from '../views/TempNightTermPage.vue'
import TempTalkTermPage from '../views/TempTalkTermPage.vue'
import TempVoteTermPage from '../views/TempVoteTermPage.vue'
import TempTallyTermPage from '../views/TempTallyTermPage.vue'
import TempResultTermPage from '../views/TempResultTermPage.vue'


Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home
  },
  {
    path: '/about',
    name: 'About',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/About.vue')
  },
  {
    path: '/sample',
    name: 'SamplePage',
    component: SamplePage
  },
  {
    path: '/top',
    name: 'TopPage',
    component: TopPage
  },
  {
    path: '/room-top',
    name: 'RoomTopPage',
    component: RoomTopPage
  },
  {
    path: '/night-page',
    name: 'NightPage',
    component: NightPage
  },
  {
    path: '/talk-page',
    name: 'TalkTermPage',
    component: TalkTermPage
  },
  {
    path: '/temp-room',
    name: 'TempRoomPage',
    component: TempRoomPage
  },
  {
    path: '/temp-night',
    name: 'TempNightTermPage',
    component: TempNightTermPage
  },
  {
    path: '/temp-talk',
    name: 'TempTalkTermPage',
    component: TempTalkTermPage
  },
  {
    path: '/temp-vote',
    name: 'TempVoteTermPage',
    component: TempVoteTermPage
  },
  {
    path: '/temp-tally',
    name: 'TempTallyTermPage',
    component: TempTallyTermPage
  },
  {
    path: '/temp-result',
    name: 'TempResultTermPage',
    component: TempResultTermPage
  }
]

const router = new VueRouter({
  routes: routes,
  scrollBehavior(to, from, savedPosition) {
    if (savedPosition) {
      return savedPosition
    } else {
      return { x: 0, y: 0 }
    }
  }
})

export default router
