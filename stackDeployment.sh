#!/bin/sh

# This file is part of the Stack Deployment of the HTWG IoT Infrastructur Solution
# Author: Mouaad Gssair, M.Gssair@htwg-konstanz.de

# This Script is meant for cloning the Stack Source from a Git Repo and runnung the Install Script
# NOTE: Make sure to run this script with root access
# TODO: Proxy Settings ?

set -e
# Any subsequent(*) commands which fail will cause the shell script to exit immediately
 
# Test if Git is installed
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

# Make Directory if it doesn't already exist
mkdir -p ~/IoTstack
cd  ~/IoTstack
export WORK_DIR=$(PWD)
echo "Working Directory: " $WORK_DIR

# Initializing and cloning Repository
git init
git remote add origin -f https://github.com/GMouaad/HTWG_IoT_Solution.git
git config core.sparseCheckout true
echo "/Stack-Deployment" > .git/info/sparse-checkout
git pull origin master

chmod +x $WORK_DIR/installAll.sh
sudo ./$WORK_DIR/installAll.sh