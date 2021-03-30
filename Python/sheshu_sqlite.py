# Write a program to run SQLite without using SQLite3 in shell command

import sqlite3
import sys
from printy import printy
import subprocess

def get_connection_with_database(database_name):
    return sqlite3.connect(database_name)

print("SQLite version 3.33.0 2020-08-14 13:23:32")
print("Enter \".help\" for usage hints.")
if len(sys.argv) == 2:
    database_name = sys.argv[1]
else:
    print("Connected to a ", end = "")
    printy("transient in-memory database.", "r")
    print("Use \".open FILENAME\" to reopen on a persistent database.")
    database_name = "Test.db"

connection = get_connection_with_database(database_name)
while True:
    query = input("sqlite> ")
    if query[0] != ".":
        query = query.replace(";", "")
        try:
            output = connection.execute(query)
        except:
            print("Invalid query.")
        records = output.fetchall()
        if records != []:
            for record in records:
                for index in range(len(record)):
                    print(record[index], "|", end ="")
                print()
    else:
        if query == ".quit":
            connection.close()
            exit()
        else:
            subprocess.run(['SQLite3', database_name, query])
