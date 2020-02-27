# TTN Stack
This Subrepository is meant for the Install and Deployment of The Things Network Stack V3.3.1

## Components

### Scalable SQL Database: CockroachDB
Web UI for Visualization of Metrics, DBs etc. running on http://127.0.0.1:26256

### Redis DB
On Memory DB for 'hot' Data.
Redis Server running on 127.0.0.1:6379

#### Connect to remote Redis Server
```bash
redis-cli -h XXX.XXX.XXX.XXX -p YYYY
```
xxx.xxx.xxx.xxx is the IP address and yyyy is the port
Or 
```bash
redis-cli -u redis://user:pass@host:port
```
In case of password : redis-cli -h host -p port -a password
#### Install redis-cli
Ubuntu (tested on 14.04) has package called redis-tools which contains redis-cli among other tools. To install it type:
```
sudo apt-get install redis-tools
```
#### Install TTN Stack
 - Configure the Stack in the docker-compose.yml file
 - cd to the directory of the file and run the following cmds. Or use the -f <path/to/file.yml> argument

```
docker-compose pull
```

```
docker-compose run --rm stack is-db init
```

```
docker-compose run --rm stack is-db create-admin-user   --id admin   --email M.Gssair@htwg-konstanz.de
```
```
docker-compose run --rm stack is-db create-oauth-client   --id cli   --name "Command Line Interface"   --owner admin   --no-secret   --redirect-uri "local-callback"   --redirect-uri "code"
```
```
docker-compose run --rm stack is-db create-oauth-client   --id console   --name "Console"   --owner admin   --secret console  --redirect-uri  --redirect-uri "http://localhost:1885/console/oauth/callback" --redirect-uri "https://localhost:8885/console/oauth/callback"  --redirect-uri "/console/oauth/callback"
```

or
```
docker-compose run --rm stack is-db create-oauth-client   --id console   --name "Console"   --owner admin   --secret console  --redirect-uri  --redirect-uri "http://wireless-iot-pc.aut.ei.htwg-konstanz.de:1885/console/oauth/callback" --redirect-uri "https://wireless-iot-pc.aut.ei.htwg-konstanz.de:8885/console/oauth/callback"  --redirect-uri "/console/oauth/callback"
```

```
docker-compose up -d
```

```
stack is-db create-oauth-client   --id plant-browser   --name "plant-browser"   --owner admin   --secret topsecret   --redirect-uri "http://localhost:8080/#/geolocator"
```

### Ports
Service/Server | Port
-------------- | ---------------
UDP Socket | 1700
GW Configuration MQTT Broker | 1882
GW Configuration MQTT TLS Broker | 8882
Application Server MQTT Broker | 1883
Application Server MQTT/TLS Broker | 8883
gRPC | 1884 
encrypted gRPC | 8884
Console (Web UI/HTTP) | 1885
Console (Web UI/HTTPS) | 8885
Basic Station LNS Websocket (ws) | 1887
Basic Station LNS Secure Websocket (wss) | 8887
