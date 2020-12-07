// Interface

import java.sql.*;

interface iFramework
{
	public int insertOrUpdateRecord(String query) throws SQLException;
	public ResultSet getRecord(String query) throws SQLException;
	public String[] storeFieldNames() throws SQLException;
	public String getData(String fileName) throws SQLException;
}