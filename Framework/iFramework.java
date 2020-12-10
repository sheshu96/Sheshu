// Interface

import org.json.simple.JSONObject;  
import org.json.simple.JSONArray;

interface iFramework
{
	public int addRecord(String query) throws Exception;
	public JSONObject searchRecord(String query) throws Exception;
	public JSONObject printRecords(String query) throws Exception;
	public int updateRecord(String query) throws Exception;
	public int deleteRecord(String query) throws Exception;
	public boolean checkWhetherRecordPresentOrNot(String id) throws Exception;
	public String[] getFieldNames() throws Exception;
	public String[] getData(String fileName) throws Exception;
}