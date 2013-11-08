package com.ece4894.controllerView;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.ece4894.model.Resident;

/**
 * @author Sahil Gupta
 * 
 *         This class is the login screen where the user enters the system.
 */

public class LoginPage extends JPanel {

	private static final long serialVersionUID = 1L;
	final static Dimension screenSize = Toolkit.getDefaultToolkit()
			.getScreenSize();

	JLabel loginPageLabel, userNameLabel, passwordLabel, FirstNameLabel,
			LastNameLabel, GTIDLabel, ResidenceHallLabel;
	JTextField userNameTextField, passwordTextField, FirstNameTextField,
			LastNameTextField, GTIDTextField, ResidenceHallTextField;
	JButton login;

	public Resident CurrentUser;

	public LoginPage() {
		this.setLayout(new FlowLayout());
		setBounds(screenSize.width / 2 - 150, screenSize.height / 2 - 150, 240,
				400);

		CurrentUser = new Resident();
		this.setBackground(Color.green);

		loginPageLabel = new JLabel("Login Page");
		loginPageLabel.setFont(new Font("Helvetica", Font.BOLD, 30));

		userNameLabel = new JLabel("Username");
		userNameTextField = new JTextField(20);

		passwordLabel = new JLabel("Password");
		passwordTextField = new JTextField(20);

		FirstNameLabel = new JLabel("First Name");
		FirstNameTextField = new JTextField(20);

		LastNameLabel = new JLabel("Last Name");
		LastNameTextField = new JTextField(20);

		GTIDLabel = new JLabel("GT ID");
		GTIDTextField = new JTextField(20);

		ResidenceHallLabel = new JLabel("Residence Hall");
		ResidenceHallTextField = new JTextField(20);

		login = new JButton("Login");
		login.addActionListener(new LoginButtonListener());

		this.add(loginPageLabel);
		this.add(userNameLabel);
		this.add(userNameTextField);
		this.add(passwordLabel);
		this.add(passwordTextField);
		this.add(FirstNameLabel);
		this.add(FirstNameTextField);
		this.add(LastNameLabel);
		this.add(LastNameTextField);
		this.add(GTIDLabel);
		this.add(GTIDTextField);
		this.add(ResidenceHallLabel);
		this.add(ResidenceHallTextField);

		this.add(login);
	}

	private class LoginButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			CurrentUser.setUserName(userNameTextField.getText());
			CurrentUser.setPassword(passwordTextField.getText());
			CurrentUser.setFirstName(FirstNameTextField.getText());
			CurrentUser.setLastName(LastNameTextField.getText());
			CurrentUser.setResidenceHall(ResidenceHallTextField.getText());
			Long GTIDNumber = 0L;
			try {
				GTIDNumber = Long.parseLong(GTIDTextField.getText());
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(new JFrame(),
						"Enter GTID in a valid format", "Inane error",
						JOptionPane.ERROR_MESSAGE);
			}

			CurrentUser.setGTID(GTIDNumber);
			if (CurrentUser.getUserName().isEmpty()
					|| CurrentUser.getPassword().isEmpty()
					|| CurrentUser.getFirstName().isEmpty()
					|| CurrentUser.getLastName().isEmpty()
					|| CurrentUser.getGTID() == null
					|| CurrentUser.getResidenceHall().isEmpty()) { 
				JOptionPane.showMessageDialog(new JFrame(),
						"Information not entered", "Inane error",
						JOptionPane.ERROR_MESSAGE);
			} else {
				if (!com.ece4894.services.StudentUserDao.isUserExistent(
						CurrentUser.getUserName(), CurrentUser.getPassword())) {
					JOptionPane.showMessageDialog(new JFrame(),
							"Incorrect Login", "Inane error",
							JOptionPane.ERROR_MESSAGE);
				} else {
					JFrame mainFrame = MainFrame.getMain();
					RequestPage panel = new RequestPage(CurrentUser);
					mainFrame.setContentPane(panel);
					mainFrame.setBounds(mainFrame.getContentPane().getBounds());
					mainFrame.setVisible(true);
					mainFrame.repaint();
				}
			}
		}
	}
}
