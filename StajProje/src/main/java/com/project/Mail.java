package com.project;

import java.util.Properties;


 
public class Mail {

	
	
	 public static void gonder() throws Exception{
		 
		 
	        final String username = "your mail address";
	        final String password = "your mail password"; //kendi þifrenizle deneyiniz...
	 
	        Properties props = new Properties();
	        props.put("mail.smtp.auth", "true");
	        props.put("mail.smtp.starttls.enable", "true");
	        props.put("mail.smtp.host", "smtp.gmail.com");
	        props.put("mail.smtp.port", "587");
	 
	        Session session = Session.getInstance(props,
	                new javax.mail.Authenticator() {
	 
	                    protected PasswordAuthentication getPasswordAuthentication() {
	                        return new PasswordAuthentication(username, password);
	                    }
	                });
	        try {	        
	            Message message = new MimeMessage(session);
	            message.setFrom(new InternetAddress("your mail address"));
	            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("opposite mail address"));
	            message.setSubject("Bilgilendirme Maili :");
	             
	      
	            MimeBodyPart messageBodyPart = new MimeBodyPart();
	             
	            //Mesaj Ýçerik
	            messageBodyPart.setText("Merhabalar");
	            Multipart multipart = new MimeMultipart();
	            multipart.addBodyPart(messageBodyPart);
	   
	            
	              message.setContent(multipart);
	            

	            Transport.send(message);
	 
	            System.out.println("Mail Gönderildi");
	 
	        } catch (MessagingException e) {
	            throw new RuntimeException(e);
	        }
	 	
	 	
}
	 
}

