These files test each individual component of the larger "AutomatedEmailRunner.java." The files test the following features:
1) The program will wait for 2 minutes after the file enters the database in case data is still being written to it
2) The program will loop over every file in the directory until it is empty or a special file called "notification.quit"
   is added to the directory by the user.
3) The program will moved processed files into a separate directory where the data can be stored for a certain amount of
   time and then as a security measure to protect private data they can be deleted.
