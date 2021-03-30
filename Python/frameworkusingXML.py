#Framework program

import xml.etree.ElementTree as xml

def is_file_present(file_name):
	try:
		file_obj = open(file_name)
	except:
		print("File not found or error in opening the file.")
		exit()

def write_data_into_file():
	with open(data_file, 'wb') as file_obj:
		tree.write(file_obj)
	file_obj.close()

def add_record():
	element = xml.Element('Record')
	element.set('Status', 'True')
	for field_name in field_names:
		sub_element = xml.SubElement(element, field_name)
		field_value = input("Enter " + field_name + ": ")
		sub_element.text = field_value
	root.append(element)
	write_data_into_file()
	print("Record Inserted.")

def search_record():
	id = input('Enter ' + field_names[0] + ': ')
	is_record_found = False
	for record in root.findall('Record'):
			is_record_found = check_status_and_id_of_given_record(record, id)
			if is_record_found == True:
				print_record(record)
				break
	verify_and_print_record_not_found(is_record_found)

def verify_and_print_record_not_found(record_status):
	if record_status == False:
		print("Record not found.")

def display_records():
	for record in root.findall('Record'):
		if record.attrib['Status'] == 'True':
			print("*********************")
			print_record(record)

def print_record(record):
	for field_name in field_names:
		print(field_name + ': ' + record.find(field_name).text)

def update_record():
	id = input("Enter " + field_names[0] + ': ')
	is_record_found = False
	is_file_present(updatable_fields_file)
	with open(updatable_fields_file) as updatable_fields_file_obj:
		updatable_fields = updatable_fields_file_obj.read()
	for record in root.findall('Record'):
		is_record_found = check_status_and_id_of_given_record(record, id)
		if is_record_found == True:
			counter = 1
			for update_position in updatable_fields:
				print(str(counter) + ". Update " + field_names[int(update_position) - 1])
				counter += 1
			option = int(input("Cjoose your option: "))
			print("Enter " + field_names[int(updatable_fields[option - 1]) - 1] + ': ', end = "")
			record.find(field_names[int(updatable_fields[option - 1]) - 1]).text = input()
			break
	write_data_into_file()
	print("Record updated successfully.")
	verify_and_print_record_not_found(is_record_found)

def delete_record():
	id = input("Enter " + field_names[0] + ': ')
	is_record_found = False
	for record in root.findall('Record'):
		is_record_found = check_status_and_id_of_given_record(record, id)
		if is_record_found == True:
			record.attrib['Status'] = 'False'
			break
	write_data_into_file()
	print("Record deleted successfully.")
	verify_and_print_record_not_found(is_record_found)

def check_status_and_id_of_given_record(record, id):
	if record.attrib['Status'] == 'True' and record.find(field_names[0]).text == id:
		return True
	else:
		return False

menu_file = "Menu.cfg"
data_file = "Data.xml"
field_file = "FieldNames.cfg"
updatable_fields_file = "UpdatableFields.cfg"

is_file_present(data_file)
tree = xml.parse(data_file)
root = tree.getroot()
field_names = []
is_file_present(field_file)
field_file_obj = open(field_file)
for field_name in field_file_obj.readlines():
	field_name = field_name.rstrip('\n')
	field_names.append(field_name)
field_file_obj.close()

is_file_present(menu_file)
menu_file_obj = open(menu_file)
menu_data = menu_file_obj.read()
menu_file_obj.close()

functions = [add_record, search_record, display_records, update_record, delete_record]
while True:
	print(menu_data)
	user_option = int(input("Choose your option: "))
	if user_option == 6:
		print("Are you sure? ")
		print("1. Exit\n2. Cancel")
		exit_option = int(input("Choose your option: "))
		if exit_option == 1:
			exit()
	elif user_option > 0 and user_option < 6:
		functions[user_option - 1]()
	else:
		print("Invalid.")
