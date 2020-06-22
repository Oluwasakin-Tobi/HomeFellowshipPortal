package com.portal.homeFellowship.utility;


import java.io.File;
import java.util.Date;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.annotation.PostConstruct;
import javax.mail.Session;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * 
 */
@Component
public class Mailer {

	final static Logger LOGGER = LoggerFactory.getLogger(Mailer.class);
	@Autowired
	private Environment environment;

	private Properties props;
	private Session session;
	private MimeMessage msg;

	@PostConstruct
	private void init() {
		msg = null;
//		props = System.getProperties();
//		props.put("mail.smtp.host", environment.getRequiredProperty("mail.smtp.host"));
//		props.put("mail.smtp.port", environment.getRequiredProperty("mail.smtp.port"));
		//session = Session.getDefaultInstance(props, null);
		
		// For Yahoo.. to fix email exchange server restriction.
		
		 Properties mailServerProperties = System.getProperties();

	        mailServerProperties.put("mail.smtp.host", "smtp.gmail.com");

	        mailServerProperties.put("mail.smtp.port", "587");

	        mailServerProperties.put("mail.smtp.auth", "true");

	        mailServerProperties.put("mail.smtp.starttls.enable", "true");

	 

	        System.out.println("Mail Server properties setup successful.");
		
//        props.put("mail.smtp.auth", "true");
        session = Session.getInstance(mailServerProperties, 
        		new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("abtcoolb7@gmail.com", "08101899045");
            }
        });
	}

	public int Send(String Sender, String Recipient, String CcRecipient, String BccRecipient, String Subject,
			String Body, String ErrorMessage[], String Attachments) {
		// Error status;
		int ErrorStatus = 0;
		// Create some properties and get the default Session;
		msg = new MimeMessage(session);

		try {
			// extracts the senders and adds them to the message.
			// Sender is a comma-separated list of e-mail addresses as per
			// RFC822.
			{
				InternetAddress[] TheAddresses = InternetAddress.parse(Sender);
				msg.addFrom(TheAddresses);
			}

			// Extract the recipients and assign them to the message.
			// Recipient is a comma-separated list of e-mail addresses as per
			// RFC822.
			{
				InternetAddress[] TheAddresses = InternetAddress.parse(Recipient);
				msg.addRecipients(Message.RecipientType.TO, TheAddresses);
			}

			// Extract the Cc-recipients and assign them to the message;
			// CcRecipient is a comma-separated list of e-mail addresses as per
			// RFC822
			if (null != CcRecipient) {
				InternetAddress[] TheAddresses = InternetAddress.parse(CcRecipient);
				msg.addRecipients(Message.RecipientType.CC, TheAddresses);
			}

			// Extract the Bcc-recipients and assign them to the message;
			// BccRecipient is a comma-separated list of e-mail addresses as per
			// RFC822
			if (null != BccRecipient) {
				InternetAddress[] TheAddresses = InternetAddress.parse(BccRecipient);
				msg.addRecipients(Message.RecipientType.BCC, TheAddresses);
			}

			// Subject field
			msg.setSubject(Subject);

			// Create the Multipart to be added the parts to
			Multipart mp = new MimeMultipart();

			// Create and fill the first message part
			{
				MimeBodyPart mbp = new MimeBodyPart();
				mbp.setContent(Body, "text/html");

				// Attach the part to the multipart;
				mp.addBodyPart(mbp);
				
//				File file = new File("D:\\Logs\\email.html");
//				mbp.saveFile(file);
			}

			// Attach the files to the message
			if (null != Attachments) {
				int StartIndex = 0, PosIndex = 0;
				while (-1 != (PosIndex = Attachments.indexOf("///", StartIndex))) {
					// Create and fill other message parts;
					MimeBodyPart mbp = new MimeBodyPart();
					FileDataSource fds = new FileDataSource(Attachments.substring(StartIndex, PosIndex));
					mbp.setDataHandler(new DataHandler(fds));
					mbp.setFileName(fds.getName());
					mp.addBodyPart(mbp);
					PosIndex += 3;
					StartIndex = PosIndex;
				}
				// Last, or only, attachment file;
				if (StartIndex < Attachments.length()) {
					MimeBodyPart mbp = new MimeBodyPart();
					FileDataSource fds = new FileDataSource(Attachments.substring(StartIndex));
					mbp.setDataHandler(new DataHandler(fds));
					mbp.setFileName(fds.getName());
					mp.addBodyPart(mbp);
					
				}
			}

			
			// Add the Multipart to the message
			msg.setContent(mp);
			// msg.setContent(Body,"text/html");

			// Set the Date: header
			msg.setSentDate(new Date());

			// Send the message;

			Transport.send(msg);
			LOGGER.info("*******EMAIL SENT TO..." + Recipient + " ********");
			LOGGER.info("*******EMAIL COPIED TO >>>>" + CcRecipient + " ********");
			LOGGER.info("*******EMAIL BLIND COPIED TO====" + BccRecipient + " ********");

		} catch (Exception MsgException) { 
			ErrorStatus = -1; 
			LOGGER.error("********Oops!. Something went wrong " + MsgException.getMessage());
			MsgException.printStackTrace();
		}
		return ErrorStatus;
	} // End Send Class
}

