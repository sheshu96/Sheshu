// Framework program in Java

import java.sql.*;
import java.util.Scanner;

class Framework
{
	Scanner scan = new Scanner(System.in);
	Statement stmt;
	ResultSet result;
	static Connection conn;
	static String errorMessage = "Invalid, please enter a valid option.";
	static String[] fieldNames;
	static String[] promptMessages;
	static Framework obj;

	public static void main(String args[])
	{
		try
		{
			obj = new Framework();
			connectToDB();
			storeFieldNames();
			String messages = getData("Messages");
			promptMessages = messages.split(",");
			printMenu();
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	}

	public static void printMenu() throws Exception
	{
		String menu = getData("Menu");
		while(true)
		{
			System.out.print(menu + "\nChoose your option: ");
			int userOption = obj.scan.nextInt();
			switch(userOption)
			{
				case 1:
					addRecord();
					break;
				case 2:
					searchRecord();
					break;
				case 3:
					printRecords();
					break;
				case 4:
					updateRecord();
					break;
				case 5:
					deleteRecord();
					break;
				case 6:
					System.out.println("Do you really want to exit? ");
					System.out.println("Enter 'Y' to exit or 'N' to continue: ");
					String exitOption = obj.scan.next();
					if(exitOption.toUpperCase().equals("Y"))
					{
						obj.stmt.close();
						conn.close();
						System.exit(0);
					}
				default:
					System.out.println(errorMessage);
			}
		}
	}

	public static void connectToDB()
	{
		String url = "jdbc:mysql://165.22.14.77/dbSheshu?user=Sheshu&password=Sheshu";
		try
		{
			conn = DriverManager.getConnection(url);
			System.out.println("Connection successful.");
			obj.stmt = conn.createStatement();
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	}

	public static String getData(String fileName) throws SQLException
	{
		obj.result = obj.stmt.executeQuery("select Content from Config where filename = '" + fileName + "'");
		String data = "";
		while(obj.result.next())
		{
			data = obj.result.getString("Content");
		}
		return data;
	}

	public static void storeFieldNames() throws SQLException
	{
		obj.result = obj.stmt.executeQuery("Select * from my_table");
		ResultSetMetaData rsmd = obj.result.getMetaData();
		fieldNames = new String[rsmd.getColumnCount() - 1];
		for(int index = 0; index < fieldNames.length; index++)
		{
			if(rsmd.getColumnName(index + 1).equals("Status") != true)
			{
				fieldNames[index] = rsmd.getColumnName(index + 1);
			}
		}
	}

	public static void addRecord() throws SQLException
	{
		String fieldValues = "";
		for(int index = 0; index < fieldNames.length; index++)
		{
			System.out.print(fieldNames[index] + ": ");
			String fieldValue = obj.scan.nextLine();
			fieldValues += "'" + fieldValue + "', ";
		}
		fieldValues += "'A'";
		int rowsAffected = obj.stmt.executeUpdate("insert into my_table values(" + fieldValues + ")");
		if(rowsAffected == 1)
		{
			System.out.println(promptMessages[0]);
		}
		
	}

	public static void searchRecord() throws SQLException
	{
		String recordToBeSearched = getInput();
		boolean isRecordFound = checkWhetherRecordPresentOrNot(recordToBeSearched);
		if(isRecordFound)
		{
			obj.result = obj.stmt.executeQuery("select * from my_table where Status = 'A' and " + fieldNames[0] + " = " + recordToBeSearched);
			printRecord(obj.result);
		}
		else
		{
			printRecordNotFound();
		}
	}

	public static void printRecord(ResultSet result) throws SQLException
	{
		while(result.next())
		{
			System.out.println("-----------------------");
			for(String fieldName : fieldNames)
			{
				System.out.println(fieldName + ": " + result.getString(fieldName));
			}
		}
	}

	public static void printRecords() throws SQLException
	{
		obj.result = obj.stmt.executeQuery("select * from my_table where Status = 'A'");
		printRecord(obj.result);	
	}

	public static void updateRecord() throws SQLException
	{
		String recordToBeUpdated = getInput();
		boolean isRecordFound = checkWhetherRecordPresentOrNot(recordToBeUpdated);
		if(isRecordFound)
		{
			String tempUpdatableFields = getData("UpdatableFields");
			String updatableFields[] = tempUpdatableFields.split(",");
			int[] updatableFieldPositions = new int[updatableFields.length];
			for(int index = 0; index < updatableFieldPositions.length; index++)
			{
				updatableFieldPositions[index] = Integer.parseInt(updatableFields[index]);
			}
			int counter = 1;
			for(int index = 0; index < updatableFieldPositions.length; index++)
			{
				System.out.println(counter + ". Update " + fieldNames[updatableFieldPositions[index] - 1]);
				counter++;
			}
			System.out.print("Choose your option: ");
			int updateOption = obj.scan.nextInt();
			System.out.print("Enter new " + fieldNames[updatableFieldPositions[updateOption - 1] - 1] + ": ");
			String updateValue = obj.scan.next();
			int rowsAffected = obj.stmt.executeUpdate("update my_table set " + fieldNames[updatableFieldPositions[updateOption - 1] - 1] + " = '" + updateValue + "' where " + fieldNames[0] + " = " + recordToBeUpdated);
			if(rowsAffected == 1)
			{
				System.out.println(promptMessages[1]);
			}
		}
		else
		{
			printRecordNotFound();
		}
	}

	public static void deleteRecord() throws SQLException
	{
		String recordToBeDeleted = getInput();
		boolean isRecordFound = checkWhetherRecordPresentOrNot(recordToBeDeleted);
		if(isRecordFound)
		{
			int rowsAffected = obj.stmt.executeUpdate("update my_table set Status = 'D' where " + fieldNames[0] + " = " + recordToBeDeleted);
			if(rowsAffected == 1)
			{
				System.out.println(promptMessages[2]);
			}
		}
		else
		{
			printRecordNotFound();
		}
	}

	public static void printRecordNotFound()
	{
		System.out.println(promptMessages[3]);
	}

	public static String getInput()
	{
		System.out.print("Enter " + fieldNames[0] + ": ");
		String id = obj.scan.next();
		return id;
	}

	public static boolean checkWhetherRecordPresentOrNot(String id) throws SQLException
	{
		boolean isRecordFound = false;
		obj.result = obj.stmt.executeQuery("select " + fieldNames[0] + " from my_table where " + fieldNames[0] + " = " + id);
		if(obj.result.next())
		{
			return isRecordFound = true;
		}
		return isRecordFound;
	}
}