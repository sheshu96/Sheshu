// MySQL

import java.sql.*;
import org.json.simple.JSONObject;  
import org.json.simple.JSONArray;

class MySQL implements iFramework
{
	Connection conn;
	Statement stmt;
	ResultSet result;
	String[] fieldNames;

	public MySQL()
	{
		String url = "jdbc:mysql://165.22.14.77/dbSheshu?user=Sheshu&password=Sheshu";
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(url);
			System.out.println("Connection successful.");
			stmt = conn.createStatement();
			fieldNames = getFieldNames();
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	}

	public int addRecord(String query) throws SQLException
	{
		int rowsAffected = stmt.executeUpdate(query);
		return rowsAffected;
	}

	private JSONObject convertDataIntoJSONFormat() throws SQLException
	{
		JSONArray array = new JSONArray();
		JSONObject objJSON = new JSONObject();
		while(result.next())
		{
			JSONObject record = new JSONObject();
			for(String fieldName : fieldNames)
			{
				record.put(fieldName, result.getString(fieldName));
			}
			array.add(record);
		}
		objJSON.put("my_table", array);
		return objJSON;
	}

	public JSONObject searchRecord(String query) throws SQLException
	{
		result = stmt.executeQuery(query);
		return convertDataIntoJSONFormat();
	}

	public JSONObject printRecords(String query) throws SQLException
	{
		result = stmt.executeQuery(query);
		return convertDataIntoJSONFormat();
	}

	public int updateRecord(String query) throws SQLException
	{
		int rowsAffected = stmt.executeUpdate(query);
		return rowsAffected;
	}

	public int deleteRecord(String query) throws SQLException
	{
		int rowsAffected = stmt.executeUpdate(query);
		return rowsAffected;
	}

	public String[] getFieldNames() throws SQLException
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

	public boolean checkWhetherRecordPresentOrNot(String id) throws SQLException
	{
		boolean isRecordFound = false;
		result = stmt.executeQuery("select " + fieldNames[0] + " from my_table where " + fieldNames[0] + " = " + id + " and Status = 'A'");
		if(result.next())
		{
			return isRecordFound = true;
		}
		return isRecordFound;
	}

	public String[] getData(String fileName) throws SQLException
	{
		result = stmt.executeQuery("select Content from Config where filename = '" + fileName + "'");
		String data = "";
		while(result.next())
		{
			data = result.getString("Content");
		}
		return data.split(", ");
	}
}