package Application;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class AuxiliarFilesFunctions {
	public static void saveJson(String json, String path)
	{
		FileWriter file = null;
		String directoryForResult = "./JsonResults/";
		try {
			File directory = new File(directoryForResult);
		    if (! directory.exists()){
		        directory.mkdir();
		    }
		}catch (Exception e) {
			LogControl.ErrorLog("Error creating the JsonResultsDirectory");
		}
		
		try {
			LogControl.InfoLog("Creating json \"" + path + "\"");
			file = new FileWriter(directoryForResult + path);
            file.write(json);
            LogControl.InfoLog("The json \"" + path + "\" has been created successfully.");       
        } catch (IOException e) {
            LogControl.ErrorLog(e.getMessage());
        } finally {
            try {
                file.flush();
                file.close();
            } catch (IOException e) {
            	LogControl.ErrorLog(e.getMessage());
            }
        }
	}
	
	public static ArrayList<String> getXmlFiles()
	{
		ArrayList<String> xmlFiles = new ArrayList<String>();
		
		File folder = new File("./");
	    File[] listOfFiles = folder.listFiles();
	    for(int i = 0; i < listOfFiles.length; i++){
	        String fileName = listOfFiles[i].getName();
	        if(fileName.endsWith(".xml")||fileName.endsWith(".XML")) {
	        	xmlFiles.add(fileName);
	        }
	    }
	    
	    return xmlFiles;
	}
	
	public static String stripExtension (String str) 
	{
        if (str == null) 
    	{
        	return null;
    	}
        
        int pos = str.lastIndexOf(".");

        if (pos == -1)
    	{
        	return str;
    	}

        return str.substring(0, pos);
    }
}
