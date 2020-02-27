#!/usr/bin/env python3
from rak811 import Mode, Rak811
from random import randrange, uniform
from sys import exit
from time import sleep
#import struct

# https://pypi.org/project/pycayennelpp/
# pip3 install pycayennelpp
from cayennelpp import LppFrame

# random number between 30 and 40, rounded to 2 decimal numbers
frandTemp =round(uniform(30, 40),2)
frandHum =round(uniform(30, 40),2)

# create empty frame
frame = LppFrame()
# add some sensor data
frame.add_temperature(0, frandTemp)     # add_temperature(self, channel, value)
#frame.add_humidity(6, frandHum)         # add_humidity(self, channel, value)
# get byte buffer in CayenneLPP format
buffer = frame.bytes()
print('LoRa Setup')
lora = Rak811()
lora.hard_reset()
lora.mode = Mode.LoRaWan
lora.band = 'EU868'
lora.set_config(dev_eui='323833354E386202',
app_eui='70B3D57ED0027534',
app_key='F994F6E96B72A3CE9C0D4AE9D3DDC03B')
print('Joining:OTAA')
lora.join_otaa()
lora.dr = 4

print('Sending packets every minute - Interrupt to cancel loop')
print('You can send downlinks from the TTN console')
try:
    while True:
        print('Send packet')
        # Cayenne lpp random value as analog
        #lora.send(bytes.fromhex('0102{:04x}'.format(randint(0, 0x7FFF))))
        # or using the CayenneLPP Lib:  lora.send(buffer)

        # only for demo:
        frandTemp =round(uniform(30, 40),2)
        print("Sending:",frandTemp)
        buffer = str(frandTemp)
        lora.send(buffer)
        print("Message sent")

        while lora.nb_downlinks:
            print('Received', lora.get_downlink()['data'].hex())
        sleep(60)
except Exception as e: print(e)

print('Cleaning up')
lora.close()
exit(0)