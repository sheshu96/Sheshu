#Program to send mail

import smtplib

user_mail_id = input("Enter mail id: ")
user_password = input("Enter password: ")
recipient_mail_id = input("Enter recipient mail id: ")
message = input("Enter your message: ")
server = smtplib.SMTP('smtp.gmail.com', 587)
server.starttls()
server.login(user_mail_id, user_password)
server.sendmail(user_mail_id, recipient_mail_id, message)
print("Mail sent.")
server.quit()
