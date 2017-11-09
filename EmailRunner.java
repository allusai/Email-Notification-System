import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.String;

public class EmailRunner {

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
		TemplateValidation tempValid = new TemplateValidation(inputFile);
		boolean validData = false;
		
		if(command.equals("N1"))
		{
			validData = validate1();
		}
			
		
		
		
	}



}