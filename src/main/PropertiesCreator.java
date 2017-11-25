 import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/* PropertiesCreator is a class of static helper methods that I made
   which takes in the name of the properties file and returns
   a Properties object that we can access fields with */

public class PropertiesCreator 
{
	public static Properties readPropertyFile(String fileName) throws IOException
	{
		File propertiesFile = new File(fileName);
		
		//If the configuration file could not be found, exit the program
		if(!propertiesFile.exists())
		{
			System.out.println("Configuration file not found");
			return null;
		}
			
		FileInputStream fileInput = new FileInputStream(propertiesFile);
		Properties p = new Properties();
		p.load(fileInput);
		fileInput.close();
		
		return p;
	}
	
	public static Properties writePropertyFile()
	{
		Properties propFile = new Properties();
		
		//Configuration for file directory and possible options
		try
		{
			propFile.setProperty("directoryPath","/Users/saikalyan/Desktop/Email/"
					+ "Email-Notification-System/Templates/");
			propFile.setProperty("username", "sai.2000k@gmail.com");
			propFile.setProperty("password", "sharonhigh2017");
			propFile.setProperty("template1","N1");
			propFile.setProperty("template2","N2");
			propFile.setProperty("template3","N3");
			//Can add more template commands her if the need ever comes up
			
			//Save the properties file
			//propFile.store(new FileOutputStream("SystemConfiguration.properties"),null);
		}
		catch(Exception e)
		{
			System.out.println("Could not write properties file");
		}
		
		return propFile;
	}
}
