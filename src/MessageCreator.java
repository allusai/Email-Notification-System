import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class MessageCreator {

	/** Creates a custom email message for Notification #1
	 * 
	 * @param File   the file containing the data we need to create our message
	 * @return String  the formatted message we can send to the user later
	 * @throws FileNotFoundException
	 */
	public String customMessage1(File f) throws FileNotFoundException
	{
		//We can use the Scanner to get the useful data from the file
		Scanner fileParser = new Scanner(f);

		//Skip the email for now
		String temp = fileParser.nextLine();
		
		String s = "Hello, "; //The message we will build now
		s += "\n" + "\n";  //Hello, 
		
		s += "This is an automated message from Bala Lahari ";
		s += "regarding a new grade you have received.";
		s += "\n" + "\n";
		
		s += "On the assignment " + fileParser.nextLine(); //On the assignment HW#2
		s += " you have received a grade of " + fileParser.nextDouble(); //you have received a grade of 90.0
		s += "\n" + "\n";
		
		s += "If you have any questions regarding this grade, please contact ";
		s += "your teacher directly.";
		s += "\n" + "\n";
		
		s += "Have a great day! \n";
		
		return s;
	}
	
	/** Creates a custom email message for Notification #2
	 * 
	 * @param File   the file containing the data we need to create our message
	 * @return String  the formatted message we can send to the user later
	 * @throws FileNotFoundException
	 */
	public String customMessage2(File f) throws FileNotFoundException
	{
		//We can use the Scanner to get the useful data from the file
		Scanner fileParser = new Scanner(f);

		//Skip the email for now
		String temp = fileParser.nextLine();
		
		String s = "Hello, "; //The message we will build now
		s += "\n" + "\n";  //Hello, 
		
		s += "This is an automated message from Bala Lahari.";
		s += "\n" + "\n";
		
		s += "There is a class coming up on " + fileParser.nextLine(); //Coming up on Friday
		s += ", " + fileParser.nextLine() + ". "; //Friday, 10/23.
		s += "\n" + "\n";
		
		s += "We hope to see you there, have a great day!";
		s += "\n" + "\n";
		
		return s;
	}
	
	/** Creates a custom email message for Notification #3
	 * 
	 * @param File   the file containing the data we need to create our message
	 * @return String  the formatted message we can send to the user later
	 * @throws FileNotFoundException
	 */
	public String customMessage3(File f) throws FileNotFoundException
	{
		//We can use the Scanner to get the useful data from the file
		Scanner fileParser = new Scanner(f);

		//Skip the email for now
		String temp = fileParser.nextLine();
		
		String s = "Hello, "; //The message we will build now
		s += "\n" + "\n";  //Hello, 
		
		s += "This is an automated message from Bala Lahari.";
		s += "\n" + "\n";
		
		s += "The assignment " + fileParser.nextLine() + " is due "; //Balalahari_Permission_Slip is due
		s += "on " + fileParser.nextLine() + " at " + fileParser.nextLine(); //on 09/21 at 8:00PM.
		s += "\n" + "\n";
		
		s += "If you have any questions, please be sure to contact your teacher ";
		s += "well before then.";
		s += "\n" + "\n";
		
		s += "Good luck!";
		
		return s;
	}
	
}
