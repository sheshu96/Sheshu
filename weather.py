#Weather program using python

import requests

location = input("Enter your location to find temperature: ")
url = "http://api.openweathermap.org/data/2.5/find?appid=d12cf8c5c3c40febafd1dca5750f7eb1&units=metric&q=" + location
weather_info = requests.get(url).json()
temp_from_weather_info = weather_info['list'][0]['main']['temp']
print("Temperature in " + location + " is : " + str (temp_from_weather_info) + chr(176) + "C")