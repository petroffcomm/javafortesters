package com.example.fw;

import java.util.Properties;

import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Store;

public class MailHelper extends BaseHelper {
	
	private String mailserver;

	public MailHelper(ApplicationManager manager) {
		super(manager);
		this.mailserver = manager.getProperty("mailserver.host");
	}

	public MailMessage getNewMail(String user, String password) {
		Properties props = System.getProperties();
		Session session = Session.getDefaultInstance(props);
		
		Store store;
		try {
			store = session.getStore("pop3");
			store.connect(mailserver, user, password);
			Folder folder = store.getDefaultFolder().getFolder("INBOX");
			folder.open(Folder.READ_WRITE);
			if (folder.getMessageCount() != 1) {
				return null;
			}
			Message message = folder.getMessage(1);
			
			message.setFlag(Flags.Flag.DELETED, true);
			MailMessage msg = new MailMessage((String) message.getContent());
			folder.close(true);
			store.close();

			return msg;
		
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
