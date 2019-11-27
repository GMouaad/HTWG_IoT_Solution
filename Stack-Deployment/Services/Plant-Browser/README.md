# Plant Browser
This Service enables the user to navigate through the devices in his plant, locate the devices and See the Log file of the Plant.

## Dependencies
This Service dependes on the Database from where it queries the Data of the Plant/Devices

## Run on Docker

### Build image 
 - docker image build -t plant-browser .
### Run the image
 - docker run -p 8080:8080 --name plant-browser-service plant-browser