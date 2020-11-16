#Framework program using Database\

import sqlite3

def read_and_return_data_from_file(file_name):
    try:
        with open(file_name) as file_obj:
            data = file_obj.read()
        file_obj.close()
        return data
    except:
        print("File not found or error while opening.")

def add_record():
    field_values = []
    field_values.append('A')
    for field_name in field_names:
        field_value = input(field_name + ": " , end = "")
        field_values.append(field_value)
    field_values = tuple(field_values)
    print("Details are saved successfully.")
    query = "INSERT INTO + data_file_with_table_name[1] + VALUES" + str(field_values)
    connection.execute(query)
    connection.commit()

def search_record():
    id = input("Enter " + field_names[1] + ": ")
    query = "SELECT * FROM " + data_file_with_table_name[1] + " WHERE " + field_names[1] + " = " + id
    print(connection.execute(query))

def display_records():
    connection.execute("SELECT * FROM data_file_with_table_name[1]")
    print(connection.fetchall())

def update_record():
    print("In update record.")

def delete_record():
    print("In Delete record.")

data_file_with_table_name = "file_name_and_table_name.cfg"
menu_file = "Menu.cfg"
fields_file = "FieldNames.cfg"
updatable_fileds_file = "UpdatableFields.cfg"
fields_and_data_types_file = "Fields_and_data_types.cfg"

menu_data = read_and_return_data_from_file(menu_file)
field_names = read_and_return_data_from_file(fields_file)
field_names = eval(field_names)
fields_and_data_types = read_and_return_data_from_file(fields_and_data_types_file)
data_file_name_and_table_name = read_and_return_data_from_file(data_file_with_table_name)

connection = sqlite3.connect(data_file_with_table_name[0])

functions = [add_record, search_record, display_records, update_record, delete_record]
while True:
    print(menu_data)
    user_option = int(input("Choose your option: "))
    if user_option == 6:
        exit_option = input("Enter 'Y' to exit: ")
        if exit_option.upper() == 'Y':
            exit()
    elif user_option > 0 and user_option < 6:
        functions[user_option - 1]()
    else:
        print("Invalid, please enter a valid option.")