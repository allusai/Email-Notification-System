import java.util.Properties;


public class PropertiesCreator 
{
	public Properties writePropertyFile()
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
