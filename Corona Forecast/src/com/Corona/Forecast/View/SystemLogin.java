package com.Corona.Forecast.View;


import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JSeparator;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import com.Corona.Forecast.Controller.*;

public class SystemLogin extends JFrame {

	private JPanel contentPane;
	private JTextField usernameField;
	private JPasswordField passwordField;
	private Authentication authController  = new Authentication();
	
	private VirusInfoUpdate epidemiologistDisplay;
	private PopulationInfoFormDisplay analystDisplay;
	private Instruction loginInstruction = new Instruction();
	
	//Username and password getters
	public String getUsernameField() {
		return usernameField.getText();
	}

	public String getPasswordField() {
		return passwordField.getText();
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SystemLogin frame = new SystemLogin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public SystemLogin() {
		this.setTitle("Corona Forecast Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(500, 250, 500, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lbSystemLogin = new JLabel("System Login");
		lbSystemLogin.setFont(new Font("Baskerville Old Face", Font.BOLD, 25));
		lbSystemLogin.setBounds(174, 11, 211, 35);
		contentPane.add(lbSystemLogin);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font("Baskerville Old Face", Font.PLAIN, 16));
		lblUsername.setBounds(45, 76, 74, 14);
		contentPane.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Baskerville Old Face", Font.PLAIN, 16));
		lblPassword.setBounds(45, 164, 74, 14);
		contentPane.add(lblPassword);
		
		usernameField = new JTextField();
		usernameField.setBounds(160, 73, 211, 20);
		contentPane.add(usernameField);
		usernameField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(160, 161, 211, 20);
		contentPane.add(passwordField);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String username = usernameField.getText();
				String password = passwordField.getText();
				
				int attemptResult = authController.Login(username, password);
				
				if(username.equals("") || password.equals("")) {}
				else if(attemptResult == 1) {
					usernameField.setText(null);
					passwordField.setText(null);
					JOptionPane.showMessageDialog(null, authController.ShowAttemptResults(attemptResult), "Login", JOptionPane.INFORMATION_MESSAGE);	
					setVisible(false);
					
					analystDisplay = new PopulationInfoFormDisplay();
					analystDisplay.main(null);
				}
				else if(attemptResult == 2) {
					usernameField.setText(null);
					passwordField.setText(null);
					JOptionPane.showMessageDialog(null, authController.ShowAttemptResults(attemptResult), "Login", JOptionPane.INFORMATION_MESSAGE);	
					setVisible(false);
					
					epidemiologistDisplay = new VirusInfoUpdate();
					epidemiologistDisplay.main(null);
				}
				else if(attemptResult == 3) {
					usernameField.setText(null);
					passwordField.setText(null);
					JOptionPane.showMessageDialog(null, authController.ShowAttemptResults(attemptResult), "Login", JOptionPane.INFORMATION_MESSAGE);	
					setVisible(false);
				}
				else {
					usernameField.setText(null);
					passwordField.setText(null);
					JOptionPane.showMessageDialog(null, authController.ShowAttemptResults(attemptResult),"Error",JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnLogin.setFont(new Font("Baskerville Old Face", Font.PLAIN, 16));
		btnLogin.setBounds(45, 227, 89, 23);
		contentPane.add(btnLogin);
		
		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				usernameField.setText(null);
				passwordField.setText(null);
			}
		});
		btnReset.setFont(new Font("Baskerville Old Face", Font.PLAIN, 16));
		btnReset.setBounds(200, 227, 89, 23);
		contentPane.add(btnReset);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);			
			}
		});
		btnExit.setFont(new Font("Baskerville Old Face", Font.PLAIN, 16));
		btnExit.setBounds(352, 227, 89, 23);
		contentPane.add(btnExit);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 204, 464, 2);
		contentPane.add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(10, 57, 464, 2);
		contentPane.add(separator_1);
		
		JButton btnHelpLogin = new JButton("Help");
		btnHelpLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, loginInstruction.getLoginInstructions(), "Login Instructions", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		btnHelpLogin.setBounds(411, 11, 63, 23);
		contentPane.add(btnHelpLogin);
	}

}
