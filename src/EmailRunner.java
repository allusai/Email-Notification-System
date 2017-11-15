import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Properties;
import java.util.Scanner;

import javax.mail.MessagingException;

public class EmailRunner {

	public static void main(String[] args) throws FileNotFoundException, MessagingException
	{
		/* Write the properties file for this system */
		PropertiesCreator propCreator = new PropertiesCreator();
		Properties p = propCreator.writePropertyFile();
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
			//System.out.println(validData); //Before it is not valid
			//System.out.println("Inside N1 validation");
			validData = tempValid.validate1();
			//System.out.println(validData); //Now it is valid
		}
		
		else if(command.equals(p.getProperty("template2")))
		{
			validData = tempValid.validate2();
		}
		
		else if(command.equals(p.getProperty("template3")))
		{
			validData = tempValid.validate3();
		}
		
		else
		{
			System.out.println("Unrecognized command/file name");
		}
		
		//Now that the data has been verified, we can send the message
		if(!validData)
		{
			System.out.println("Data is invalid.");
			return;
		}
		
		if(command.equals(p.getProperty("template1")))
		{
			MessageCreator mc = new MessageCreator();
			String message =  mc.customMessage1(inputFile);
			
			SendEmail sender = new SendEmail(message);
			String recepient = "sai.2000k@gmail.com";
			String subject = "Hello";
			sender.sendTheMessage(p, subject,recepient);
			
		}
		
		else if(command.equals(p.getProperty("template2")))
		{
			MessageCreator mc = new MessageCreator();
			String message =  mc.customMessage2(inputFile);
			
			SendEmail sender = new SendEmail(message);
			String recepient = "sai.2000k@gmail.com";
			String subject = "Hello";
			sender.sendTheMessage(p, subject,recepient);
			
		}
		
		else if(command.equals(p.getProperty("template3")))
		{
			MessageCreator mc = new MessageCreator();
			String message =  mc.customMessage3(inputFile);
			
			SendEmail sender = new SendEmail(message);
			String recepient = "sai.2000k@gmail.com";
			String subject = "Hello";
			sender.sendTheMessage(p, subject,recepient);	
		}

	}
}