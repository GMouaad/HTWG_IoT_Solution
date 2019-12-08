import Vue from 'vue'
import Router from 'vue-router'
import HomeContent from './components/Home/homeContent.vue'
import Devices from './components/DeviceViews/devices.vue'
import Geolocator from './components/Geolocator/geolocator.vue'
import Logs from './components/LogsViews/logs.vue'
import Wiki from './components/Wiki/Wiki.vue'

Vue.use(Router)

export default new Router({
  // base: '/ui/',
  // mode: 'history',
  routes: [
    {
      path: '/',
      name: 'ui',
      component: HomeContent
    },
    {
      path: '/home',
      name: 'home',
      component: HomeContent
    },
    {
      path: '/devices',
      name: 'devices',
      component: Devices
    },
    {
      path: '/geolocator',
      name: 'geolocator',
      component: Geolocator
    },
    {
      path: '/logs',
      name: 'logs',
      component: Logs
    },
    {
      path: '/wiki',
      name: 'wiki',
      component: Wiki
    },
    {
      path: '/oauth-callback',
      name: 'oauth-callback'
      // component: oauth-callback
    },
    {
      path: '/about',
      name: 'about',
      // route level code-splitting
      // this generates a separate chunk (about.[hash].js) for this route
      // which is lazy-loaded when the route is visited.
      component: () => import(/* webpackChunkName: "about" */ './views/About.vue')
    }
  ]
})
