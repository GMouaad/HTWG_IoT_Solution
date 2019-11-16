<template>
    <v-card>
        <v-card v-for="post in news" :key="post.title">
            <v-card-title>
                {{post.title}}
            </v-card-title>
            <v-card-text>
                By {{post.owner}}
            </v-card-text>
        </v-card>
    </v-card>
</template>
<script>
export default {
    name: 'Projects',
    data: () => ({
        news: [
          { title: 'Mobile App Dev', owner: 'Mouaad Gssair' },
          { title: 'PoC Microservices', owner: 'Mouaad Gssair' },
          { title: 'IoT Infrastructur', owner: 'Mouaad Gssair' },
          { title: 'BoW Control System', owner: 'Daniel Grotz & M. Gssair' }
        ],
    }),
    created(){
        // this.$http.get("http://localhost:8080/api/news").then(result => {
        //         this.news.push(result.body.slice(0,5));
        //     }, error => {
        //         console.error(error);
        //     });
    }
}
</script>