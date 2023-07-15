import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'TopPage',
    component: () => import('../views/TopPage.vue')
  },
  {
    path: '/room',
    name: 'RoomTopPage',
    component: () => import('../views/RoomTopPage.vue')
  },
  {
    path: '/night',
    name: 'NightPage',
    component: () => import('../views/NightPage.vue')
  },
  {
    path: '/talk',
    name: 'TalkTermPage',
    component: () => import('../views/TalkTermPage.vue')
  },
  {
    path: '/vote',
    name: 'VoteTermPage',
    component: () => import('../views/VoteTermPage.vue')
  },
  {
    path: '/tally',
    name: 'TallyTermPage',
    component: () => import('../views/TallyTermPage.vue')
  },
  {
    path: '/result',
    name: 'ResultTermPage',
    component: () => import('../views/ResultTermPage.vue')
  },
  {
    path: '/:catchAll(.*)',
    component: () => import('../views/NotFoundPage.vue')
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

const route = [
  {
    from: "TopPage",
    to: ["RoomTopPage"]
  },
  {
    from: "RoomTopPage",
    to: ["NightPage"]
  },
  {
    from: "NightPage",
    to: ["TalkTermPage"]
  },
  {
    from: "TalkTermPage",
    to: ["VoteTermPage"]
  },
  {
    from: "VoteTermPage",
    to: ["TallyTermPage"]
  },
  {
    from: "TallyTermPage",
    to: ["ResultTermPage"]
  },
  {
    from: "ResultTermPage",
    to: ["RoomTopPage"]
  },
]

router.beforeEach((to, from, next) => {
  if (to.query["flg"] === "kaisan") {
    next()
    return true
  }

  if (from.name === "TopPage") {
    next()
    return true
  }

  let curentRoute = route.find(it => it.from === from.name)
  if (!curentRoute) {
    next()
    return true
  }
  if (curentRoute.to.some(it => it === to.name)) {
    next()
    return true
  }

  const answer = window.confirm("ゲームを終了しますか？")

  if (!answer) {
    next(false)
    return false
  }
  next()
})

export default router
