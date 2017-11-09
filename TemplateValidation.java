import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.String;


public class TemplateValidation {
	private File thisFile;
	
	//Constructor
	public TemplateValidation(File f)
	{
		thisFile = f;
	}
	
	/** Regardless of the notification, the first line always contains the
	* email address of the recipient. So this data can be validated in a separate
	* chunk of code (document)
	*
	* @return boolean  Whether the first line is a valid email address
	*/
	boolean validateEmail()
	{
		Scanner fileScanner = new Scanner(thisFile);
		
		/* The first line should be a string with an @ and . */
		String firstLine = fileScanner.nextLine();
		
		if(firstLine.indexOf('@') == -1) //If '@' not found, invalid
			return false;
		if(firstLine.indexOf('.') == -1) //If '.' not found, invalid
			return false;
	}
	
	/** Checks if the data in thisFile is what we need to send Notification1. Later
	* on we can implement more comprehensive checking.
	*
	* @return boolean  Whether all the data is valid 
	*/
	boolean validate1()
	{
		Scanner fileScanner = new Scanner(thisFile);
		
		//If the email is not valid, return false
		if(!validateEmail())
			return false;
		
		//Note that we can jump over the first line inside this method (document)
		fileScanner.nextLine();
		
		/* The next line should be a string between 2 and 40 characters */
		String secondLine = fileScanner.nextLine();
		
		if(secondLine.length() < 2 || secondLine.length() > 40)
			return false;
			
		/* The next line should be a double between 0.0 and 100.0 */
		double grade = fileScanner.nextDouble();
		
		if(grade < 0.0 || grade > 100.0)
			return false;
			
		//If we did all the checks and there is no problem, the file is valid
		return true;
	}
	
	/** Checks if the data in thisFile is what we need to send Notification2.
	*
	* @return boolean  Whether all the data is valid 
	*/
	boolean validate2()
	{

	}
	
	/** Checks if the data in thisFile is what we need to send Notification3.
	*
	* @return boolean  Whether all the data is valid 
	*/
	boolean validate3()
	{

	}
	
	public static void main(String[] args) throws FileNotFoundException
	{
		
		//Scanner to take in the name of the file
		Scanner fileNameInput = new Scanner(System.in);
		String fileName = "";
		
		System.out.println("Enter the name of a file: ");
		fileName = fileNameInput.nextLine();
		
		//Create a File object that we can parse
		File inputFile = new File(fileName);
		
		//Check what the first two letters of the file are (the type of notification)
		String command;
		command = fileName.substring(0,2);
		
		
		/* See if the rest of the data provided in that file is what we expected 
		for that type of notification */
		boolean validData = false;
		
		if(command == 0)
		{
			validData = validate1(inputFile);
		}
			
		
		
		
	}

}
