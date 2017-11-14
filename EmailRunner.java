import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Properties;
import java.util.Scanner;

public class EmailRunner {
	
	public static Properties writePropertyFile()
	{
		Properties propFile = new Properties();
		
		//Configuration for file directory and possible options
		try
		{
			propFile.setProperty("directoryPath","/Users/saikalyan/Desktop/Email/"
					+ "Email-Notification-System/Templates/");
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

	public static void main(String[] args) throws FileNotFoundException
	{
		Properties p = EmailRunner.writePropertyFile();
		//System.out.println(p.getProperty("directoryPath"));
		
		
		//Scanner to take in the name of the file
		Scanner fileNameInput = new Scanner(System.in);
		String fileName = "";
	
		System.out.println("Enter the name of a file: ");
		fileName = fileNameInput.nextLine();
		
		//System.out.println("You entered: " + fileName);
		
		//Create a File object that we can parse
		String path = p.getProperty("directoryPath");
				
		File inputFile = new File(path + fileName);
		System.out.println(inputFile.exists());
		
		if(!inputFile.exists())
		{
			System.out.println("File does not exist");
			return;
		}
		
		//Check what the first two letters of the file are (the type of notification)
		String command;
		command = fileName.substring(0,2);
		System.out.println("Command was: " + command);
		
		/* See if the rest of the data provided in that file is what we expected 
		for that type of notification */
		TemplateValidation tempValid = new TemplateValidation(inputFile);
		boolean validData = false;
		
		if(command.equals(p.getProperty("template1")))
		{
			System.out.println(validData); //Before it is not valid
			System.out.println("Inside N1 validation");
			validData = tempValid.validate1();
			System.out.println(validData); //Now it is valid
		}
		
		else if(command.equals(p.getProperty("template2")))
		{
			System.out.println(validData);
			System.out.println("Inside N2 validation");
			validData = tempValid.validate2();
			System.out.println(validData);
		}
		
		else if(command.equals(p.getProperty("template3")))
		{
			System.out.println(validData);
			System.out.println("Inside N3 validation");
			validData = tempValid.validate3();
			System.out.println(validData);
		}
		
		else
		{
			System.out.println("Unrecognized command/file name");
		}

	}
}
