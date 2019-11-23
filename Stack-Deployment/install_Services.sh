#!/bin/sh

# This file is part of the Stack Deployment of the HTWG IoT Infrastructure Solution
# Author: Mouaad Gssair, M.Gssair@htwg-konstanz.de

# This Script is meant for installing the basic Requered Software and run the particular
# install Scripts of the HTWG IoT Infrastructur Solution
# NOTE: Make sure to run this script with root access

set -e
# Any subsequent(*) commands which fail will cause the shell script to exit immediately

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
# Build All the docker images from their docker file
#docker build -f service_dockerfile -t title