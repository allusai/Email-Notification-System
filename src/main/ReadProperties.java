//This class is just intended to practice reading in the configuration file

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;

public class ReadProperties {

	public static void main(String[] args) throws IOException {
		String fileName = args[0];
		
		try{
			File file = new File(fileName);
			
			FileInputStream fileInput = new FileInputStream(file);
			Properties properties = new Properties();
			properties.load(fileInput);
			fileInput.close();
			
			//Returned null because file didn't have a certain property
			System.out.println(properties.getProperty("username"));
		}
		catch(FileNotFoundException f){
			System.out.println("Configuration file not found");
			return;
		}

	}

}
