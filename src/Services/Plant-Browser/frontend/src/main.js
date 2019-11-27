import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import './registerServiceWorker'
import vuetify from './plugins/vuetify'
import VueResource from 'vue-resource'

Vue.use(VueResource)
Vue.config.productionTip = false
Vue.component('WikiSection', require('./components/Wiki/wikiSection.vue').default)
new Vue({
  router,
  store,
  vuetify,
  components: { App },
  render: h => h(App)
}).$mount('#app')
