<template>
  <!-- <div class="hello">
    <h1>{{ msg }}</h1>
  </div> -->

  <div>
      <section>
        <v-layout column wrap class="my-12" align-center>
          <v-flex xs12 sm4 class="my-4">
            <div class="text-center">
              <h2 class="headline">PLANT NAME</h2>
              <span class="subheading">
                Browse your Plants and find Devices and general Information
              </span>
            </div>
          </v-flex>
          <v-flex xs12>
            <v-container grid-list-xl>
              <v-layout row wrap align-start>
                <v-flex xs12 md4>
                  <v-card flat class="transparent">
                    <v-card-text class="text-center">
                      <v-icon x-large class="blue--text text--lighten-2">mdi-palette</v-icon>
                    </v-card-text>
                    <v-img class="white--text" height="300px" src="../../assets/images/black-and-white-monitoring.jpg"/>
                    <v-card-title primary-title class="layout justify-center">
                      <div class="headline text-center">Monitor your devices</div>
                    </v-card-title>
                    <v-card-text>
                      Guideline to Monitor your devices using the Monitoring Tool for in the IoT Solution.
                    </v-card-text>
                  </v-card>
                </v-flex>
                <v-flex xs12 md4>
                  <v-card flat class="transparent" max-width="400">
                    <v-card-text class="text-center">
                      <v-icon x-large class="blue--text text--lighten-2">mdi-wrench</v-icon>
                    </v-card-text>
                    <v-img class="white--text" height="300px" src="../../assets/images/factory-devices-1.jpg"/>
                    <v-card-title  primary-title class="layout justify-center align-end">
                      <div class="headline">Connect your Device</div>
                    </v-card-title>
                    <v-card-text>
                      Click here to read the Guidline to connect a device to the plant.
                    </v-card-text>
                  </v-card>
                </v-flex>
                <v-flex xs12 md4>
                  <v-card flat class="transparent">
                    <v-card-text class="text-center">
                      <v-icon x-large class="blue--text text--lighten-2">mdi-flash</v-icon>
                    </v-card-text>
                    <v-img class="white--text" height="300px" src="../../assets/images/operator.jpg"/>
                    <v-card-title primary-title class="layout justify-center">
                      <div class="headline text-center">Involved Persons</div>
                    </v-card-title>
                    <v-card-text>
                      The invloved Professors, Engineers and Students on the Devices
                    </v-card-text>
                  </v-card>
                </v-flex>
              </v-layout>
            </v-container>
          </v-flex>
        </v-layout>
      </section>

      <section>
        <v-container grid-list-xl>
          <v-layout row wrap justify-center class="my-12">
            <v-flex xs12 sm4>
              <v-card flat class="transparent">
                <v-card-title primary-title class="layout justify-center">
                  <div class="headline">The Motive</div>
                </v-card-title>
                <v-card-text>
                  "Curiosity is the Spark behind the spark of every great idea. The future belongs to the curious.", Author unknown.
                </v-card-text>
              </v-card>
            </v-flex>
            <v-flex xs12 sm4 offset-sm1>
              <v-card flat class="transparent">
                <v-card-title primary-title class="layout justify-center">
                  <div class="headline">Contact</div>
                </v-card-title>
                <v-card-text>
                  Contact a Student or laboratory engineer and he will guide you to solve your Problem or redirect you to the person who can help you
                </v-card-text>
                <v-list class="transparent">
                  <v-list-item>
                    <v-list-item-action>
                      <v-icon class="blue--text text--lighten-2">mdi-account</v-icon>
                    </v-list-item-action>
                    <v-list-item-content>
                      <v-list-item-title>Mouaad Gssair</v-list-item-title>
                    </v-list-item-content>
                  </v-list-item>
                  <v-list-item>
                    <v-list-item-action>
                      <v-icon class="blue--text text--lighten-2">mdi-phone</v-icon>
                    </v-list-item-action>
                    <v-list-item-content>
                      <v-list-item-title>777-867-5309</v-list-item-title>
                    </v-list-item-content>
                  </v-list-item>
                  <v-list-item>
                    <v-list-item-action>
                      <v-icon class="blue--text text--lighten-2">mdi-map-marker</v-icon>
                    </v-list-item-action>
                    <v-list-item-content>
                      <v-list-item-title>Konstanz, DE</v-list-item-title>
                    </v-list-item-content>
                  </v-list-item>
                  <v-list-item>
                    <v-list-item-action>
                      <v-icon class="blue--text text--lighten-2">mdi-email</v-icon>
                    </v-list-item-action>
                    <v-list-item-content>
                      <v-list-item-title>M.Gssair@htwg-konstanz.de</v-list-item-title>
                    </v-list-item-content>
                  </v-list-item>
                </v-list>
              </v-card>
            </v-flex>
          </v-layout>
        </v-container>
      </section>

  </div>
</template>

<script>
export default {
  name: 'HomeContent',
  props: {
    msg: String
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h3 {
  margin: 40px 0 0;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #42b983;
}
#prlx {
  background:linear-gradient(to top right, rgba(100,115,201,.33), rgba(25,32,72,.7))
}
</style>
