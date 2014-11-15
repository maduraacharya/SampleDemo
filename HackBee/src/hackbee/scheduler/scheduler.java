package hackbee.scheduler;

import hackbee.controllers.Event;
import hackbee.dao.HackBeeDAOImpl;
import hackbee.dao.HackbeeDAO;
import hackbee.exceptions.DaoException;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.TimerTask;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Scheduler extends TimerTask {

	@Override
	public void run() {
		try {
			scheduleSuggestAndEmail();
		} catch (DaoException e) {
			e.printStackTrace();
		}

	}

	private void scheduleSuggestAndEmail() throws DaoException {

		HackbeeDAO dao = new HackBeeDAOImpl();
		Map<String, List<Event>> userList = dao.getSuggestions();
		Set<String> userSet = userList.keySet();

		for (String userId : userSet) {
			// Recipient's email ID needs to be mentioned.
			String to = userId;

			// Sender's email ID needs to be mentioned
			String from = "info@hackbee.com";

			// Assuming you are sending email from localhost
			String host = "localhost";

			// Get system properties
			Properties properties = System.getProperties();

			// Setup mail server
			properties.setProperty("mail.smtp.host", host);

			// Get the default Session object.
			Session session = Session.getDefaultInstance(properties);

			try {
				// Create a default MimeMessage object.
				MimeMessage message = new MimeMessage(session);

				// Set From: header field of the header.
				message.setFrom(new InternetAddress(from));

				// Set To: header field of the header.
				message.addRecipient(Message.RecipientType.TO,
						new InternetAddress(to));

				// Set Subject: header field
				message.setSubject("New Events coming up");

				// Now set the actual message
				for (Event event : userList.get(userId)) {
					message.setText(event.getEvent_name() + "\n" + event.getHosted_by() + "\n");
				}
				

				// Send message
				Transport.send(message);
				System.out.println("Sent message successfully....");
			} catch (MessagingException mex) {
				mex.printStackTrace();
			}
		}

	}

}
