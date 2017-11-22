import java.io.File;


public class DirectoryReader {
	
	public static void main(String[] args) 
	{
		File dir = new File("/Users/saikalyan/Desktop/Email/Email-Notification-System/Templates/");
		File[] directoryListing = dir.listFiles();
		
		//Check if it has been 2 minutes since the file was last modified
		if (directoryListing != null) {
		    for (File child : directoryListing) {
		      System.out.println(child.getName());
		      System.out.println(System.currentTimeMillis()-child.lastModified()>120000);
		    }
		  } else {
		    // Handle the case where dir is not really a directory.
		    System.out.println("That directory does not exist");
		    return;
		  }

	}

}
