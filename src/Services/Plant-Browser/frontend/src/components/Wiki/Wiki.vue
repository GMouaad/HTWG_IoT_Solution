<template>
  <div>
      <v-container>
          <v-row align-center>
            <v-col cols="1" shrink sm="4" md="4" lg="3">
              <v-card pa-2 ma-2 class="blue lighten-5" shrink>
                  <v-card-title>Content</v-card-title>
                      <v-treeview
                            :items="contentSections"
                            activatable hoverable dense open-all>
                      </v-treeview>
                </v-card>
              </v-col>
              <v-divider vertical></v-divider>
              <v-col  cols="2" sm="8" md="7" lg="8">
                  <wiki-section v-for="section in contentSections" :key="section.id"
                  :title="section.name" :content="section.content" v-bind:subsections="section.children"/>
              </v-col>
          </v-row>
      </v-container>
      <v-card>

      </v-card>
  </div>
</template>

<script>

// import WikiSection from './wikiSection.vue'

export default {
  data: () => ({
    components: {
    //   WikiSection
    },
    contentSections: [
      {
        id: 1,
        name: 'Getting Started',
        content: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut sem nulla pharetra diam sit amet nisl suscipit. Vitae auctor eu augue ut lectus arcu. Consectetur adipiscing elit ut aliquam purus sit amet luctus. Congue nisi vitae suscipit tellus.'
      },
      {
        id: 2,
        name: 'Device Management',
        content: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut sem nulla pharetra diam sit amet nisl suscipit. Vitae auctor eu augue ut lectus arcu. Consectetur adipiscing elit ut aliquam purus sit amet luctus. Congue nisi vitae suscipit tellus.',
        children: [
          {
            id: 1,
            name: 'Registering a new Device',
            content: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut sem nulla pharetra diam sit amet nisl suscipit. Vitae auctor eu augue ut lectus arcu. Consectetur adipiscing elit ut aliquam purus sit amet luctus. Congue nisi vitae suscipit tellus.'
          },
          {
            id: 2,
            name: 'Connecting a new Device',
            content: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut sem nulla pharetra diam sit amet nisl suscipit. Vitae auctor eu augue ut lectus arcu. Consectetur adipiscing elit ut aliquam purus sit amet luctus. Congue nisi vitae suscipit tellus.'
          }
        ]
      },
      {
        id: 4,
        name: 'Monitoring your Plant',
        content: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut sem nulla pharetra diam sit amet nisl suscipit. Vitae auctor eu augue ut lectus arcu. Consectetur adipiscing elit ut aliquam purus sit amet luctus. Congue nisi vitae suscipit tellus.',
        children: []
      }
    ]
  })
}
</script>

<style>

</style>
