// SQLite

import java.sql.*;

class SQLite implements iFramework
{
	Connection conn;
	Statement stmt;
	ResultSet result;

	public SQLite()
	{
		String url = "jdbc:sqlite:framework.db";
		try
		{
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection(url);
			System.out.println("Connection successful.");
			stmt = conn.createStatement();
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	}

	public int insertOrUpdateRecord(String query) throws SQLException
	{
		int rowsAffected = stmt.executeUpdate(query);
		return rowsAffected;
	}

	public ResultSet getRecord(String query) throws SQLException
	{
		result = stmt.executeQuery(query);
		return result;
	}


	public String[] storeFieldNames() throws SQLException
	{
		result = stmt.executeQuery("Select * from my_table");
		ResultSetMetaData rsmd = result.getMetaData();
		String[] fieldNames = new String[rsmd.getColumnCount() - 1];
		for(int index = 0; index < fieldNames.length; index++)
		{
			if(rsmd.getColumnName(index + 1).equals("Status") != true)
			{
				fieldNames[index] = rsmd.getColumnName(index + 1);
			}
		}
		return fieldNames;
	}
	
	public String getData(String fileName) throws SQLException
	{
		result = getRecord("select Content from Config where filename = '" + fileName + "'");
		String data = "";
		while(result.next())
		{
			data = result.getString("Content");
		}
		return data;
	}
}