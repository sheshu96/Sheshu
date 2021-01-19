// MySQl connection in CS

namespace MySQLlib
{
	using System;
	using MySql.Data.MySqlClient;
	 
	public class MySQL
	{
		public static MySqlConnection ConnectToDB(String Host, String Database, String UserId, String Password)
	    {
	        String ConnString = "Server = " + Host + "; Database = " + Database + "; User Id = " + UserId + "; Password = " + Password;
            	MySqlConnection conn = new MySqlConnection(ConnString);
            	return conn;
	    }
	}
}
