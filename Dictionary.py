#Dictionary program in python

import requests
import os
import json

app_id = "e7d1b891"
app_key = "1c03fc176dab15bc72d3908825bba27c"
word = input("Enter a word: ")
url = "https://od-api.oxforddictionaries.com:443/api/v2/entries/en-gb/" + word
response = requests.get(url, headers={"app_id": app_id, "app_key": app_key}).json()
audio_url = response['results'][0]['lexicalEntries'][0]['entries'][0]['pronunciations'][0]['audioFile']
definition = response['results'][0]['lexicalEntries'][0]['entries'][0]['senses'][0]['definitions']
print(definition[0])
command = "vlc -I null --play-and-exit " + audio_url
os.system(command)