# HTWG IoT Infrastructure Solution
This  is the Stack of the HTWG IoT Infrastructure Solution
Author: Mouaad Gssair, M.Gssair@htwg-konstanz.de

## Deployment/install
to install the and required software components for it, you have to get the deployment Script "StackDeployment.sh" and run it as root user.
This will itself launch all the  other install Scripts for each component of the Stack.
Alternatibevely tou can clone the hole Repository and run the "installAll.sh" Script from there.
## Architecture
The Stack is based on the Micro-services architectural Pattern
### Infrastructure Layer
 - InfluxDB
 - Grafana
 - Telegraf
 - H2 Database Engine
 - System Monitoring
 
### Application Layer
 - Asset Management Service
 - API-Adater(s)
 
### Business Layer
 - Plant-Browser
 - Operational Monitoring