package com.ece4894.controllerView;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * @author Sahil Gupta
 * 
 *         This class creates the frame and starts the application.
 */

public class MainFrame {
	static JFrame main;
	static JPanel mainPanel;
	static JButton login;
	static JButton register;

	final static int WIDTH = 400, HEIGHT = 500;
	final static int DIALOGWIDTH = 300, DIALOGHEIGHT = 400;
	final static Dimension screenSize = Toolkit.getDefaultToolkit()
			.getScreenSize();

	public static void init() {
		mainPanel = new LoginPage();
	}

	public static void main(String[] args) {
		main = new JFrame("GT Secured Entrance System");
		main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		init();
		main.setBounds(mainPanel.getBounds());
		main.setContentPane(mainPanel);
		main.setVisible(true);
	}

	/**
	 * @return the main
	 */
	public static JFrame getMain() {
		return main;
	}
}