import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException; 
import javax.mail.PasswordAuthentication; 
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress; 
import javax.mail.internet.MimeMessage;

//Code to send email from www.mkyong.com/author/mkyong
//It works! 
public class SendMailTLS 
{
	public static void main(String[] args) throws AddressException, MessagingException 
	{
		final String username = "sai.2000k@gmail.com"; 
		final String password = "sharonhigh2017";
		
		Properties props = new Properties(); 
		props.put("mail.smtp.auth", "true"); 
		props.put("mail.smtp.starttls.enable", "true"); 
		props.put("mail.smtp.host", "smtp.gmail.com"); 
		props.put("mail.smtp.port", "587");
		
		Session session = Session.getInstance(props,
				new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication()
			{ 
				return new PasswordAuthentication(username, password);
			}
		});
		

			
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("sai.2000k@gmail.com"));
			message.setRecipients(Message.RecipientType.TO, 
					InternetAddress.parse("sai.2000k@gmail.com"));
			message.setSubject("Testing Subject");
			message.setText("Yo");
			
			Transport.send(message);

			System.out.println("Done");
			
		
	
		
	}
}
