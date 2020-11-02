#framework program using python

fields_file = "FieldNames.cfg"
menu_file = "Menu.cfg"
data_file = "Data.dat"
updatable_fields_file = "UpdateFieldValues.cfg"

field_names = []
field_names_obj = open(fields_file)
for field_name in field_names_obj.readlines():
	field_name = field_name.rstrip()
	field_names.append(field_name)
	field_names_obj.close()

menu_file_obj = open(menu_file)
menu_data = menu_file_obj.read()
menu_file_obj.close()

data_file_obj = open(data_file)
records = data_file_obj.read()
data_file_obj.close()
records = eval(records)

def is_file_found(file_name):
	try:
		file_obj = open(file_name)
	except Exception:
		print("File not found or error in opening file.")
		exit()

def add_record():
	status = True
	field_values = []
	field_values.append(status)
	for field_name in field_names:
		print(field_name + ': ', end = "")
		field_values.append(input())
	records.append(field_values)
	is_file_found(data_file)
	data_file_obj = open(data_file, 'w')
	data_file_obj.write(str(records))
	data_file_obj.close()
	print("Record inserted successfully.")

def search_record():
	id = input("Enter " + field_names[0] + "to find: ")
	is_record_found = False
	for record in records:
		if record[0] == True and record[1] == id:
			is_record_found = True
			index = 1
			for field_name in field_names:
				print(field_name + ': ', record[index])
				index += 1
	if is_record_found == False:
		print_record_not_found()

def print_record_not_found():
	print("Record not found.")

def print_all_records():
	for record in records:
		if record[0] == True:
			print("********************")
			index = 1	
			for field_name in field_names:
				print(field_name + ':', record[index])
				index += 1

def update_record():
	id = input("Enter " + field_names[0] + "to find: ")
	is_record_found = False
	is_file_found(updatable_fields_file)
	update_fields_file_obj = open(updatable_fields_file)
	updatable_field_values = update_fields_file_obj.read()
	for record in records:
		if record[0] == True and record[1] == id:
			is_record_found = True
			counter = 1
			for update_values in range(len(updatable_field_values)):
				print(str(counter) + '. Update', field_names[int(updatable_field_values[counter - 1]) - 1])
				counter += 1
			update_option = int(input("Choose your option: "))
			print("Enter new " + field_names[int(updatable_field_values[update_option - 1]) - 1] + ': ', end = "")
			record[int(updatable_field_values[update_option - 1])] = input()
			data_file_obj = open(data_file, 'w')
			data_file_obj.write(str(records))
			data_file_obj.close()
			print("Record updated successfully.")
	if is_record_found == False:
		print_record_not_found()


def delete_record():
	id = input("Enter " + field_names[0] + " to find: ")
	is_record_found = False
	for record in records:
		if record[0] == True and record[1] == id:
			is_record_found = True
			record[0] = False
			is_file_found(data_file)
			data_file_obj = open(data_file, 'w')
			data_file_obj.write(str(records))
			data_file_obj.close()
			print("Record deleted successfully.")
	if is_record_found == False:
		print_record_not_found()

while True:
	print(menu_data)
	functions = [add_record, search_record, print_all_records, update_record, delete_record, exit]
	user_option = int(input("Choose your option: "))
	if user_option > 0 and user_option < 7:
		functions[user_option - 1]()
	else:
		print("INVALID OPTION.")