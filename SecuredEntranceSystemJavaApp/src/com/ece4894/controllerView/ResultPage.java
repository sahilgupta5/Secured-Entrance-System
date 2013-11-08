package com.ece4894.controllerView;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.ece4894.model.Resident;

/**
 * @author Sahil Gupta
 * 
 *         This class displays the success or failure of the request.
 */

public class ResultPage extends JPanel {

	private static final long serialVersionUID = 1L;
	final static Dimension screenSize = Toolkit.getDefaultToolkit()
			.getScreenSize();

	JLabel userNameLabel;
	JButton AnotherRequest, Logout;
	Resident CurrentUser;

	public ResultPage(String ResultString, Resident CurrentUser) {
		this.setLayout(new FlowLayout());
		setBounds(screenSize.width / 2 - 200, screenSize.height / 2 - 100, 350,
				130);

		this.CurrentUser = CurrentUser;

		userNameLabel = new JLabel(ResultString);
		userNameLabel.setFont(new Font("Helvetica", Font.BOLD, 20));

		AnotherRequest = new JButton("Another Request");
		AnotherRequest.addActionListener(new AnotherRequestButtonListener());

		Logout = new JButton("Logout");
		Logout.addActionListener(new LogoutButtonListener());
		
		this.add(userNameLabel);
		this.add(AnotherRequest);
		this.add(Logout);
	}
	
	private class AnotherRequestButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			JFrame mainFrame = MainFrame.getMain();
			RequestPage panel = new RequestPage(CurrentUser);
			mainFrame.setContentPane(panel);
			mainFrame.setBounds(mainFrame.getContentPane().getBounds());
			mainFrame.setVisible(true);
			mainFrame.repaint();
		}
	}
	
	private class LogoutButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			JFrame mainFrame = MainFrame.getMain();
			LoginPage panel = new LoginPage();
			mainFrame.setContentPane(panel);
			mainFrame.setBounds(mainFrame.getContentPane().getBounds());
			mainFrame.setVisible(true);
			mainFrame.repaint();
		}
	}
}
