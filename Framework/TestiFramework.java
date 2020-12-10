// Program to get connected with MySQL or SQLite Database

class MainClass
{
	public static void main(String args[])
	{
		try
		{
			String className = args[0];
			System.out.println(className);
			CRUD objCRUD = new CRUD(className);
			objCRUD.printMenu();
		}
		catch(Exception e)
		{
			System.out.println("Invalid class name.");
		}
	}
}