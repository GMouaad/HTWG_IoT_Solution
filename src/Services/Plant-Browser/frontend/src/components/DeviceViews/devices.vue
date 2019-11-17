<template>
    <v-flex xs12>
        <v-container grid-list-xl>
        <v-layout row wrap align-start>
            <v-flex xs12 md4 v-for="device in devices" :key="device.id">
            <v-card max-width="400" height="150">
                <v-card-title>{{device.name}}</v-card-title>
                <v-card-text>
                <p>Device-ID : {{device.id}}
                    <br/>
                Owner : {{device.owner}}</p>
                </v-card-text>
            </v-card>
            </v-flex>
        </v-layout>
        </v-container>
    </v-flex>
</template>
<script>
export default {
  name: 'Devices',
  data: () => ({
    devices: [
      {
        id: 234,
        name: 'Tank 1 Laser Level Transmitter',
        owner: 'AUT-Labor',
        unit: 'Meters',
        plantID: 1,
        type: null,
        description: null,
        installationDate: null,
        lastLocation: { longitude: 9.1707902, latitude: 47.6675354 },
        lastUpdate: null,
        additionlProps: {}
      },
      {
        id: 218,
        name: 'Tank 1 Temperature',
        owner: 'AUT-Labor',
        unit: 'Celcius',
        plantID: 1,
        type: null,
        description: null,
        installationDate: null,
        lastLocation: { longitude: 9.1707902, latitude: 47.6675354 },
        lastUpdate: null,
        additionlProps: {}
      },
      { id: 736,
        name: 'Tank 2 Pressure',
        owner: 'AUT-Labor',
        unit: 'bar',
        plantID: 1,
        type: null,
        description: null,
        installationDate: null,
        lastLocation: { longitude: 9.1707902, latitude: 47.6675354 },
        lastUpdate: null,
        additionlProps: {} },
      { id: 312,
        name: 'Tank 2 Valve',
        owner: 'AUT-Labor',
        plantID: 1,
        type: null,
        description: null,
        installationDate: null,
        lastLocation: { longitude: 9.1707902, latitude: 47.6675354 },
        lastUpdate: null,
        additionlProps: {} }
    ]
  }),
  created () {
    this.$http.get('/api/device-mgmt/Devices').then(
      result => {
        // this.devices.push(result.body)
        // Array.prototype.push.apply(this.devices, result.body)
        for (let index = 0; index < result.body.length; index++) {
          this.devices.push(result.body[index])
        }
      },
      error => {
        console.error(error)
      }
    )
  }
}
</script>
