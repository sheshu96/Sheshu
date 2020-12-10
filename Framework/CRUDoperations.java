// Framework program in Java

import java.util.Scanner;
import org.json.simple.JSONObject;  
import org.json.simple.JSONArray;

class CRUD
{
	Scanner scan = new Scanner(System.in);
	String result;
	String[] fieldNames;
	String[] promptMessages;
	String[] menu;
	String[] updatableFields;
	static String errorMessage = "Invalid, please enter a valid option.";
	iFramework obj;

	public CRUD(String className)
	{
		try
		{
			obj = (iFramework)Class.forName(className).newInstance();
			fieldNames = obj.getFieldNames();
			menu = obj.getData("Menu");
			promptMessages = obj.getData("Messages");
			updatableFields = obj.getData("UpdatableFields");
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	}

	public void printMenu() throws Exception
	{
		while(true)
		{
			for(int index = 0; index < menu.length; index++)
			{
				System.out.println(menu[index]);
			}
			System.out.print("Choose your option: ");
			int userOption = scan.nextInt();
			scan.nextLine();
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

	public void addRecord() throws Exception
	{
		String fieldValues = "";
		String tempFieldNames ="";
		for(String fieldName : fieldNames)
		{
			System.out.print(fieldName + ": ");
			String fieldValue = scan.nextLine();
			fieldValues += "'" + fieldValue + "', ";
			tempFieldNames += fieldName + ", ";
		}
		fieldValues += "'A'";
		tempFieldNames += "Status";
		int rowsAffected = obj.addRecord("insert into my_table (" + tempFieldNames + ") values (" + fieldValues + ")");
		if(rowsAffected == 1)
		{
			System.out.println(promptMessages[0]);
		}
	}

	public void searchRecord() throws Exception
	{
		String recordToBeSearched = getInput();
		boolean isRecordFound = obj.checkWhetherRecordPresentOrNot(recordToBeSearched);
		if(isRecordFound)
		{
			JSONObject objJSON = obj.searchRecord("select * from my_table where " + fieldNames[0] + " = " + recordToBeSearched);
			printRecord(objJSON);
		}
		else
		{
			printRecordNotFound();
		}
	}

	public void printRecords() throws Exception
	{
		JSONObject objJSON = obj.printRecords("select * from my_table where Status = 'A'");
		printRecord(objJSON);
	}

	public void printRecord(JSONObject objJSON)
	{
		JSONArray arrayObj = (JSONArray) objJSON.get("my_table");
		for(int index = 0; index < arrayObj.size(); index++)
		{
			System.out.println("---------------------");
			JSONObject obj = (JSONObject) arrayObj.get(index);
			for(String fieldName : fieldNames)
			{
				System.out.println(fieldName + ": " + obj.get(fieldName));
			}
		}
	}

	public void updateRecord() throws Exception
	{
		String recordToBeUpdated = getInput();
		boolean isRecordFound = obj.checkWhetherRecordPresentOrNot(recordToBeUpdated);
		if(isRecordFound)
		{
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
			int rowsAffected = obj.updateRecord("update my_table set " + fieldNames[updatableFieldPositions[updateOption - 1] - 1] + " = '" + updateValue + "' where " + fieldNames[0] + " = " + recordToBeUpdated);
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

	public void deleteRecord() throws Exception
	{
		String recordToBeDeleted = getInput();
		boolean isRecordFound = obj.checkWhetherRecordPresentOrNot(recordToBeDeleted);
		if(isRecordFound)
		{
			int rowsAffected = obj.deleteRecord("update my_table set Status = 'D' where " + fieldNames[0] + " = " + recordToBeDeleted);
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
}