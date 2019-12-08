import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import './registerServiceWorker'
import vuetify from './plugins/vuetify'
import VueResource from 'vue-resource'
// import oauth from './OAuth'
// import oauth from '../node_modules/@zalando/oauth2-client-js'
// Object.defineProperty(Vue.prototype, '$oauth', { value: oauth })
// Vue.use(oauth)
Vue.use(VueResource)
Vue.config.productionTip = false
Vue.component('WikiSection', require('./components/Wiki/wikiSection.vue').default)
new Vue({
  router,
  store,
  vuetify,
  // oauth,
  components: { App },
  render: h => h(App)
}).$mount('#app')
