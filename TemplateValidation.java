import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class TemplateValidation {
	
	public static void main(String[] args) throws FileNotFoundException
	{
		
		//Scanner to take in the name of the file
		Scanner fileNameInput = new Scanner(System.in);
		String fileName = "";
		
		System.out.println("Enter the name of a file");
		fileName = fileNameInput.nextLine();
		
		//Create a File object that we can parse
		File inputFile = new File(fileName);
		
		//Scanner to parse the file
		Scanner fileScanner = new Scanner(inputFile);
		System.out.println("File not found");
		
		//Check what the first number in the file is
		int command;
		command = fileScanner.nextInt();
		
		boolean validData = false;
		
		if(command == 0)
		{
			validData = validate1(inputFile);
		}
			
		
		
		
	}

}
