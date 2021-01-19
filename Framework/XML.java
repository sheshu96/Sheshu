// XML
 
import java.io.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.*;
import java.util.Scanner;
import org.json.simple.JSONObject;  
import org.json.simple.JSONArray;

class XML implements iFramework
{
	File file;
	String[] fieldNames;
	String[] fieldValues;
	String query;
	DocumentBuilderFactory dbf;
	DocumentBuilder db;
	Document document;
	NodeList nList;
	Node nNode;
	Element eElement;
	String fieldValue;
	JSONArray array;
	JSONObject objJSON;

	public XML()
	{
		try
		{
			file = new File("data.xml");
			dbf = DocumentBuilderFactory.newInstance();
			db = dbf.newDocumentBuilder();
			document = db.parse(file);
            		document.getDocumentElement().normalize();
            		fieldNames = getFieldNames();
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	}

    public int addRecord(String query) throws Exception
    {
        SQLParser objSQLParser = new SQLParser(query);
        fieldValues = objSQLParser.getFieldValues();
        try
        {
            Element root = document.getDocumentElement();
            Element child = document.createElement("record");
            root.appendChild(child);
            Attr attr = document.createAttribute("Status");
            attr.setValue("A");
            child.setAttributeNode(attr);
            for(int index = 0; index < fieldNames.length; index++)
            {
                Element subChild = document.createElement(fieldNames[index]);
                subChild.appendChild(document.createTextNode(fieldValues[index]));
                child.appendChild(subChild);
            }
            writeDataIntoFile();
            return 1;
        }
        catch (Exception e)
        {
        	System.out.println(e.getMessage());
        	return 0;
        }
    }

    public JSONObject printRecords(String query) throws Exception
    {
        nList = document.getElementsByTagName("record");
        return convertDataIntoJSONFormat();
    }

    private JSONObject convertDataIntoJSONFormat() throws Exception
    {
    	objJSON = new JSONObject();
    	array = new JSONArray();
    	for (int index = 0; index < nList.getLength(); index++) 
        {
        	JSONObject record = new JSONObject();
        	getFieldValue(index);
        	String attribute = eElement.getAttribute("Status");
        	if(attribute.equals("A"))
        	{
	        	for(int fieldIndex= 0; fieldIndex < fieldNames.length; fieldIndex++)
	        	{
	        		record.put(fieldNames[fieldIndex], eElement.getElementsByTagName(fieldNames[fieldIndex]).item(0).getTextContent());
	        	}
	        	array.add(record);
        	}
        }
        objJSON.put("my_table", array);
        return objJSON;
    }

    public JSONObject searchRecord(String query) throws Exception
    {
    	SQLParser objSQLParser = new SQLParser(query);
    	fieldValues = objSQLParser.getFieldValues();
    	array = new JSONArray();
    	objJSON = new JSONObject();
    	nList = document.getElementsByTagName("record");
    	for(int index = 0; index < nList.getLength(); index++)
    	{
	    	JSONObject record = new JSONObject();
	    	getFieldValue(index);
    		if(fieldValue.equals(fieldValues[0]))
    		{
    			for(int fieldIndex = 0; fieldIndex < fieldNames.length; fieldIndex++)
    			{
    				record.put(fieldNames[fieldIndex], eElement.getElementsByTagName(fieldNames[fieldIndex]).item(0).getTextContent());
    			}
    			array.add(record);
    		}
    	}
    	objJSON.put("my_table", array);
    	return objJSON;
    }

    public int updateRecord(String query) throws Exception
    {
    	SQLParser objSQLParser = new SQLParser(query);
    	String[] tempFieldNames = objSQLParser.getFieldNames();
    	fieldValues = objSQLParser.getFieldValues();
    	nList = document.getElementsByTagName("record");
    	for(int index = 0; index < nList.getLength(); index++)
    	{
    		getFieldValue(index);
    		if(fieldValue.equals(fieldValues[1]))
    		{
    			NodeList nodes = nNode.getChildNodes();
    			for(int rowIndex = 0; rowIndex < nodes.getLength(); rowIndex++)
    			{
    				nNode = nodes.item(rowIndex);
    				if(nNode.getNodeName().equals(tempFieldNames[0]))
    				{
    					nNode.setTextContent(fieldValues[0]);
    					writeDataIntoFile();
    					return 1;
    				}
    			}
    		}
    	}
    	return 0;
    }

    public int deleteRecord(String query) throws Exception
    {
		SQLParser objSQLParser = new SQLParser(query);
		fieldValues = objSQLParser.getFieldValues();
    	nList = document.getElementsByTagName("record");
    	for(int index = 0; index < nList.getLength(); index ++)
    	{
    		getFieldValue(index);
    		String attribute = eElement.getAttribute("Status");
    		if(attribute.equals("A") && fieldValue.equals(fieldValues[1]))
    		{
    			eElement.setAttribute("Status", "D");
    			writeDataIntoFile();
	            return 1;
    		}
    	}
    	return 0;
    }

    private void getFieldValue(int index)
    {
    	nNode = nList.item(index);
		eElement = (Element) nNode;
		fieldValue = eElement.getElementsByTagName(fieldNames[0]).item(0).getTextContent();
    }

	public boolean checkWhetherRecordPresentOrNot(String id) throws Exception
	{
		boolean isRecordFound = false;
		nList = document.getElementsByTagName("record");
		for(int index = 0; index < nList.getLength(); index++)
		{
			getFieldValue(index);
			String attribute = eElement.getAttribute("Status");
			if(attribute.equals("A") && fieldValue.equals(id))
			{
				isRecordFound = true;
			}
		}
		return isRecordFound;
	}

    private void writeDataIntoFile() throws Exception
    {
    	TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource domSource = new DOMSource(document);
        StreamResult streamResult = new StreamResult(new FileWriter(file));
        transformer.transform(domSource, streamResult);
    }

    private String[] getDataFromFile(String fileName) throws Exception
    {
    	fileName = fileName + ".cfg";
    	String data = "";
    	try 
    	{
		File myObj = new File(fileName);
		Scanner scanner = new Scanner(myObj);  
		while (scanner.hasNextLine()) 
		{
			data = scanner.nextLine();
		}
		scanner.close();
	}
	catch (Exception e)
	{
		System.out.println("An error occurred.");
	}
	return data.split(", ");
	}

	public String[] getFieldNames() throws Exception
    	{
    	return getDataFromFile("FieldNames");
	}

	public String[] getData(String fileName) throws Exception
	{
		return getDataFromFile(fileName);
	}
}
