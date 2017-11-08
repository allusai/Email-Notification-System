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
	
	
	/** Checks if the data in thisFile is what we need to send Notification1. Later
	* on we can implement more comprehensive checking.
	*
	* @return boolean  Whether all the data is valid 
	*/
	boolean validate1()
	{

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
		
		//Scanner to parse the file
		Scanner fileScanner = new Scanner(inputFile);
		System.out.println("File not found");
		
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
