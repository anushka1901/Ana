import java.io.File;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

class Baby_Authenticator extends Authenticator
{
	public PasswordAuthentication getPasswordAuthentication()
	{
		PasswordAuthentication pa = 
	    new PasswordAuthentication
	    ("kingbrooker21@gmail.com", "Robin@1999");

		return pa;
	}
}

public class SendEmail
{
	public static void main(String[] args) throws Exception
	{
		// provide configuration information about the mail service provider
		Properties p = new Properties();

		p.put("mail.smtp.host", "smtp.gmail.com");
		p.put("mail.smtp.port", "587");
		p.put("mail.smtp.starttls.enable", "true");
		p.put("mail.smtp.auth", "true");
		p.put("mail.smtp.ssl.trust", "smtp.gmail.com");
		p.put("mail.debug", "true");
		
		//create object of authenticator
		BabyOfAuthenticator baby = new BabyOfAuthenticator();

		// get object of session (in whose context we are going to send the message
		Session session = Session.getInstance(p, baby);

		// create object of mime-message (which means the actual message
		MimeMessage message = new MimeMessage(session);

		//provide the subject of message
		message.setSubject("Darna mana hai !!");

		// create an array of Internet address class to denote email address in network understandable format
		String[] emails = "amazing@gmail.com,superb@gmail.com"
				.split(",");

		InternetAddress[] address = new InternetAddress[emails.length];

		
		for (int i = 0; i < address.length; i++)
		{
			address[i] = new InternetAddress(emails[i]);
		}

		message.setRecipients(Message.RecipientType.TO, address);

		//create some body parts
		MimeBodyPart body1 = new MimeBodyPart();

		body1.setContent("<b><i>Hello World</i></b>", "text/html");
		
		MimeBodyPart body2 = new MimeBodyPart();

		body2.setContent("<b><i>This is a email sent through my code</i></b>", "text/html");

		// create object of Multipart to store the body parts as a single unit
		MimeMultipart part = new MimeMultipart();

		part.addBodyPart(body1);
		part.addBodyPart(body2);
		
		//store Multipart inside message
		message.setContent(part);

		// send this message to gmail server
		Transport.send(message);

		System.out.println("OK");
	}
}

