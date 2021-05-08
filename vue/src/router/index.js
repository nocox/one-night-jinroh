import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'
import SamplePage from '../pages/SamplePage.vue'
import TopPage from '../views/TopPage.vue'
import RoomTopPage from '../views/RoomTopPage.vue'
import NightPage from '../views/NightPage.vue'
import TalkTermPage from '../views/TalkTermPage.vue'
import VoteTermPage from '../views/VoteTermPage.vue'
import TallyTermPage from '../views/TallyTermPage.vue'
import ResultTermPage from '../views/ResultTermPage.vue'

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
    path: '/vote-page',
    name: 'VoteTermPage',
    component:VoteTermPage
  },
  {
    path: '/tally-page',
    name: 'TallyTermPage',
    component:TallyTermPage
  },
  {
    path: '/result-page',
    name: 'ResultTermPage',
    component:ResultTermPage
  },
  
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
