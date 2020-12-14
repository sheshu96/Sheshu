// CRUD operations using MySQL Database

using System;
using System.Data.Common;
using MySql.Data.MySqlClient;
using MySQLlib;
using System.Data;

public class CRUD
{
    String[] fieldNames;
    String query;
    static MySqlConnection conn;
    public static void Main(String[] args)
    {
        conn = MySQL.ConnectToDB("165.22.14.77", "dbSheshu", "Sheshu", "Sheshu");
        conn.Open();
        CRUD objCRUD = new CRUD();
        objCRUD.StoreFieldNames();
        objCRUD.PrintMenu();
    }

    public void PrintMenu()
    {
        while(true)
        {
            Console.WriteLine("1. Open An Account\n2. Search Account\n3. Print Accounts\n4. Exit");
            Console.Write("Choose your option: ");
            String userOption = Console.ReadLine();
            switch(userOption)
            {
                case "1":
                    AddRecord();
                    break;
                case "2":
                    SearchRecord();
                    break;
                case "3":
                    PrintRecords();
                    break;
                case "4":
                    Console.WriteLine("Do you really want to exit? ");
                    Console.Write("Enter 'Y' to exit or 'N' to continue: ");
                    String exitOption = Console.ReadLine();
                    if(String.Equals(exitOption.ToUpper(), "Y"))
                    {
                        conn.Close();
                        System.Environment.Exit(0);
                    }
                    else if(String.Equals(exitOption.ToUpper(), "N"))
                    {
                        continue;
                    }
                    else
                    {
                        Console.WriteLine("Invalid, please enter a valid option.");
                    }
                    break;
                default:
                    Console.WriteLine("Invalid, please enter a valid option.");
                    break;
            }
        }
    }

    public void AddRecord()
    {
        String fieldValues = "";
        String tempFieldNames = "";
        foreach(String fieldName in fieldNames)
        {
            Console.Write(fieldName + ": ");
            String fieldValue = Console.ReadLine();
            fieldValues += "\'" + fieldValue + "\',";
            tempFieldNames += fieldName + ", ";
        }
        fieldValues += "'A'";
        tempFieldNames += "Status";
        query = "insert into my_table(" + tempFieldNames + ") values(" + fieldValues + ")";
        MySqlCommand cmd = new MySqlCommand(query, conn);
        cmd.ExecuteNonQuery();
        Console.WriteLine("Data Inserted Successfully.");
    }

    public void SearchRecord()
    {
        String idToBeSearched = GetInput();
        bool isRecordFound = CheckWhetherRecordPresentOrNot(idToBeSearched);
        if(isRecordFound)
        {
            query = "select * from my_table where " + fieldNames[0] + " = " + idToBeSearched;
            MySqlCommand cmd = new MySqlCommand(query, conn);
            PrintRecord(cmd);
        }
    }

    public void PrintRecords()
    {
        query = "Select * from my_table where Status = 'A'";
        MySqlCommand cmd = new MySqlCommand(query, conn);
        PrintRecord(cmd);
    }

    public void StoreFieldNames()
    {
        query = "select * from my_table";
        MySqlCommand cmd = new MySqlCommand(query, conn);
        MySqlDataAdapter adapt = new MySqlDataAdapter(cmd);
        DataTable table = new DataTable();
        adapt.Fill(table);
        fieldNames = new String[table.Columns.Count - 1];
        for(int index = 0; index < table.Columns.Count; index++)
        {
            if(String.Equals(table.Columns[index].ToString(), "Status") == false)
            {
                fieldNames[index] = table.Columns[index].ToString();
            }
        }
    }

    private String GetInput()
    {
        Console.Write("Enter " + fieldNames[0] + ": ");
        String id = Console.ReadLine();
        return id;
    }

    public bool CheckWhetherRecordPresentOrNot(String id)
    {
        query = "select * from my_table where Status = 'A' and " + fieldNames[0] + " = " + id;
        MySqlCommand cmd = new MySqlCommand(query, conn);
        DbDataReader reader = cmd.ExecuteReader();
        bool isRecordFound = false;
        if(reader.HasRows)
        {
            isRecordFound = true;
        }
        reader.Close();
        return isRecordFound;
    }

    public void PrintRecord(MySqlCommand cmd)
    {
        MySqlDataAdapter adapt = new MySqlDataAdapter(cmd);
        DataTable table = new DataTable();
        adapt.Fill(table);
        for(int index = 0; index < table.Rows.Count; index++)
        {
            Console.WriteLine("--------------------");
            foreach(String fieldName in fieldNames)
            {
                Console.WriteLine(fieldName + ": " + table.Rows[index][fieldName]);
            }
        }
    }
}