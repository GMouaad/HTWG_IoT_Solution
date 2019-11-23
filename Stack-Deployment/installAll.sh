#!/bin/sh

# This file is part of the Stack Deployment of the HTWG IoT Infrastructure Solution
# Author: Mouaad Gssair, M.Gssair@htwg-konstanz.de

# This Script is meant for installing the basic Requered Software and run the particular
# install Scripts of the HTWG IoT Infrastructur Solution
# NOTE: Make sure to run this script with root access

set -e
# Any subsequent(*) commands which fail will cause the shell script to exit immediately

# Function to install Docker when it's not already installed
installGit () {
    echo "Docker is not installed"
    echo "Installing Docker"
    curl -sSL https://get.docker.com | sh
}


# Make Directory if it doesn't already exist
mkdir -p ~/IoTstack
cd  ~/IoTstack
export WORK_DIR=$(pwd)
echo "Working Directory: " $WORK_DIR

# Install Docker
# Test if Docker is already installed
echo "Cheking if Docker is installed.."
docker --version 2>&1 >/dev/null # 
DOCKER_IS_AVAILABLE=$?
# If return value is 0, then it is installed
if [ $DOCKER_IS_AVAILABLE -eq 0 ]; then
    echo "Docker already installed"
fi

# Configure docker to run as non-root user
sudo groupadd docker
sudo usermod -aG docker $USER
newgrp docker

# Install Docker-compose
echo "Installing Docker-compose"
sudo curl -L "https://github.com/docker/compose/releases/download/1.24.1/docker-compose-$(uname -s)-$(uname -m)" -o /usr/local/bin/docker-compose

# Set Permissions
sudo chmod +x /usr/local/bin/docker-compose

# Test if Installation was successful
echo "Cheking if Installation was successful"
docker-compose --version 2>&1 >/dev/null # Output is not shown on the console
COMPOSE_IS_AVAILABLE=$?
# If return value is 0, then it is installed
if [ $COMPOSE_IS_AVAILABLE -eq 0 ]; then
    echo "Docker-compose installed successfully"
else
    echo "Docker-compose was not installed correctly"
fi

# Run the Portainer with docker-compose
docker-compose up -d

# Run Script to install Infrustructure Components
# TODO: Test this
chmod +x install_Infrastructure.sh
sudo ./install_Infrastructure.sh

mkdir $WORK_DIR/Applications
# install Services...

