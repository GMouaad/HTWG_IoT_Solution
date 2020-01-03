<template>
<v-footer dark padless>
    <v-card width="100%" flat tile class="indigo lighten-1 white--text text-center">
      <!-- <v-card-text>
          <p class="white--text pt-0">
              Reach out to us on Social Media!
          </p>
        <v-btn v-for="icon in icons" :key="icon" class="mx-4 white--text" icon>
          <v-icon size="18px">{{ icon }}</v-icon>
        </v-btn>
      </v-card-text> -->

      <v-divider></v-divider>

      <v-card-text class="white--text">
        {{ new Date().getFullYear() }} — <strong>HTWG IoT</strong>
      </v-card-text>
    </v-card>
  </v-footer>
</template>
<script>
export default {
  name: 'HomeFooter',
  props: {
    title: String
  },
  data: () => ({
    icons: [
      'fab fa-facebook',
      'fab fa-twitter',
      'fab fa-google-plus',
      'fab fa-linkedin',
      'fab fa-instagram'
    ]
  })
}
</script>
