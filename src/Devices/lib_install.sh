#!/bin/sh

# This file is part of the Stack Development of the HTWG IoT Platform Solution
# Author: Mouaad Gssair, M.Gssair@htwg-konstanz.de

# This Script is meant for installing the necessary Librares for the lora Devices
# NOTE: Make sure to ..

set -e
# Any subsequent(*) commands which fail will cause the shell script to exit immediately

sudo apt install python3-pip

sudo pip3 install rak811

pip3 install pycayennelpp