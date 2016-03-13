# The Smart Bar System
The purpose of the Smart Bar system is to build an automated drink dispensing machine that could measure out accurate volumes of mixers or liquors. Unlike existing machines with similar functionality, the SmartBar is an inexpensive device and is capable of dispensing a mixer; which the other products cannot do. Additionally, the SmartBar employed a computer program that could be displayed on a screen to show the selection of drinks available.

## Overview
![alt tag](https://github.com/jkai/SmartBar/blob/master/Images/1.PNG)

The SmartBar was implemented by using a microcontroller that operated the system of pumps, valves and sensors. The microcontroller was connected to a screen to show available drink options. The microcontroller was supplied with power by a phone charger. The pumps were supplied with power from a 12V source. The overall power consumption of the system was low.

## Design
![alt tag](https://github.com/jkai/SmartBar/blob/master/Images/2.PNG)

Several integrated valves and pumps in the switching circuit were used for dispensing the liquid and were controlled by the General-purpose input/output (GPIO) pins on Raspberry Pi; selected GPIO pins would be turn on to active the switching circuit based on the user input to Raspberry Pi. In addition, several GPIO pins were used as inputs to obtain data from integrated sensors.

Besides, the Java Remote Method Invocation (Java RMI) API was used in this project to implement the functionality that allows the customer ordering drinks through Internet. A Java RMI server would be running in Raspberry Pi’s operation system, the RMI client that runs in a PC pass request to RMI server to manipulate GPIO pins through Ethernet interface.

![alt tag](https://github.com/jkai/SmartBar/blob/master/Images/4.PNG)

## GUI Snapshot
![alt tag](https://github.com/jkai/SmartBar/blob/master/Images/3.PNG)
