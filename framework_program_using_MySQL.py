# Framework program using SQLite

import mysql.connector

def add_record():
    field_values = []
    for field_name in field_names:
        print(field_name + ": ", end = "")
        field_value = input()
        field_values.append(field_value)
    field_values.append('A')
    record = tuple(field_values)
    cursor.execute('INSERT INTO my_table VALUES' + str(record))
    connection.commit()
    print(messages[0])

def search_record():
    id_to_search_record = get_id()
    is_record_found = check_whether_record_present_or_not(id_to_search_record)
    if is_record_found == True:
        cursor.execute('SELECT * FROM my_table WHERE ' + field_names[0] + ' = ' + id_to_search_record)
        record = cursor.fetchone()
        print_record(record)
    else:
        print_record_not_found()

def display_records():
    cursor.execute("SELECT * FROM my_table WHERE Status = 'A'")
    for record in cursor:
        print_record(record)

def update_record():
    id_to_update_record = get_id()
    is_record_found = check_whether_record_present_or_not(id_to_update_record)
    if is_record_found == True:
        counter = 1
        for field_position in updatable_fields:
            print(str(counter) + ". Update " + field_names[int(field_position) - 1])
            counter += 1
        choice = int(input("Enter a number: "))
        print("Enter " + field_names[int(updatable_fields[choice - 1]) - 1] +": ", end = "")
        field_value_to_update_record = input()
        cursor.execute('UPDATE my_table SET ' + field_names[int(updatable_fields[choice - 1]) - 1] + ' = ' + "\"" + field_value_to_update_record + "\""+ ' WHERE ' + field_names[0] + ' = ' + id_to_update_record)
        connection.commit()
        print(messages[1])
    else:
        print_record_not_found()

def delete_record():
    id_to_delete_record = get_id()
    is_record_found = check_whether_record_present_or_not(id_to_delete_record)
    if is_record_found == True: 
        cursor.execute('UPDATE my_table SET Status = "D" WHERE ' + field_names[0] + ' = ' + id_to_delete_record)
        connection.commit()
        print(messages[2])
    else:
        print_record_not_found()
        
def check_whether_record_present_or_not(id):
    is_record_found = False
    cursor.execute('SELECT ' + field_names[0] + ' FROM my_table WHERE Status = "A"')
    for record in cursor:
        if record[0] == id:
            is_record_found = True
    return is_record_found

def print_record(record):
    index = 0
    for field_name in field_names:
        print(field_name + ": " + str(record[index]))
        index += 1
    print('-' * 20)

def read_data_from_file(file_name):
    try:
        with open(file_name) as file_obj:
            data = file_obj.read()
        file_obj.close()
        return data
    except:
        print("File not found or error while opening the file.")

def print_record_not_found():
    print(messages[3])

def get_id():
    id = input("Enter " + field_names[0] + ": ")
    return id

menu_file = "menu.cfg"
updatable_fields_file = "updatable_fields.cfg"
print_messages_file = 'print_messages.cfg'

menu = read_data_from_file(menu_file)
updatable_fields = eval(read_data_from_file(updatable_fields_file))
messages = eval(read_data_from_file(print_messages_file))

connection = mysql.connector.connect(host = "165.22.14.77", user = "Sheshu", password = "Sheshu", database = "dbSheshu")
cursor = connection.cursor()
cursor.execute("select * from information_schema.columns where table_schema = 'dbSheshu' and table_name = 'my_table'")
field_names = []
for field_name in cursor:
    if field_name[3] != 'Status':
        field_names.append(field_name[3])
print(field_names)
functions = [add_record, search_record, display_records, update_record, delete_record]

while True:
    print(menu)
    user_option = int(input("Enter a number: "))
    if user_option == 6:
        print("Do you really want to exit?")
        exit_option = input("Enter 'Y' to exit or Enter 'N' to continue: ")
        if exit_option.upper() == 'Y':
            cursor.close()
            connection.close()
            exit()
    elif user_option > 0 and user_option < 6:
        functions[user_option - 1]()
    else:
        print("Invalid, please enter a valid option.")