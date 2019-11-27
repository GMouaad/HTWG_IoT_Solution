FROM 	nodered/node-red:latest-minimal
LABEL maintainer="M.Gssair@htwg-konstanz.de"
EXPOSE 	8880
EXPOSE  1700/udp
RUN npm install node-red-contrib-influxdb
#RUN npm install node-red-contrib-opcua
COPY settings.js /data
ENV FLOWS=flows.json
ENV NODE_PATH=/usr/src/node-red/node_modules:/data/node_modules

CMD ["npm", "start", "--", "--userDir", "/data"]