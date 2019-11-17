<template>
    <div class="overflow-hidden">
        <v-app-bar app color="#6A76AB" dark prominent src="../../assets/images/toolbar-background.jpg"
          dense clipped-left class="nopad"> <!-- scroll-target="#scrolling-techniques-3" -->
            <template v-slot:img="{ props }">
                <v-img v-bind="props" gradient="to top right, rgba(100,115,201,.7), rgba(25,32,72,.7)"></v-img>
            </template>
            <v-app-bar-nav-icon v-on:click="nav = !nav"></v-app-bar-nav-icon>
            <v-toolbar-title class="headline" id="nopad"> <!-- class text-uppercase -->
                    <v-container>
                        <v-row pa-6>
                            <v-col>
                                <v-avatar size="50px">
                                    <v-img src="../../assets/images/Logo.jpg" alt=""/>
                                </v-avatar>
                            </v-col>
                            <v-col>
                                <v-row>
                                    <span>HTWG IoT</span>
                                </v-row>
                                <v-row>
                                    <span class="font-weight-normal overline">PLANT BROWSER</span>
                                </v-row>
                            </v-col>
                        </v-row>
                    </v-container>
            </v-toolbar-title>
            <div class="flex-grow-1"></div>
            <v-btn icon to="/home">
                <v-icon>home</v-icon>
            </v-btn>
            <v-btn icon>
                <v-icon>mdi-account</v-icon>
            </v-btn>
            <v-menu bottom left>
                <template v-slot:activator="{ on }">
                    <v-btn icon v-on="on">
                        <v-icon>mdi-dots-vertical</v-icon>
                    </v-btn>
                </template>
                <v-list>
                    <v-list-item v-for="(item, i) in items" :key="i">
                        <v-list-item-title>{{ item.title }}</v-list-item-title>
                    </v-list-item>
                </v-list>
            </v-menu>
            <template v-slot:extension>
                <v-tabs align-with-title background-color="transparent">
                    <v-tab to="/">Home</v-tab>
                    <v-tab to="/devices">Devices</v-tab>
                    <v-tab to="/geolocator">Geolocator</v-tab>
                    <!-- <v-tab to="/workspace">Wiki</v-tab> -->
                    <v-tab to="/logs">Logs</v-tab>
                    <v-tab to="/wiki">Wiki</v-tab>
                </v-tabs>
            </template>
        </v-app-bar>
        <!-- <v-sheet app id="scrolling-techniques-3" class="overflow-y-auto"  max-height="600" >
            <v-container style="height: 670px;"></v-container>
        </v-sheet> -->
        <v-navigation-drawer app black v-model="nav" absolute temporary clipped height="100%" width="200">
            <v-list-item>
                <v-list-item-avatar>
                    <v-img src="../../assets/images/Logo.jpg"></v-img>
                </v-list-item-avatar>
                <v-list-item-content>
                    <v-list-item-title>HTWG IoT</v-list-item-title>
                </v-list-item-content>
            </v-list-item>
            <v-divider></v-divider>
            <v-list dense>
                <v-list-item v-for="item in navItems" :key="item.title" link :to="item.target">
                    <v-list-item-icon>
                        <v-icon>{{ item.icon }}</v-icon>
                    </v-list-item-icon>
                    <v-list-item-content>
                        <v-list-item-title>{{ item.title }}</v-list-item-title>
                    </v-list-item-content>
                </v-list-item>
            </v-list>
    </v-navigation-drawer>
    </div>
</template>
<script>
export default {
  name: 'HomeHeader',
  props: {
    title: String
  },
  data: () => ({
      loginStatus: '',
      loginStatusText: '',
      items: [
        { title: 'Login'},
        { title: 'Settings' },
        { title: 'Help' }
      ],
      nav: null,
      navItems: [
          { title: 'Home', icon: 'home', target: '/home'},
          { title: 'Devices', icon: 'ballot', target: '/devices'},
        //   { title: 'Workspace', icon: 'dashboard', target: '/workspace'},
          { title: 'Geolocator', icon: 'mdi-map-search-outline', target: '/geolocator'},
          { title: 'Logs', icon: 'mdi-file-document-outline', target: '/logs'},
          { title: 'Wiki', icon: 'info', target: '/wiki'}
        ],
    }),
    methods: {

    },
    created(){
        var instance = this
        instance.loginStatus = false
    },
    mounted(){
        var instance = this
        instance.loginStatusText = this.loginStatus ? 'Logout' : 'Login' 
    }
}
</script>
<style>
    .nopad{
        padding: 0px;
    }
    #nopad{
        padding: 0px;
    }
</style>