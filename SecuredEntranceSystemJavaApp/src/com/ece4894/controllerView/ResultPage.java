package com.ece4894.controllerView;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JLabel;
import javax.swing.JPanel;

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

	public ResultPage(String ResultString) {
		this.setLayout(new FlowLayout());
		setBounds(screenSize.width / 2 - 200, screenSize.height / 2 - 100, 350,
				130);


		userNameLabel = new JLabel(ResultString);
		userNameLabel.setFont(new Font("Helvetica", Font.BOLD, 50));

		this.add(userNameLabel);
	}
}
