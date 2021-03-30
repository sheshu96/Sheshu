#OTP program using  python

import random
import requests

mobile_number = input("Enter mobile number: ")
random_number = random.randint(1000,9999)
url = "http://psms.goforsms.com/API/sms.php?username=srushtiimages&password=tecnics&from=WEBSMS&to=" + mobile_number + "&msg=Your OTP is: " + str(random_number)
response = requests.get(url)
print("OTP has been sent to your mobile number.")
otp = int(input("Enter OTP: "))
if otp == random_number:
	print("Access Granted.")
else:
	print("Invalid OTP, please enter a valid OTP.")
