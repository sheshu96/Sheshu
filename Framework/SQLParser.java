// Program to write SQL Parser

class SQLParser
{
	String[] fieldNames;
	String[] fieldValues;
	String query;

	public SQLParser(String query)
	{
		this.query = query;
	}

	public String getTableName()
	{
		String[] queryData = query.split(" ");
		String tableName = "";
		if(queryData[0].toUpperCase().equals("SELECT"))
		{
			for(int index = 0; index < queryData.length; index++)
			{
				if(queryData[index].toUpperCase().equals("FROM"))
				{
					tableName = queryData[index + 1];
				}
			}	
		}
		else if(queryData[0].toUpperCase().equals("INSERT"))
		{
			tableName = queryData[2];
		}
		else if(queryData[0].toUpperCase().equals("UPDATE"))
		{
			tableName = queryData[1];
		}
		return tableName;
	}

	public String[] getFieldNames()
	{
		String tempQuery = query.replaceAll("[(,)\']", "");
		String[] queryData = tempQuery.split(" ");

		if(queryData[0].toUpperCase().equals("SELECT"))
		{
			fieldNames = new String[1];
			for(int index = 0; index < queryData.length; index++)
			{
				if(queryData[index].toUpperCase().equals("WHERE"))
				{
					fieldNames[0] = queryData[index + 1];
				}
			}
		}
		else if(queryData[0].toUpperCase().equals("INSERT"))
		{
			int counter = 0;
			for(int index = 3; index < queryData.length; index++)
			{
				if(queryData[index].toUpperCase().equals("VALUES"))
				{
					break;
				}
				else
				{
					counter++;
				}
			}
			fieldNames = new String[counter];
			for(int index = 0; index < fieldNames.length; index++)
			{
				fieldNames[index] = queryData[index + 3];
			}
		}
		else if(queryData[0].toUpperCase().equals("UPDATE"))
		{
			fieldNames = new String[2];
			fieldNames[0] = queryData[3];
			fieldNames[1] = queryData[7];
		}
		return fieldNames;
	}

	public String[] getFieldValues()
	{
		String tempQuery = query.replaceAll("[(,)\']", "");
		String[] queryData = tempQuery.split(" ");
		if(queryData[0].toUpperCase().equals("SELECT"))
		{
			fieldValues = new String[1];
			for(int index = 0; index < queryData.length; index++)
			{
				if(queryData[index].toUpperCase().equals("WHERE"))
				{
					fieldValues[0] = queryData[index + 3];
				}
			}
		}

		else if(queryData[0].toUpperCase().equals("INSERT"))
		{
			int counter = 0;
			for(int index = 3; index < queryData.length; index++)
			{
				if(queryData[index].toUpperCase().equals("VALUES"))
				{
					break;
				}
				else
				{
					counter++;
				}
			}
			fieldValues = new String[counter];
			for(int index = 0; index < fieldValues.length; index++)
			{
				fieldValues[index] = queryData[index + counter + 4];
			}
		}
		else if(queryData[0].toUpperCase().equals("UPDATE"))
		{
			fieldValues = new String[2];
			fieldValues[0] = queryData[5];
			fieldValues[1] = queryData[9];
		}
		return fieldValues;
	}
}
