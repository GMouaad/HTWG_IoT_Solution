import Vue from 'vue'
import Router from 'vue-router'
import HomeContent from './components/Home/homeContent.vue'
import Projects from './components/ProjectViews/projects.vue'
import News from './components/NewsViews/news.vue'

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
      path: '/projects',
      name: 'projects',
      component: Projects
    },
    {
      path: '/news',
      name: 'news',
      component: News
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
