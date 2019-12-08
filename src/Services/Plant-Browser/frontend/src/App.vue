<template>
  <v-app>
    <home-header/>
    <v-content>
      <router-view/>
    </v-content>
    <home-footer/>
  </v-app>
</template>

<script>
// import HelloWorld from './components/HelloWorld';
import HomeHeader from './components/Home/homeHeader'
// import HomeContent from './components/Home/homeContent'
import HomeFooter from './components/Home/homeFooter'

export default {
  name: 'App',
  components: {
    HomeHeader,
    // HomeContent,
    HomeFooter
  },
  data: () => ({
    //
  })
}
</script>
<style>
  html{
      overflow-y: auto;
    }
</style>
