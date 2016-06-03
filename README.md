# heat-control

Heat Control system of a home which allows owners to control the temperature of their homes.
The HeatControl System is basically a home temperature controller. The home has rooms and each room has a thermostat. 
The basic functionality of this system allows the owners to:
a)	Turn the system On or Off.
b)	Choose the temperature (in Fahrenheit), for the entire home or by room. 
c)	Choose the duration (in mins) for which the temperature should be maintained.
d)	Print the current temperature in Fahrenheit. 
In a typical usage scenario of the thermostat, the user turns on the thermostat (if it is not already On), chooses the temperature setting, selects the area (the entire house or by room) for which the temperature should hold and the duration for the temperature. The user then starts the thermostat. 
The time at which the start is invoked is noted in the thermostat and the stop time is calculated, using the mins set for the duration. For example, if the user chooses duration of 5 mins, and starts the thermostat at 10:00 AM, the stop time should be calculated to be 10:05AM.

You need to use the Interface Thermostat which HomeControl System implements.
