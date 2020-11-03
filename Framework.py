#Framework program using python

fields_file = "FieldNames.cfg"
menu_file = "Menu.cfg"
data_file = "Data.dat"
updatable_fields_file = "UpdatableFields.cfg"


def add_record():
	status = True
	field_values = []
	field_values.append(status)
	for field_name in field_names:
		print("Enter", field_name, ':', end = "")
		field_value = input()
		field_values.append(field_value)
	records.append(field_values)
	write_data_into_file(records)
	print("Record inserted successfully.")

def search_record():
	id = input("Enter " + field_names[0] + " to find: ")
	is_record_found = False
	for record in records:
		if record[0] == True and record[1] == id:
			is_record_found = True
			print_record(record)
	check_record_found_or_not(is_record_found)

def check_record_found_or_not(record_status):
	if record_status == False:
		print("Record not found.")

def print_all_records():
	for record in records:
		if record[0] == True:
			print("*******************")
			print_record(record)
			
def print_record(record):
	index = 1
	for field_name in field_names:
				print(field_name + ':', record[index])
				index += 1

def update_record():
	id = input("Enter " + field_names[0] + " to find: ")
	updatable_fields_file_obj = open(updatable_fields_file)
	updatable_fields = updatable_fields_file_obj.read()
	updatable_fields_file_obj.close()
	is_record_found = False
	for record in records:
		if record[0] == True and record[1] == id:
			is_record_found = True
			counter = 1
			for updatable_field_position in range(len(updatable_fields)):
				print(str(counter) + '. ' + field_names[int(updatable_fields[counter - 1]) - 1])
				counter += 1
			update_option = int(input("Choose your option: "))
			print("Enter new " + field_names[int(updatable_fields[update_option - 1]) - 1] + ": ", end = "")
			record[int(updatable_fields[update_option - 1])] = input()
			write_data_into_file(records)
			print("Record updated successfully.")
	check_record_found_or_not(is_record_found)
def delete_record():
	id = input("Enter " + field_names[0] + " to find: ")
	is_record_found = False
	for record in records:
		if record[0] == True and record[1] == id:
			is_record_found = True
			record[0] = False
			write_data_into_file(records)
			print("Record deleted successfully.")
	check_record_found_or_not(is_record_found)

def write_data_into_file(records):
	data_file_obj = open(data_file, 'w')
	data_file_obj.write(str(records))
	data_file_obj.close()

def is_file_present(file_name):
	try:
		file_obj = open(file_name)
	except Exception:
		print("File not found or error in opening.")
		exit()

field_names = []
is_file_present(fields_file)
field_names_obj = open(fields_file)
for field_name in field_names_obj.readlines():
	field_name = field_name.rstrip()
	field_names.append(field_name)
field_names_obj.close()

is_file_present(menu_file)
menu_file_obj = open(menu_file)
menu_data = menu_file_obj.read()
menu_file_obj.close()

is_file_present(data_file)
data_file_obj = open(data_file)
records = data_file_obj.read()
data_file_obj.close()
records = eval(records)

while True:
	print(menu_data)
	functions = [add_record, search_record, print_all_records, update_record, delete_record, exit]
	user_option = int(input("Choose your option: "))
	if user_option == 6:
		print("Are you sure? ")
		print("1. Exit\n2. Cancel\nChoose your option: ", end = "")
		exit_option = int(input())
		if exit_option == 1:
			exit()
		else:
			continue
	elif user_option > 0 and user_option < 6:
		functions[user_option - 1]()
	else:
		print("INVALID OPTION.")