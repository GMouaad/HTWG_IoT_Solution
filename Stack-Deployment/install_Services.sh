#!/bin/sh

# This file is part of the Stack Deployment of the HTWG IoT Infrastructure Solution
# Author: Mouaad Gssair, M.Gssair@htwg-konstanz.de

# This Script is meant for installing the Services (in form of docker containers ?) and run them
# install Scripts of the HTWG IoT Infrastructur Solution
# NOTE: Make sure to run this script with root access

set -e
# Any subsequent(*) commands which fail will cause the shell script to exit immediately

startDocker() {
    echo "Docker is not running"
    echo "Starting Docker"
    sudo systemctl start docker
}

# Make Directory if it doesn't already exist
mkdir -p ~/IoTstack
cd  ~/IoTstack
export WORK_DIR=$(pwd)
echo "Working Directory: " $WORK_DIR


mkdir -p $WORK_DIR/Services
# Make the following for every Service
mkdir $WORK_DIR/Sevices/Plant-Browser
curl https://github.com/GMouaad/HTWG_IoT_Solution/raw/master/src/Services/Plant-Browser/plant-browser-release/plant-browser-service.jar --output $WORK_DIR/Services/Plant-Browser/plant-browser-service.jar

# Check if docker is running
echo "Cheking if Docker is running.."
sudo systemctl status docker | grep 'running' &> >/dev/null || startDocker # 2>&1
# If return value is 0, then it is running
if [ $? == 0 ]; then
    echo "Docker already running"
fi

# Build All the docker images from their docker file
docker image build -f $WORK_DIR/Services/Plant-Browser/Dockerfile -t plant-browser .

# To run the Service 
# docker run -p 8080:8080 --name plant-browser-service plant-browser