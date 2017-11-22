import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class SendEmail {

	private String emailText;
	
	public SendEmail(String s)
	{
		emailText = s;
	}
	
	public void sendTheMessage(Properties p, String subject, String recepient) throws MessagingException
	{
		final String username = p.getProperty("username"); 
		final String password = p.getProperty("password");
		
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
			message.setFrom(new InternetAddress(username));
			message.setRecipients(Message.RecipientType.TO, 
					InternetAddress.parse(recepient));
			message.setSubject(subject);
			message.setText(emailText);
			
			Transport.send(message);

			System.out.println("Done");
	}
	
}
