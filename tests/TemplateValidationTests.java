import java.io.FileNotFoundException;

/* This class runs all of the unit tests on the template
   validation class */

public class TemplateValidationTests 
{
	public static void main(String[] args) throws FileNotFoundException 
	{
		TemplateValidation tv = new TemplateValidation();
		boolean result = false;
		
		
		/* Check if we can validate emails */
		result = tv.validateEmail("allujaya@yahoo.com");
		System.out.println(result); //Should print true
		
		result = tv.validateEmail("");
		System.out.println(result); //Should print false
		
		result = tv.validateEmail("sai.2000k");
		System.out.println(result); //Should print false
		
		System.out.println("-------");
		
		/* Check if we can validate days of the week like Monday */
		result = tv.isValidDay("Monday");
		System.out.println(result); //Should print true
		
		result = tv.isValidDay("Sunday");
		System.out.println(result); //Should print true
		
		result = tv.isValidDay("Funday");
		System.out.println(result); //Should print false
		
		result = tv.isValidDay("");
		System.out.println(result); //Should print false
		
		System.out.println("-------");
		
		/* Check if we can validate dates like 08/31 or 11/21 */
		result = tv.isValidDate("12/25");
		System.out.println(result); //Should print true
		
		result = tv.isValidDate("08/21");
		System.out.println(result); //Should print true
		
		result = tv.isValidDate("08/32");
		System.out.println(result); //Should print false
		
		result = tv.isValidDate("02/29");
		System.out.println(result); //Should print true
		
		//This will crash the program without try/catch
		//But the bug was fixed so false is returned instead
		result = tv.isValidDate("8/21");
		System.out.println(result); //Should print false
		
		result = tv.isValidDate("");
		System.out.println(result); //Should print false
		
		System.out.println("-------");
		
		/* Check if we can validate times like 8:30AM */
		result = tv.isValidTime("8:30AM");
		System.out.println(result); //Should print true
		
		result = tv.isValidTime("12:30PM");
		System.out.println(result); //Should print true
		
		result = tv.isValidTime("");
		System.out.println(result); //Should print false
		
		result = tv.isValidTime("12");
		System.out.println(result); //Should print false
		
		//Pending bug, prints true
		result = tv.isValidTime("14:00AM");
		System.out.println(result); //Should print false
		
		result = tv.isValidTime("12:00");
		System.out.println(result); //Should print false
		
	}

}
