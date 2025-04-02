package authorizatiom.DBProjcet;

import java.util.Properties;
import java.util.Random;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendMail {
	
	public static String OtpString;
	
	public  static void sendMail(String recipient) {
		System.out.println("Preparing to send Email....");
		
		Properties properties = new Properties();
		
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.port", "587");

		
		String username = "dbvisualizerverify@gmail.com";
		String password = "gtiw gtqi teos zrnr";
		
		Session session = Session.getInstance(properties, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});
		
		Message message = prepareMessage(session,username,recipient);
		try {
			Transport.send(message);
		}catch(Exception e) {
			System.out.println("error...."+e.getMessage());
		}
		System.out.println("Email send Successfully to "+recipient);
	}
	
	public static String randomOtp() {
	    Random random = new Random();
	    StringBuilder otp = new StringBuilder();
	    for (int i = 0; i < 6; i++) {
	        int randomInt = random.nextInt(10); 
	        otp.append(randomInt);
	    }
	    OtpString = otp.toString();
	    return OtpString;
	}
	public static void main(String[] args) {
//		sendMail("madhumithasrimathi@gmail.com");
	}
	
	


public static Message prepareMessage(Session session,String username,String recipient){
	Message message = new MimeMessage(session);
	try {
		message.setFrom(new InternetAddress(username));
		message.setRecipient(Message.RecipientType.TO,new InternetAddress(recipient));
		message.setSubject("DBVisualizer");
		message.setText("Hello " + recipient + ",\n\n" +
                 randomOtp() + " is your DBVisualizer verification code.\n" +
                 "This code is only valid for 5 minutes.\n\n" +
                 "Thank you,\n\t\t-Team DBVisualizer");		}catch(Exception e) {
		System.out.println("error here..."+e.getMessage());
	}
	return message;
	
}

public  static String getOtpString() {
	return OtpString;
}
}

