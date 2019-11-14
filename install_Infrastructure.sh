#!/bin/sh

# This Script is meant for the Stack Deployment of the HTWG IoT Infrastructur Solution
# Author: Mouaad Gssair, M.Gssair@htwg-konstanz.de
# NOTE: Make sure to run this script with root access
# TODO: Proxy Settings ?

set -e
# Any subsequent(*) commands which fail will cause the shell script to exit immediately

# Test if Git is already installed
echo "Cheking if Git is installed.."
git --version 2>&1 >/dev/null # 
GIT_IS_AVAILABLE=$?
# If return value is 0, then it is installed
if [ $GIT_IS_AVAILABLE -eq 0 ]; then
    echo "Git already installed"
else
    echo "Git is not installed"
    echo "Installing Git"
    sudo apt-get install git
fi

mkdir ~/IoTstack
cd  ~/IoTstack
# Initializing and cloning Repository
git init
git remote add origin -f https://github.com/GMouaad/HTWG_IoT_Solution.git
git config core.sparseCheckout true
echo "/Infrastructure" > .git/info/sparse-checkout
git pull origin master

# Check if Docker is installed and running
echo "Cheking if Docker is running.."
sudo systemctl status docker | grep 'running' &> >/dev/null # 2>&1
# If return value is 0, then it is installed
if [ $? == 0 ]; then
    echo "Docker already running"
else
    echo "Docker is not running"
    echo "Starting Docker"
    sudo systemctl start docker
fi

# Creating Docker Volumes and Network for Metrics Monitoring
cd Infrastructure/Monitoring
docker network create monitoring
docker volume create grafana-volume
docker volume create influxdb-volume

# Cheking if the creation was successful
docker network ls | grep 'monitoring' &> /dev/null
if [ $? == 0 ]; then
   echo "Network created successfully"
fi

docker volume ls | grep 'grafana-volume' &> /dev/null
if [ $? == 0 ]; then
   echo "Volumes created successfully"
fi

# Runing the containers to create the config files and removing them afterwards
docker run --rm \
  -e INFLUXDB_DB=telegraf -e INFLUXDB_ADMIN_ENABLED=true \
  -e INFLUXDB_ADMIN_USER=admin \ # change User!
  -e INFLUXDB_ADMIN_PASSWORD=supersecretpassword \ # change Password!
  -e INFLUXDB_USER=telegraf -e INFLUXDB_USER_PASSWORD=secretpassword \ # change user and password!
  -v influxdb-volume:/var/lib/influxdb \
  influxdb /init-influxdb.sh

# Run the Containers with docker-compose
docker-compose up -d