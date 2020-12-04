// Framework program in Java

import java.sql.*;
import java.util.Scanner;

class MainClass
{
	public static void main(String args[])
	{
		Framework obj = new Framework();
		try
		{
			obj.printMenu();
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}

	}
}

class Framework
{
	Scanner scan = new Scanner(System.in);
	Statement stmt;
	ResultSet result;
	Connection conn;
	String[] fieldNames;
	String[] promptMessages;
	static String errorMessage = "Invalid, please enter a valid option.";

	public Framework()
	{
		String url = "jdbc:mysql://165.22.14.77/dbSheshu?user=Sheshu&password=Sheshu";
		try
		{
			conn = DriverManager.getConnection(url);
			System.out.println("Connection successful.");
			stmt = conn.createStatement();
			storeFieldNames();
			String messages = getData("Messages");
			promptMessages = messages.split(", ");
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	}

	public void printMenu() throws SQLException
	{
		String menu = getData("Menu");
		while(true)
		{
			System.out.print(menu + "\nChoose your option: ");
			int userOption = scan.nextInt();
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
					String exitOption = scan.next();
					switch(exitOption.toUpperCase())
					{
						case "Y":
							stmt.close();
							conn.close();
							System.exit(0);
						case "N":
							continue;
						default:
							System.out.println(errorMessage);
					}
					break;
				default:
					System.out.println(errorMessage);
			}
		}
	}

	private String getData(String fileName) throws SQLException
	{
		result = stmt.executeQuery("select Content from Config where filename = '" + fileName + "'");
		String data = "";
		while(result.next())
		{
			data = result.getString("Content");
		}
		return data;
	}

	private void storeFieldNames() throws SQLException
	{
		result = stmt.executeQuery("Select * from my_table");
		ResultSetMetaData rsmd = result.getMetaData();
		fieldNames = new String[rsmd.getColumnCount() - 1];
		for(int index = 0; index < fieldNames.length; index++)
		{
			if(rsmd.getColumnName(index + 1).equals("Status") != true)
			{
				fieldNames[index] = rsmd.getColumnName(index + 1);
			}
		}
	}

	public void addRecord() throws SQLException
	{
		String fieldValues = "";
		for(int index = 0; index < fieldNames.length; index++)
		{
			System.out.print(fieldNames[index] + ": ");
			String fieldValue = scan.nextLine();
			fieldValues += "'" + fieldValue + "', ";
		}
		fieldValues += "'A'";
		int rowsAffected = stmt.executeUpdate("insert into my_table values(" + fieldValues + ")");
		if(rowsAffected == 1)
		{
			System.out.println(promptMessages[0]);
		}
		
	}

	public void searchRecord() throws SQLException
	{
		String recordToBeSearched = getInput();
		boolean isRecordFound = checkWhetherRecordPresentOrNot(recordToBeSearched);
		if(isRecordFound)
		{
			result = stmt.executeQuery("select * from my_table where Status = 'A' and " + fieldNames[0] + " = " + recordToBeSearched);
			printRecord(result);
		}
		else
		{
			printRecordNotFound();
		}
	}

	private void printRecord(ResultSet result) throws SQLException
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

	public void printRecords() throws SQLException
	{
		result = stmt.executeQuery("select * from my_table where Status = 'A'");
		printRecord(result);	
	}

	public void updateRecord() throws SQLException
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
			int updateOption = scan.nextInt();
			System.out.print("Enter new " + fieldNames[updatableFieldPositions[updateOption - 1] - 1] + ": ");
			String updateValue = scan.next();
			int rowsAffected = stmt.executeUpdate("update my_table set " + fieldNames[updatableFieldPositions[updateOption - 1] - 1] + " = '" + updateValue + "' where " + fieldNames[0] + " = " + recordToBeUpdated);
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

	public void deleteRecord() throws SQLException
	{
		String recordToBeDeleted = getInput();
		boolean isRecordFound = checkWhetherRecordPresentOrNot(recordToBeDeleted);
		if(isRecordFound)
		{
			int rowsAffected = stmt.executeUpdate("update my_table set Status = 'D' where " + fieldNames[0] + " = " + recordToBeDeleted);
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

	private void printRecordNotFound()
	{
		System.out.println(promptMessages[3]);
	}

	private String getInput()
	{
		System.out.print("Enter " + fieldNames[0] + ": ");
		String id = scan.next();
		return id;
	}

	private boolean checkWhetherRecordPresentOrNot(String id) throws SQLException
	{
		boolean isRecordFound = false;
		result = stmt.executeQuery("select " + fieldNames[0] + " from my_table where " + fieldNames[0] + " = " + id);
		if(result.next())
		{
			return isRecordFound = true;
		}
		return isRecordFound;
	}
}