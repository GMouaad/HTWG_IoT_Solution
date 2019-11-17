// vue.config.js
module.exports = {
    // proxy all webpack dev-server requests starting with /api
    // to our Spring Boot backend (localhost:8088) using http-proxy-middleware
    // see https://cli.vuejs.org/config/#devserver-proxy
    devServer: {
      proxy: {
        '/': {
          target: 'http://localhost:8080', // this configuration needs to correspond to the Spring Boot backends' application.properties server.port
          ws: true,
          changeOrigin: true
        }
      }
    },
    // Change build paths to make them Maven compatible
    // see https://cli.vuejs.org/config/
    // devServer: {
    //   proxy: 'http://localhost:8002',
    // },
    outputDir: 'target/dist',
    assetsDir: 'static'
  };