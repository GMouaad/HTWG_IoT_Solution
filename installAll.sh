#!/bin/sh

# This Script is meant for the Stack Installation of the HTWG IoT Infrastructur Solution
# Author: Mouaad Gssair, M.Gssair@htwg-konstanz.de
# NOTE: Make sure to run this script with root access

# Install Docker
curl -sSL https://get.docker.com | sh

# Configure docker to run as non-root user
sudo groupadd docker
sudo usermod -aG docker $USER
newgrp docker

# Install Docker-compose
sudo curl -L "https://github.com/docker/compose/releases/download/1.24.1/docker-compose-$(uname -s)-$(uname -m)" -o /usr/local/bin/docker-compose

# Set Permissions
sudo chmod +x /usr/local/bin/docker-compose

# Verify Installation
docker-compose --version

docker build -f service_dockerfile -t title