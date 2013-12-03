package com.ece4894.controllerView;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.ece4894.model.Resident;
import com.ece4894.model.VisitorRequest;

/**
 * @author Sahil Gupta
 * 
 *         This class represents the request page where the user enters the
 *         request.
 */

public class RequestPage extends JPanel {

	private static final long serialVersionUID = 1L;
	final static Dimension screenSize = Toolkit.getDefaultToolkit()
			.getScreenSize();

	JLabel requestPageLabel, VisitorFirstNameLabel, VisitorLastNameLabel,
			VisitorGTIDLabel;
	JTextField VisitorFirstNameTextField, VisitorLastNameTextField,
			VisitorGTIDTextField;
	JButton submitRequest;

	VisitorRequest request;
	Resident currentUser;

	public RequestPage(Resident currentUser) {
		this.setLayout(new FlowLayout());
		setBounds(screenSize.width / 2 - 200, screenSize.height / 2 - 100, 320,
				270);
		this.currentUser = currentUser;
		request = new VisitorRequest();

		this.setBackground(Color.green);

		requestPageLabel = new JLabel("Request Page");
		requestPageLabel.setFont(new Font("Helvetica", Font.BOLD, 30));

		VisitorFirstNameLabel = new JLabel("Visitor's First Name");
		VisitorFirstNameTextField = new JTextField(20);

		VisitorLastNameLabel = new JLabel("Visitor's Last Name");
		VisitorLastNameTextField = new JTextField(20);

		VisitorGTIDLabel = new JLabel("Visitor's GTID");
		VisitorGTIDTextField = new JTextField(20);

		submitRequest = new JButton("Submit Request");
		submitRequest.addActionListener(new submitRequestButtonListener());

		this.add(requestPageLabel);
		this.add(VisitorFirstNameLabel);
		this.add(VisitorFirstNameTextField);
		this.add(VisitorLastNameLabel);
		this.add(VisitorLastNameTextField);
		this.add(VisitorGTIDLabel);
		this.add(VisitorGTIDTextField);
		this.add(submitRequest);
	}

	private class submitRequestButtonListener implements ActionListener {

		public void actionPerformed(ActionEvent event) {
			request.setFirstName(VisitorFirstNameTextField.getText());
			request.setLastName(VisitorLastNameTextField.getText());

			Long GTIDNumber = 0L;
			try {
				GTIDNumber = Long.parseLong(VisitorGTIDTextField.getText());
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(new JFrame(),
						"Enter GTID in a valid format", "Inane error",
						JOptionPane.ERROR_MESSAGE);
			}

			request.setGTID(GTIDNumber);
			request.setTag(com.ece4894.services.RequestDao.getTagUsingGTID(GTIDNumber));
			request.setGuestOfGTID(currentUser.getGTID());
			request.setDateTimeIn(new BigDecimal(new Long(System
					.currentTimeMillis()).toString()));
			request.setDateTimeOut(new BigDecimal(new Long(System
					.currentTimeMillis() + 600000).toString()));
			// add 600000 milliseconds to account for 10 minute expiration time
			request.setRequestNumber(com.ece4894.services.RequestDao
					.countRequest() + 1);
			if (request.getFirstName().isEmpty()
					|| request.getLastName().isEmpty()
					|| request.getGTID() == null) {
				JOptionPane.showMessageDialog(new JFrame(),
						"Information not entered", "Inane error",
						JOptionPane.ERROR_MESSAGE);
			} else {
				String ResultString;
				if (com.ece4894.services.RequestDao.isRequestValid(request)) {
					if (com.ece4894.services.RequestDao.getBuilding(
							request.getGTID()).equals(
							com.ece4894.services.RequestDao.getBuilding(request
									.getGuestOfGTID()))) {
						ResultString = "You live in the same building! The visitor should have access";
					} else {
						boolean result = com.ece4894.services.RequestDao
								.putRequest(request);
						if (result) {
							ResultString = "Success!";
						} else {
							ResultString = "Failure!";
						}
					}
				}else{
					ResultString = "Invalid request data entered! Visitor is not a student. Please try again.";
				}
				JFrame mainFrame = MainFrame.getMain();
				ResultPage panel = new ResultPage(ResultString, currentUser);
				mainFrame.setContentPane(panel);
				mainFrame.setBounds(mainFrame.getContentPane()
						.getBounds());
				mainFrame.setVisible(true);
				mainFrame.repaint();
			}
		}

	}

}
