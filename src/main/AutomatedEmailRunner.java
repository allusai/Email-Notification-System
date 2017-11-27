import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;

import javax.mail.MessagingException;

public class AutomatedEmailRunner {

	public static void main(String[] args) throws MessagingException, IOException, FileNotFoundException
	{
		/* Read the properties file for this system */
		if(args.length != 1)
		{
			System.out.println("Enter a configuration file");
			return;
		}
		
		//The configuration file name is passed in as a command-line argument
		Properties p = PropertiesCreator.readPropertyFile(args[0]);
		
		if(p == null)
		{
			System.out.println("Properties object could not be created");
			return;
		}
		
		/* We have the files we want to send in a directory. Once we send these files though, the
		   program should not stop running since more files will be added to the directory (the directory
		   is a queue to which files will keep be added to). To accomplish this, I used a while(true)
		   infinite loop which will run until we tell the program to stop by adding a special
		   file to the directory called "notification.quit." */
		
		while(true) {
			
			//Go to the directory specified
			String dirPath = p.getProperty("directoryPath");
			File dir = new File(dirPath);
			File[] directoryListing = dir.listFiles();
			
			if(directoryListing == null)
			{
				System.out.println("Directory does not exist");
				return;
			}
			
			//System.out.println("Top of the while loop");
			
			for (File inputFile : directoryListing) 
			{
				String fileName = inputFile.getName();
				System.out.println(fileName);
				
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
						validData = tempValid.validate1();
					}
			
					else if(command.equals(p.getProperty("template2")))
					{
						validData = tempValid.validate2();
					}
			
					else if(command.equals(p.getProperty("template3")))
					{
						validData = tempValid.validate3();
					}
			
					/* If file command is not recognized, don't do anything
					else
					{
						System.out.println("Unrecognized command/file name");
					}*/
			
					//Now that the data has been verified, we can send the message
					if(!validData)
					{
						System.out.println("Data is invalid.");
					}
					
					//See the helper method below main
					sendMessage(p,inputFile,command);
					
					//Now move that file into a different directory so it
					//is not processed again
			
					String directoryPath = p.getProperty("directoryPath");
					String processedFilesPath = p.getProperty("processedFilesDirectoryPath");
			
					//absolute path rename file
					File newFile = new File(processedFilesPath + fileName);
					
					if(inputFile.renameTo(newFile)){
						System.out.println("File move success");
						inputFile.delete();
					}
					else{
						System.out.println("File move failed");
					} 
					
					System.out.println();
				}
			} 
			
			/* We don't want to continuously process the files because that will waste
			 * a lot of computing power. We only scan through the directory and check for
			 * new files every 5 seconds. To build this functionality, we have to use the
			 * Thread class which can put a thread to sleep for a given amount of time in milliseconds. */
			long sleepTime = Long.parseLong(p.getProperty("sleeptime"));
			
			try {
				Thread.sleep(sleepTime);
			} catch (InterruptedException e) {
				System.out.println("Sleep command did not work");
			}
			
			
			
		} //While loop brace
	}
	
	
	
	/** This helper method sends the actual email after all of the data has
	 * been verified inside of main.
	 * 
	 * @param p  The Properties file containing login information for your Gmail account (username, password)
	 * @param inputFile  The file that needs to be sent
	 * @param command  The indicator of what type of notification you are sending
	 * @throws FileNotFoundException
	 * @throws MessagingException
	 */
	private static void sendMessage(Properties p, File inputFile, String command) throws FileNotFoundException, MessagingException
	{
		/* Once the data has been validated, figure out which notification we want to send and build the email */
		
		if(command.equals(p.getProperty("template1")))
		{
			//The MessageCreator builds the body of the email (the custom notification text)
			MessageCreator mc = new MessageCreator();
			String message =  mc.customMessage1(inputFile);
			
			//Initialize a SendEmail object which uses HTTP requests to actually build the
			//email we want to send as a draft.
			SendEmail sender = new SendEmail(message);
			String subject = "Grade Notification";
			
			//We use a Scanner object to read in the recipient's email from the file
			Scanner fileScanner = new Scanner(inputFile);
			String recepient = fileScanner.nextLine();
			
			//This is the method from the JavaMail API that actually sends the email to the student
			sender.sendTheMessage(p, subject,recepient);
		}

		else if(command.equals(p.getProperty("template2")))
		{
			MessageCreator mc = new MessageCreator();
			String message =  mc.customMessage2(inputFile);
				
			SendEmail sender = new SendEmail(message);
			String subject = "Class Reminder";
			
			Scanner fileScanner = new Scanner(inputFile);
			String recepient = fileScanner.nextLine();
			
			sender.sendTheMessage(p, subject,recepient);	
		}

		else if(command.equals(p.getProperty("template3")))
		{
			MessageCreator mc = new MessageCreator();
			String message =  mc.customMessage3(inputFile);
			
			SendEmail sender = new SendEmail(message);
			String subject = "Missing Assignment";
			
			Scanner fileScanner = new Scanner(inputFile);
			String recepient = fileScanner.nextLine();
			
			sender.sendTheMessage(p, subject,recepient);
		}
	}
	
}