<template>
    <v-card>
        <v-card v-for="device in Devices" :key="device.name">
            <v-card-title>
                {{device.name}}
            </v-card-title>
            <v-card-text>
                By {{device.owner}}
            </v-card-text>
        </v-card>
    </v-card>
</template>
<script>
export default {
    name: 'Devices',
    data: () => ({
        Devices: [
          { name: 'Tank 1 Laser Level Transmitter', owner: 'AUT-Labor' , unit: "Meters"},
          { name: 'Tank 1 Temperature', owner: 'AUT-Labor' , unit: "Celcius"},
          { name: 'Tank 2 Pressure', owner: 'AUT-Labor' , unit: "bar"},
          { name: 'Tank 2 Valve', owner: 'AUT-Labor' }
        ],
        employees: []
    }),
    created() {
        this.$http.get("http://localhost:8080/api/device-mgmt/Devices").then(result => {
                this.Devices.push(result.body);
            }, error => {
                console.error(error);
            });
        // this.$http.get("http://dummy.restapiexample.com/api/v1/employees").then(result => {
        //         this.employees = result.body;
        //     }, error => {
        //         console.error(error);
        //     });
    }
}
</script>