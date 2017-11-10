import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


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
	 * @throws FileNotFoundException 
	*/
	boolean validateEmail() throws FileNotFoundException
	{
		Scanner fileScanner = new Scanner(thisFile);
		
		/* The first line should be a string with an @ and . */
		String firstLine = fileScanner.nextLine();
		
		if(firstLine.indexOf('@') == -1) //If '@' not found, invalid
			return false;
		if(firstLine.indexOf('.') == -1) //If '.' not found, invalid
			return false;
		
		//If all of that is good, return true
		return true;
	}
	
	/** Checks if the data in thisFile is what we need to send Notification1. Later
	* on we can implement more comprehensive checking.
	*
	* @return boolean  Whether all the data is valid 
	 * @throws FileNotFoundException 
	*/
	boolean validate1() throws FileNotFoundException
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
		return true;
	}
	
	/** Checks if the data in thisFile is what we need to send Notification3.
	*
	* @return boolean  Whether all the data is valid 
	*/
	boolean validate3()
	{
		return true;
	}
	
}
