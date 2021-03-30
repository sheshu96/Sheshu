#Weather program using python

import requests

location = input("Enter your location to find temperature: ")
url = "http://api.openweathermap.org/data/2.5/find?appid=d12cf8c5c3c40febafd1dca5750f7eb1&units=metric&q=" + location
weather_data = requests.get(url).json()
temperature = weather_data['list'][0]['main']['temp']
print("Temperature in " + location + " is: ", temperature, chr(176) + "C")
