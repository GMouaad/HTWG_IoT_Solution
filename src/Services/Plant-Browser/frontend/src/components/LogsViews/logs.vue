<template>
    <v-container>
        <v-row align-center justify-center>
            <v-col align-center justify-center>
              <v-dialog v-model="dialog" scrollable max-width="1000px" align-center justify-center>
                <template v-slot:activator="{ on }" >
                  <v-btn color="primary" dark v-on="on">Open Logs</v-btn>
                </template>
                <v-card>
                  <v-card-title>Logs</v-card-title>
                  <v-divider></v-divider>
                  <v-card-text class="black white--text" style="height: 600px;">
                    <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.
                      Ut sem nulla pharetra diam sit amet nisl suscipit. Vitae auctor eu augue ut lectus arcu. Consectetur adipiscing elit ut aliquam purus sit amet luctus.
                       Congue nisi vitae suscipit tellus.
                    </p>
                    <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.
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
              </v-dialog>
            </v-col>
              <v-divider vertical></v-divider>
              <v-col>
                <v-card dense v-for="post in news" :key="post.title">
                    <v-card-title>
                        {{post.title}}
                    </v-card-title>
                    <v-card-text>
                        By {{post.owner}}
                    </v-card-text>
                </v-card>
              </v-col>
        </v-row>
    </v-container>
</template>
<script>
export default {
  name: 'Devices',
  data: () => ({
    dialog: false,
    logFileContent: '',
    count: 0,
    news: [
      { title: 'Event 1', owner: 'Some Event' },
      { title: 'Event 2', owner: 'Some Event' },
      { title: 'Event 3', owner: 'Some Event' },
      { title: 'Event 4', owner: 'Some Event' }
    ]
  }),
  methods: {
    getLogFileContent () {
      this.logFileContent = 'Logs Changed ' + this.count++
    }
  },
  created () {
    this.logFileContent = 'some content'
    // TODO: get Events
    // this.$http.get("http://localhost:8080/api/{plant-id}/get-events").then(result => {
    //         this.news.push(result.body.slice(0,5));
    //     }, error => {
    //         console.error(error);
    //     });
  }
}
</script>
