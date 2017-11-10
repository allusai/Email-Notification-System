import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class EmailRunner {

	public static void main(String[] args) throws FileNotFoundException
	{
		
		//Scanner to take in the name of the file
		Scanner fileNameInput = new Scanner(System.in);
		String fileName = "";
		
		System.out.println("Enter the name of a file: ");
		fileName = fileNameInput.nextLine();
		
		System.out.println("You entered: " + fileName);
		
		//Create a File object that we can parse
		File inputFile = new File(fileName);
		
		//Check what the first two letters of the file are (the type of notification)
		String command;
		command = fileName.substring(0,2);
		System.out.println("Command was: " + command);
		
		/* See if the rest of the data provided in that file is what we expected 
		for that type of notification */
		TemplateValidation tempValid = new TemplateValidation(inputFile);
		boolean validData = false;
		
		if(command.equals("N1"))
		{
			System.out.println("Inside N1 validation");
			validData = tempValid.validate1();
		}

	}
}
