# AUT-Wi-IOT
Entwicklungsprojekt von Mouaad Gssair

# Description 
This  is the Stack of the HTWG IoT Infrastructure Solution
Author: Mouaad Gssair, M.Gssair@htwg-konstanz.de

## Deployment/install
to install the and required software components for it, you have to get the deployment Script "StackDeployment.sh" and run it as root user.
This will itself launch all the  other install Scripts for each component of the Stack.
Alternatibevely tou can clone the hole Repository and run the "installAll.sh" Script from there.
## Architecture
The Stack is based on the Micro-services architectural Pattern
### Infrastructure Layer
 - System Monitoring ( InfluxDB - Grafana - Telegraf)
 - TTN Stack (Netwerk Server)
 - CockroachDB (mainly for TTN Stack)
 - Redis (Mainly for TTN Stack)
 
### Application Layer
 - MQTT-Connector
 - API-Adater(s)
 
### Business Layer
 - Plant-Browser
 - Operational Monitoring (InfluxDB - Grafana)