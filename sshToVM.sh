#!/bin/sh

# This file is part of the Stack Development of the HTWG IoT Platform Solution
# Author: Mouaad Gssair, M.Gssair@htwg-konstanz.de

# This Script is meant for connecting to the VM Where the Stack is deployed using the ssh client port forwarding
# NOTE: Make sure to run this script with root access

set -e
# Any subsequent(*) commands which fail will cause the shell script to exit immediately


# Include the following function in $HOME/.bashrc to be able to call them directly from the terminal

# Function to open SSH Session in non-interractive mode, i.e. no commands can be executed, 
# with the port forwarding option to access the different ports of the Apps/Services on the VM
sshToVM_passiv () {
    echo "Starting SSH Client with Port Forwarding"
    echo "Non-interactive mode"
    ssh -4 -N -L 9000:localhost:9000 -L 3000:localhost:3000 -L 1880:localhost:1880 -L 1885:localhost:1885 -L 8885:localhost:8885 -L 8983:localhost:8883 -L 1883:localhost:1883 -L 1884:localhost:1884 -L 8884:localhost:8884 mgssair@192.168.200.98
}

# Function to open SSH Session in interactive mode, i.e. commands can be executed, 
# with the port forwarding option to access the different ports of the Apps/Services on the VM
sshToVM_interractive () {
    echo "Starting SSH Client with Port Forwarding"
    echo "Interactive mode"
    ssh -4 -L 9000:localhost:9000 -L 3000:localhost:3000 -L 1880:localhost:1880 -L 1885:localhost:1885 -L 8885:localhost:8885 -L 8983:localhost:8883 -L 1883:localhost:1883 -L 1884:localhost:1884 -L 8884:localhost:8884 mgssair@192.168.200.98
}

# Function to open SSH Session in non-interactive mode, i.e. no commands can be executed, 
# with the port forwarding option to access the different ports of the Apps/Services on the VM
sshToGW_passiv () {
    echo "Starting SSH Client with Port Forwarding"
    echo "Non-interactive mode"
    ssh -4 -N -L 8080:localhost:80 -L 8443:localhost:443 root@192.168.200.97
}

# Function to open SSH Session in interactive mode, i.e. commands can be executed, 
# with the port forwarding option to access the different ports of the Apps/Services on the VM
sshToGW_interractive () {
    echo "Starting SSH Client with Port Forwarding"
    echo "Interactive mode"
    ssh -4 -L 8080:localhost:80 -L 8443:localhost:443 root@192.168.200.97
}

# Function to open SSH Session in interactive mode, i.e. commands can be executed, 
# with the port forwarding option to access the different ports of the Apps/Services on the Machine
# NOTE: Change the username FHKN.<username>@... to your username to login
sshToEA165_interractive () {
    echo "Starting SSH Client with Port Forwarding"
    echo "Interactive mode"
    ssh -4 -L 9000:localhost:9000 -L 3000:localhost:3000 -L 8880:localhost:1880 -L 1885:localhost:1885 -L 8885:localhost:8885 -L 8983:localhost:8983 -L 1883:localhost:1883 -L 1884:localhost:1884 -L 8884:localhost:8884 FHKN.mo721gss@ea-pc165.ei.htwg-konstanz.de
}