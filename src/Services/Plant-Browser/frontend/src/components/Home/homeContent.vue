<template>
  <!-- <div class="hello">
    <h1>{{ msg }}</h1>
  </div> -->

  <div>
      <section id="scrolling-techniques-3" class="overflow-y-auto"  max-height="600">
        <v-parallax src="../../assets/images/index.jpg" height="400">
          <v-layout column align-center justify-center class="white--text">
            <h1 class="white--text mb-2 display-1 text-center">Prinx</h1>
            <!-- <div class="subheading mb-4 text-center">by Mgsair</div> -->
            <v-btn class="mt-12" color="blue lighten-2" dark large href="/pre-made-themes">
              Get Started
            </v-btn>
          </v-layout>
        </v-parallax>
      </section>
      <section>
        <v-layout column wrap class="my-12" align-center>
          <v-flex xs12 sm4 class="my-4">
            <div class="text-center">
              <h2 class="headline">Work with people, post problems to be solved and share your ideas</h2>
              <span class="subheading">
                BE CREATIVE
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
                    <v-img class="white--text" height="300px" src="../../assets/images/intro_1.jpg"/>
                    <v-card-title primary-title class="layout justify-center">
                      <div class="headline text-center">Projects in progress</div>
                    </v-card-title>
                    <v-card-text>
                      Take a look on the projects in progress and perhaps share an idea about them
                    </v-card-text>
                  </v-card>
                </v-flex>
                <v-flex xs12 md4>
                  <v-card flat class="transparent" max-width="400">
                    <v-card-text class="text-center">
                      <v-icon x-large class="blue--text text--lighten-2">mdi-wrench</v-icon>
                    </v-card-text>
                    <v-img class="white--text" height="300px" src="../../assets/images/event_9.jpg"/>
                    <v-card-title  primary-title class="layout justify-center align-end">
                      <div class="headline">Problems to be solved</div>
                    </v-card-title>
                    <v-card-text>
                      Take a look at the problems that need to be solved, perhaps you might be the one or know the one who can work on them!
                    </v-card-text>
                  </v-card>
                </v-flex>
                <v-flex xs12 md4>
                  <v-card flat class="transparent">
                    <v-card-text class="text-center">
                      <v-icon x-large class="blue--text text--lighten-2">mdi-flash</v-icon>
                    </v-card-text>
                    <v-img class="white--text" height="300px" src="../../assets/images/event_11.jpg"/>
                    <v-card-title primary-title class="layout justify-center">
                      <div class="headline text-center">Researchers</div>
                    </v-card-title>
                    <v-card-text>
                      The most invloved researchers on the projects
                    </v-card-text>
                  </v-card>
                </v-flex>
              </v-layout>
            </v-container>
          </v-flex>
        </v-layout>
      </section>

      <section>
        <v-parallax id="prlx" src="../../assets/images/Board.jpg" height="380">
          <v-layout column align-center justify-center>
            <div class="headline bold--text black--text mb-4 text-center">Project Management and Cooperating has never been easier</div>
            <em class="white--text" >Kick-start your Project today</em>
            <v-btn class="mt-12" color="blue lighten-2" dark large href="/pre-made-themes">
              Register Now!
            </v-btn>
          </v-layout>
        </v-parallax>
      </section>

      <section>
        <v-container grid-list-xl>
          <v-layout row wrap justify-center class="my-12">
            <v-flex xs12 sm4>
              <v-card flat class="transparent">
                <v-card-title primary-title class="layout justify-center">
                  <div class="headline">Our Motive</div>
                </v-card-title>
                <v-card-text>
                  "Curiosity is the Spark behind the spark of every great idea. The future belongs to the curious.", Author unknown.
                </v-card-text>
              </v-card>
            </v-flex>
            <v-flex xs12 sm4 offset-sm1>
              <v-card flat class="transparent">
                <v-card-title primary-title class="layout justify-center">
                  <div class="headline">Contact us</div>
                </v-card-title>
                <v-card-text>
                  Cras facilisis mi vitae nunc lobortis pharetra. Nulla volutpat tincidunt ornare.
                </v-card-text>
                <v-list class="transparent">
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
                      <v-list-item-title>Mgsair@gmail.com</v-list-item-title>
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
