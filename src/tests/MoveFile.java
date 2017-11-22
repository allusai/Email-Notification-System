import java.io.File;

//Moves a file from one directory to another
public class MoveFile {

	public static void main(String[] args) {
		
		String directoryPath = "/Users/saikalyan/Desktop/Email/Email-Notification-System/Templates/";
		String processedFilesPath = "/Users/saikalyan/Desktop/Email/Email-Notification-System/ProcessedFiles/";
		String fileName = "description.txt";
		
		//absolute path rename file
        File file = new File(directoryPath + fileName);
        System.out.println(file.exists());
        
        File newFile = new File(processedFilesPath + fileName);
       
        if(file.renameTo(newFile)){
            System.out.println("File move success");;
        }else{
            System.out.println("File move failed");
        } 

	}

}
