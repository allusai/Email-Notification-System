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
	
	public TemplateValidation() 
	{
		thisFile = null;
	}

	/** Regardless of the notification, the first line always contains the
	* email address of the recipient. So this data can be validated in a separate
	* chunk of code (document)
	*
	* @return boolean  Whether the first line is a valid email address
	 * @throws FileNotFoundException 
	*/
	boolean validateEmail(String s) throws FileNotFoundException
	{
		
		/* The string should have an @ and . */
		
		if(s.indexOf('@') == -1) //If '@' not found, invalid
			return false;
		if(s.indexOf('.') == -1) //If '.' not found, invalid
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

		if(!fileScanner.hasNextLine())
			return false;
		
		//If the email is not valid, return false
		String firstLine = fileScanner.nextLine();
		
		if(!validateEmail(firstLine))
			return false;

		if(!fileScanner.hasNextLine())
			return false;
		
		/* The next line should be a string between 2 and 40 characters */
		String secondLine = fileScanner.nextLine();
		
		if(secondLine.length() < 2 || secondLine.length() > 40)
			return false;

		if(!fileScanner.hasNextDouble())
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
	 * @throws FileNotFoundException 
	*/
	boolean validate2() throws FileNotFoundException
	{
		Scanner fileScanner = new Scanner(thisFile);

		if(!fileScanner.hasNextLine())
			return false;
		
		//If the email is not valid, return false
		String firstLine = fileScanner.nextLine();
				
		if(!validateEmail(firstLine))
			return false;

		if(!fileScanner.hasNextLine())
			return false;
		
		/* The next line should be a day of the week */
		String secondLine = fileScanner.nextLine();
		boolean validDayOfWeek = isValidDay(secondLine); //See bottom of code
		
		//If not a valid day of the week, return false
		if(!validDayOfWeek)
			return false;

		if(!fileScanner.hasNextLine())
			return false;
			
		/* The next line should be a MM/DD */
		String thirdLine = fileScanner.nextLine();
		boolean validDate = isValidDate(thirdLine); //See below
		
		if(!validDate)
			return false;

		if(!fileScanner.hasNextLine())
			return false;
		
		/* The next line should be a valid time like 8:30PM */
		String fourthLine = fileScanner.nextLine();
		boolean validTime = isValidTime(fourthLine); //See helper method below
		
		if(!validTime)
			return false;
		
			
		//If we did all the checks and there is no problem, the file is valid
		return true;
	}
	
	/** Checks if the data in thisFile is what we need to send Notification3.
	*
	* @return boolean  Whether all the data is valid 
	 * @throws FileNotFoundException 
	*/
	boolean validate3() throws FileNotFoundException
	{
		Scanner fileScanner = new Scanner(thisFile);
		
		if(!fileScanner.hasNextLine())
			return false;
		
		//If the email is not valid, return false
		String firstLine = fileScanner.nextLine();
				
		if(!validateEmail(firstLine))
			return false;

		if(!fileScanner.hasNextLine())
			return false;
		
		/* The next line should be a string between 2 and 40 characters */
		String secondLine = fileScanner.nextLine();
		
		if(secondLine.length() < 2 || secondLine.length() > 40)
			return false;

		if(!fileScanner.hasNextLine())
			return false;
		
		/* The next line should be a MM/DD */
		String thirdLine = fileScanner.nextLine();
		boolean validDate = isValidDate(thirdLine); //See below
		
		if(!validDate)
			return false;

		if(!fileScanner.hasNextLine())
			return false;
		
		/* The next line should be a valid time like 8:30PM */
		String fourthLine = fileScanner.nextLine();
		boolean validTime = isValidTime(fourthLine); //See helper method below
		
		if(!validTime)
			return false;
		
			
		//If we did all the checks and there is no problem, the file is valid
		return true;
	}
	
	
	
	
	//I noticed that a lot of the time I was validating the
	//format of the same info in multiple notification files.
	//So I made these helper methods to be able to re-use that code.
	
	
	//Checks if the string passed in is a day from Monday-Sunday 
	boolean isValidDay(String s)
	{
		String[] validDays = {"Monday","Tuesday","Wednesday",
				"Thursday","Friday","Saturday","Sunday"};
		boolean isValidDay = false;

		//Loop through all possible days and see if second line is one of them
		for(int i = 0; i < validDays.length; i++)
		{
			//System.out.println(s.compareTo(validDays[i])); //Prints 1 for some reason!
			
			if(s.compareTo(validDays[i]) < 2)
				isValidDay = true;
		}

		//Return whether the string is a valid day
		return isValidDay;
	}
	
	//Checks if string passed in is a date in the format MM/DD
	boolean isValidDate(String s)
	{
		try{
		//MM
		int mm = Integer.parseInt(s.substring(0,2));
				
		//DD
		int dd = Integer.parseInt(s.substring(3,5));
				
		//If mm is not between 1 and 12, return false
		if(mm < 1 || mm > 12)
			return false;
		else if(dd < 1 || dd > 31)
			return false;
		else if(s.charAt(2) != '/')
			return false; }
		catch(Exception e)
		{
			return false;
		}
		
		return true;
	}
	
	//Checks if string passed is a valid time like 8:30PM
	boolean isValidTime(String s)
	{
		try{
		//Handles empty string case
		if(s.length() < 6)
			return false;
		
		//The last two characters need to be AM or PM
		//This led to a bug for the empty string case since
		//s.length()-2 would be an out of bounds index. So the above
		//code is a fix to handle strings that are too short to be
		//valid times.
		String lastTwoChars = s.substring(s.length()-2,s.length());
		
		if(!lastTwoChars.equals("AM") && !lastTwoChars.equals("PM"))
			return false;
		
		//There needs to be a colon at index 1 (8:30) or index 2 (12:30)
		//If the location of the ":" is not 1 and is not 2, then this isn't a valid time
		if(s.indexOf(':') != 1 && s.indexOf(':') != 2)
			return false;
		}
		catch(Exception e)
		{
			return false;
		}
		
		return true;
	}
	
}
