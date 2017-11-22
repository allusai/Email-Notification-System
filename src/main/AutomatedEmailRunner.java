import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;

import javax.mail.MessagingException;

public class AutomatedEmailRunner {

	public static void main(String[] args) throws MessagingException, IOException
	{
		/* Read the properties file for this system */
		
		//The configuration file name is passed in as a command-line argument
		Properties p = PropertiesCreator.readPropertyFile(args[0]);
		
		if(p == null)
		{
			System.out.println("Properties object could not be created");
			return;
		}

		//Go to the directory specified
		String dirPath = p.getProperty("directoryPath");
		File dir = new File(dirPath);
		File[] directoryListing = dir.listFiles();
		
		if(directoryListing == null)
		{
			System.out.println("Directory does not exist");
			return;
		}
		
		long startTime = System.currentTimeMillis();
		
	    for (File inputFile : directoryListing) 
	    {
	    	if(inputFile == null)
	    	{
	    		System.out.println("No more files left");
	    		return;
	    	}
	    	
	    	//Just a kill switch to stop an infinite loop
	    	if(System.currentTimeMillis()-startTime > 10000)
	    		return;
	    	
	    	String fileName = inputFile.getName();
	    	
	    	//When we want the program to stop processing the files
	    	//in the directory, we will drop a special file into the
	    	//directory called "notification.quit"
		    if(fileName.equals("notification.quit"))
		    {
		    	System.out.println("Exit file found.");
		    	return;
		    }
		    
		    //Only process the files which have not been modified in
		    //the past two minutes as they might be in the process of
		    //getting written
		    long timeSinceModified = System.currentTimeMillis()-inputFile.lastModified();
		    long minimumWaitTime = Long.parseLong(p.getProperty("waittime"));
		    
		    //If you have waited long enough, go ahead and process the file
		    if(timeSinceModified > minimumWaitTime)
		    {
		    
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
				String recepient = "cvmrao@yahoo.com";
				String subject = "Hello";
				sender.sendTheMessage(p, subject,recepient);
				
			}
			
			else if(command.equals(p.getProperty("template2")))
			{
				MessageCreator mc = new MessageCreator();
				String message =  mc.customMessage2(inputFile);
				
				SendEmail sender = new SendEmail(message);
				String recepient = "cvmrao@yahoo.com";
				String subject = "Hello";
				sender.sendTheMessage(p, subject,recepient);
				
			}
			
			else if(command.equals(p.getProperty("template3")))
			{
				MessageCreator mc = new MessageCreator();
				String message =  mc.customMessage3(inputFile);
				
				SendEmail sender = new SendEmail(message);
				String recepient = "cvmrao@yahoo.com";
				String subject = "Hello";
				sender.sendTheMessage(p, subject,recepient);	
			}
			
			//Now move that file into a different directory so it
			//is not processed again
			
			String directoryPath = p.getProperty("directoryPath");
			String processedFilesPath = p.getProperty("processedFilesDirectoryPath");
			
			//absolute path rename file
	        File file = new File(directoryPath + fileName);
	        File newFile = new File(processedFilesPath + fileName);
	       
	        if(file.renameTo(newFile)){
	            System.out.println("File move success");;
	        }else{
	            System.out.println("File move failed");
	        } 
		
		    }
	    }  
	}
}