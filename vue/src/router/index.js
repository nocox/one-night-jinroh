import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'
import SamplePage from '../pages/SamplePage.vue'
import TopPage from '../views/TopPage.vue'
import RoomTopPage from '../views/RoomTopPage.vue'

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
    path: '/roomtop',
    name: 'RoomTopPage',
    component: RoomTopPage
  }
]

const router = new VueRouter({
  routes
})

export default router
