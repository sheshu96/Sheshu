#Bank customer program in python

import pyttsx3

class Customer:

	def __init__(self, balance = 0.0):
		self.balance = balance

	def deposit(self):
		amount = int(input("Enter amount to deposit: "))
		self.balance = self.balance + amount
		prompt = "After deposit, your account balance is: " + str(self.balance)
		print(prompt)
		get_sound(prompt)

	def withdraw(self):
		amount = int(input("Enter amount to withdraw: "))
		if amount < self.balance:
			self.balance = self.balance - amount
			prompt = "After withdraw, your account balance is: " + str(self.balance)
			print(prompt)
			get_sound(prompt)
		else:
			prompt = "Insufficient funds."
			print(prompt)
			get_sound(prompt)

	def balance_enquiry(self):
		prompt = "Your account balance is: " + str(self.balance)
		print(prompt)
		get_sound(prompt)

def get_sound(text):
	sound.say(text)
	sound.runAndWait()

sound = pyttsx3.init()
customer = Customer()
name = input("Enter your name: ")
prompt = "Hello, " + name + " your account got created."
print(prompt)
get_sound(prompt)
functions = [customer.balance_enquiry, customer.deposit, customer.withdraw]
while True:
	print("1. Balance Enquiry\n2. Deposit\n3. Withdraw\n4. Exit")
	prompt = "Choose your option: "
	get_sound(prompt)
	user_option = int(input(prompt))
	if user_option == 4:
		print("Are you sure? \n1. Exit\n2. Cancel")
		exit_option = int(input("Choose your option: "))
		if exit_option == 1:
			prompt = "Thanks for Banking. Please visit us again."
			print(prompt)
			get_sound(prompt)
			exit()
	elif user_option > 0 and user_option < 4:
		functions[user_option - 1]()
	else:
		prompt = "Invalid, please enter a valid option."
		print(prompt)
		get_sound(prompt)
