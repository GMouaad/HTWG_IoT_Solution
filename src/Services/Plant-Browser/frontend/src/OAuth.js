import Vue from 'vue'
// import Oauth from '@zalando/oauth2-client-js'

const { Oauth } = require('@zalando/oauth2-client-js')

Vue.use(Oauth)

export default new Oauth({
  id: 'console',
  authorization_url: 'https://localhost:8885/oauth/authorize'
})
