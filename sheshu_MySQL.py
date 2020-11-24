# Program to run MySQL commands in command prompt.

import mysql.connector
import time
import Data_in_table_format

connection = mysql.connector.connect(host = "165.22.14.77", user = "Sheshu", password = "Sheshu", database = "dbSheshu")

print("Welcome to the MySQL monitor.  Commands end with ; or \\g.")
print("Your MySQL connection id is " + str(connection.connection_id))
print("Server version: 5.7.32-0ubuntu0.18.04.1 (Ubuntu)")
print("\nCopyright (c) 2000, 2020, Oracle and/or its affiliates. All rights reserved.")
print("\nOracle is a registered trademark of Oracle Corporation and/or its")
print("affiliates. Other names may be trademarks of their respective")
print("owners.")
print("\nType 'help;' or '\\h' for help. Type '\\c' to clear the current input statement.")
while True:
    query = input("mysql> ")
    start = time.time()
    cursor = connection.cursor()
    if query.upper() == 'QUIT' or query.upper() == 'EXIT':
        connection.close()
        print("Bye")
        exit()
    else:
        try:
            cursor.execute(query)
            if query[:3].upper() == 'USE':
                print("Database changed")
        except:
            print("Invalid query.")
            continue
        if query[:4].upper() == 'SHOW' or query[:6].upper() == 'SELECT':
            field_names = [description[0] for description in cursor.description]
            field_values = []
            for field_value in cursor:
                field_values.append(field_value)
            Data_in_table_format.print_data_in_tabular_form(field_names, field_values)
            end = time.time()
            print(str(len(field_values)) + " row(s) in set (" + str(round(end - start , 2)) + " secs)")