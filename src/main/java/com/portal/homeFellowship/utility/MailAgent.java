package com.portal.homeFellowship.utility;

import com.portal.homeFellowship.filter.GlobalRestException;
import com.portal.homeFellowship.model.Response;
import org.slf4j.Logger;

import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.core.env.Environment;

import org.springframework.stereotype.Component;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;

import javax.mail.internet.InternetAddress;

import javax.mail.internet.MimeBodyPart;

import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import java.io.File;

import java.io.IOException;

import java.util.Date;

import java.util.Properties;

 

/**

*

*/

@Component

public class MailAgent {

 

    private final static Logger LOGGER = LoggerFactory.getLogger(MailAgent.class);

 

    @Autowired

    Environment environment;

 

    public Response generateAndSendEmail(String Body, String subject, String recipient, String Attachments) throws MessagingException {

 

        // Gmail username

       // final String username = "aanuoluwa.otitoola@gmail.com";

                final String username = "abtcoolb7@gmail.com";

 

        // Gmail password

        final String password = "08101899045";

 

        LOGGER.info("********* password ***********" + password);

 

 

 

        // Step 1

        //System.out.println("\n 1st ===> Setting up Mail Server Properties...");

        Properties mailServerProperties = System.getProperties();

        mailServerProperties.put("mail.smtp.host", "smtp.gmail.com");

        mailServerProperties.put("mail.smtp.port", "587");

        mailServerProperties.put("mail.smtp.auth", "true");

        mailServerProperties.put("mail.smtp.starttls.enable", "true");

 

        //System.out.println("Mail Server properties setup successful.");

 

        // Step 2

        //System.out.println("\n\n 2nd ===> getting Mail Session...");
        
        Multipart mp = new MimeMultipart();
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


 

        Session getMailSession = Session.getDefaultInstance(mailServerProperties, new Authenticator() {

            protected PasswordAuthentication getPasswordAuthentication() {

                return new PasswordAuthentication(username, password);

            }

        });

 

        Message generateMailMessage = new MimeMessage(getMailSession);

        generateMailMessage.setFrom(new InternetAddress("BSSA"));

        generateMailMessage.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));

       // generateMailMessage.addRecipient(Message.RecipientType.CC, new InternetAddress(CcRecipient));

        generateMailMessage.setSubject(subject);

        generateMailMessage.setContent(Body, "text/html");

 

 

        MimeBodyPart mimeBodyPart = new MimeBodyPart();

 

        mimeBodyPart.setContent(Body, "text/html");

        File file = new File("/Users/oluwasakinoluwatobi/Documents/CITH/CITHMessage.html");

 

        try {

            mimeBodyPart.saveFile(file);

        } catch (IOException e) {

            e.printStackTrace();

        }

 

//        String emailBody = "Test email (JavaMail API example). " + "<br><br> Regards, <br>A. Otitoola";

//

//        // Create the Multipart to be added the parts to

//        Multipart multipart = new MimeMultipart();

//

//        // Create and fill the first message part

//        try {

//            MimeBodyPart mimeBodyPart = new MimeBodyPart();

//            mimeBodyPart.setContent(Body, "");

//            File file = new File("D:\\Logs\\email.html");

//            try {

//                mimeBodyPart.saveFile(file);

//            } catch (IOException e) {

//                e.printStackTrace();

//            }

//            // Attach the part to the multipart;

//            multipart.addBodyPart(mimeBodyPart);

//

//            // Add the Multipart to the message

//            generateMailMessage.setContent(multipart);

//            //        generateMailMessage.setContent(emailBody, "text/html");

//            System.out.println("Mail Session has been created successfully...");

 

            // Set the Date: header

            generateMailMessage.setSentDate(new Date());

 

            // Step3

            System.out.println("\n\n Get Session and Send mail");

 

        try {

            Transport.send(generateMailMessage);

            System.out.println("Mail sent successfully");

 

            Response response = new Response();

            response.setResponseCode("00");

            response.setResponseMessage("Mail sent successfully.");

            return response;

 

        } catch (MessagingException e){

            e.printStackTrace();

            throw new GlobalRestException("99", e.toString());

        }

    }

}

