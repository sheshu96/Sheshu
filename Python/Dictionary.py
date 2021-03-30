#Dictionary program by providing menu options

import json
import os
from playsound import playsound
import requests
import speech_recognition as sr 

def print_definition():
	definition = response['results'][0]['lexicalEntries'][0]['entries'][0]['senses'][0]['definitions']
	print(definition[0])

def print_synonym():
	print(response['results'][0]['lexicalEntries'][0]['entries'][0]['senses'][0]['synonyms'][0]['text'])

def get_audio():
	audio_url = response['results'][0]['lexicalEntries'][0]['entries'][0]['pronunciations'][0]['audioFile']
	playsound(audio_url)

def print_sentence():
	print(response['results'][0]['lexicalEntries'][0]['entries'][0]['senses'][0]['examples'][0]['text'])

def get_word_by_speak():
	print("Tell a word: ")
	r = sr.Recognizer()
	with sr.Microphone() as source:
		audio_data = r.record(source, duration = 2)
		word = r.recognize_google(audio_data)
		print(word)
		return word

def get_word_by_type():
	word = input("Enter a word: ")
	return word

functions = [print_definition, print_synonym, get_audio, print_sentence]
print("Do you want to: ")
print("1. Type\n2. Speak")
option = int(input("Choose your option: "))
if option == 1:
	word = get_word_by_type()
elif option == 2:
	word = get_word_by_speak()
url = "https://od-api.oxforddictionaries.com:443/api/v2/entries/en-gb/" + word
response = requests.get(url, headers = {"app_id" : "e7d1b891", "app_key" : "1c03fc176dab15bc72d3908825bba27c"}).json()
print("1. Definition\n2. Synonym\n3. Audio\n4. Sentence\n5. Exit")
user_option = int(input("Choose your option: "))
if user_option == 5:
	print("Are you sure? ")
	print("1. Exit\n2. Cancel")
	exit_option = int(input("Choose your option: "))
	if exit_option == 1:
		exit()
elif user_option > 0 and user_option < 5:
	functions[user_option - 1]()
else:
	print("Invalid, please enter a valid option.")
