<template>
  <div ma-2>
      <h1>{{title}}</h1>
      <p>{{content}}</p>
      <div v-for="subsection in subsections" :key="subsection.name" ma-2>
          <h3>{{subsection.name}}</h3>
          <p>{{subsection.content}}</p>
      </div>
      <v-divider></v-divider>
  </div>
</template>

<script>
export default {
  name: 'WikiSection',
  props: {
    title: String,
    content: String,
    subsections: Array
  }
}
</script>

<style>
p{
    padding-left: 20px;
}
h3{
    padding-left: 10px;
}
</style>
