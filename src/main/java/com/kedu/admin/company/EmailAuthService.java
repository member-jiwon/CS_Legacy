package com.kedu.admin.company;

import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

import javax.servlet.ServletException;

import org.springframework.stereotype.Service;

import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailAuthService {
	
	private static final String FROM_EMAIL = ""; 
	private static final String APP_PASSWORD = ""; 
	String authCode = generateAuthCode();

	// �씠硫붿씪 諛쒖넚
	protected String doPost(String id)
			throws ServletException, IOException {
		String toEmail = URLDecoder.decode(id, StandardCharsets.UTF_8);; // �궗�슜�옄媛� �엯�젰�븳 �씠硫붿씪

		try {
			sendEmail(toEmail, authCode);
			return authCode;
		} catch (MessagingException e) {
			e.printStackTrace();
			return null;
		}
	}

	// 6�옄由� �씤利앸쾲�샇 �깮�꽦
	private String generateAuthCode() {
		int code = (int) (Math.random() * 900000) + 100000;
		return String.valueOf(code);
	}

	// �씠硫붿씪 諛쒖넚 以�鍮�
	private void sendEmail(String toEmail, String authCode) throws MessagingException {
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.naver.com");
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.debug", "true");

		// �꽌踰� 怨꾩젙 �씤利�
		Session session = Session.getInstance(props, new jakarta.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(FROM_EMAIL, APP_PASSWORD);
			}
		});

		String htmlContent = String.format(
				"<p>�씤利� �럹�씠吏�濡� �떎�떆 �씠�룞�븯�뿬 �븘�옒�뿉 諛쏆쑝�떊 �씤利앸쾲�샇瑜� �엯�젰�빐 二쇱꽭�슂.</p>" +
						"<p>%s</p>" +
						"<p>* �씠硫붿씪 �닔�젙�떆 �떎�떆 �씤利앺빐�빞�빀�땲�떎. *</p>",
						authCode
				);

		// 硫붿씪 �옉�꽦
		MimeMessage message = new MimeMessage(session);
		message.setFrom(new InternetAddress(FROM_EMAIL));
		message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
		message.setSubject("CS 洹몃９�썾�뼱 �씠硫붿씪 �씤利� �솗�씤", "UTF-8");
		message.setContent(htmlContent, "text/html; charset=UTF-8");

		// 硫붿씪 諛쒖넚
		Transport.send(message);
	}
}
