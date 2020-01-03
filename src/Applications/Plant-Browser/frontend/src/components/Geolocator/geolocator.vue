<template>
  <div>
    <p>This View is not implemented yet</p>
    <p>It is used for Tests at the moment</p>

    <v-btn color="primary" dark @click="authRequest">Open Logs</v-btn>
    <!-- <v-dialog v-model="dialog" scrollable max-width="1000px" align-center justify-center>
      <template v-slot:activator="{ on }">
        <v-btn color="primary" dark v-on="on">Open Logs</v-btn>
      </template>
      <v-card>
        <v-card-title>Logs</v-card-title>
        <v-divider></v-divider>
        <v-card-text class="black white--text" style="height: 600px;">
          <p>
            Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.
            Ut sem nulla pharetra diam sit amet nisl suscipit. Vitae auctor eu augue ut lectus arcu. Consectetur adipiscing elit ut aliquam purus sit amet luctus.
            Congue nisi vitae suscipit tellus.
          </p>
          <p>
            Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.
            Ut sem nulla pharetra diam sit amet nisl suscipit. Vitae auctor eu augue ut lectus arcu. Consectetur adipiscing elit ut aliquam purus sit amet luctus.
            Congue nisi vitae suscipit tellus.
          </p>
          <p v-text="logFileContent"></p>
        </v-card-text>
        <v-divider></v-divider>
        <v-card-actions>
          <v-btn color="blue darken-1" text @click="dialog = false">Close</v-btn>
          <v-btn color="blue darken-1" text @click="getLogFileContent">refresh</v-btn>
          <v-btn color="blue darken-1" text @click="dialog = false">print</v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog> -->
  </div>
</template>

<script>
var OAuth = require('@zalando/oauth2-client-js')
var provider = new OAuth.Provider({
  id: 'console',
  authorization_url: 'https://localhost:8885/oauth/authorize'
})
export default {
  data: () => ({

  }),
  methods: {
    authRequest () {
      var request = new OAuth.Request({
        client_id: 'plant-browser',
        redirect_uri: 'http://127.0.0.1:8080/#/geolocator',
        response_type: 'code',
        state: 'code'
      })
      var uri = provider.requestToken(request)
      provider.remember(request)
      window.location.href = uri
      var response = provider.parse(window.location.href)
      console.log(response)
      console.log('returned Token:' + provider.getAccessToken())
    }
  },
  mounted () {
  }
}
</script>
<style>
</style>
