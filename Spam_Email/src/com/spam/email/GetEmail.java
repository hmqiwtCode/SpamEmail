package com.spam.email;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;

@SuppressWarnings("serial")
@WebServlet("/getListEmail")
public class GetEmail extends HttpServlet {
	public static final String PATH_DIR = "imgs/";

	private String emailRd = null;
	private String passwordRd = null;
	private String subject = null;
	private String content = null;
	private String lstMailF[] = null;
	private String lstFile[] = null;

	private List<String> lstDataFromFormField = new ArrayList<String>();
	private List<String> lstFileName = new ArrayList<String>();
	private String savePathDir = null;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ServletFileUpload upload = new ServletFileUpload(new DiskFileItemFactory());
		String appPath = req.getServletContext().getRealPath("");
		savePathDir = appPath + File.separator + PATH_DIR;
		File save = new File(savePathDir);
		if (!save.exists()) {
			save.mkdir();
		}
		try {

			@SuppressWarnings("unchecked")
			List<FileItem> lstFile = upload.parseRequest(req);

			for (FileItem file : lstFile) {
				if (file.isFormField()) {
					lstDataFromFormField.add(file.getString());
				}
				if (file.getName() != null) {
					String name = file.getName().substring(file.getName().lastIndexOf("\\") + 1);
					File saveImg = new File(savePathDir + name);
					if (!saveImg.exists()) {
						file.write(saveImg);
						lstFileName.add(name);
					}

				}

			}
			chayMultiThread(req, resp);

		} catch (FileUploadException e) {

			e.printStackTrace();
		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	private void chayMultiThread(HttpServletRequest req, HttpServletResponse resp) throws InterruptedException {
		getInfoFromRequest(req, resp);
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		Session session = Session.getDefaultInstance(props, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(emailRd, passwordRd);
			}
		});
		for (int i = 0; i < lstMailF.length; i++) {
			MimeMessage msg = new MimeMessage(session);
			try {

				msg.setFrom(new InternetAddress(emailRd));
				msg.addRecipient(Message.RecipientType.TO, new InternetAddress(lstMailF[i]));
				msg.setSubject(subject);
				MimeMultipart emailContent = new MimeMultipart();
				MimeBodyPart textBodyPart = new MimeBodyPart();
				textBodyPart.setText(content);
				for (int j = 0; j < lstFile.length; j++) {
					MimeBodyPart pdfAttachment = new MimeBodyPart();
					pdfAttachment.attachFile(savePathDir + lstFile[j]);
					emailContent.addBodyPart(pdfAttachment);
				}

				emailContent.addBodyPart(textBodyPart);
				msg.setContent(emailContent);
				Transport.send(msg);
				System.out.println("success ");

			} catch (Exception e) {
				e.printStackTrace();
			}

			Thread.sleep(50);
		}
		return;;
	}

	public static String[] asStrings(Object... objArray) {
		String[] strArray = new String[objArray.length];
		for (int i = 0; i < objArray.length; i++)
			strArray[i] = String.valueOf(objArray[i]);
		return strArray;
	}

	private void getInfoFromRequest(HttpServletRequest req, HttpServletResponse resp) {
		String obj[] = lstDataFromFormField.toString().split(",");
		emailRd = obj[0].substring(1);
		passwordRd = obj[1];
		subject = obj[2];
		content = obj[3];
		lstFile = asStrings(lstFileName.toArray());
		lstMailF = obj[obj.length - 1].substring(0, obj[obj.length - 1].length() - 1).split("\r\n");
	}

}
