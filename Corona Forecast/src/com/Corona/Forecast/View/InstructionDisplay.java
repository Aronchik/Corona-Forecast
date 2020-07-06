package com.Corona.Forecast.View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class InstructionDisplay extends JFrame {

	private JPanel contentPane;
	private JLabel lblInstructions;
	
	//Attributes
	private int typeOfInfoRequest;
	
	//Methods
	private void displayUserInstructions(JLabel label) {
		label.setText(
				"<html>As an Analyst you will be able to look at the different graphs that are available"
				+ " for you in the program.</html>");
	}
	private void displayEpidemiologistInstructions(JLabel label) {
		label.setText(
				"<html> As an Epidemiologist you will be able to set virus information parameters"
				+ " in accordace with the latest scientific studies.</html>");
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InstructionDisplay frame = new InstructionDisplay();
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
	public InstructionDisplay() {
		this.setTitle("Corona Forecast");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(450, 200, 700, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblInstructions = new JLabel("Please choose your user profile");
		lblInstructions.setHorizontalAlignment(SwingConstants.CENTER);
		lblInstructions.setFont(new Font("Baskerville Old Face", Font.PLAIN, 17));
		lblInstructions.setBounds(65, 135, 564, 297);
		contentPane.add(lblInstructions);
		
		JButton btnAnalystInstructions = new JButton("Analyst");
		btnAnalystInstructions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				typeOfInfoRequest = 1;
				displayUserInstructions(lblInstructions);
			}
		});
		btnAnalystInstructions.setFont(new Font("Baskerville Old Face", Font.PLAIN, 22));
		btnAnalystInstructions.setBounds(102, 32, 145, 53);
		contentPane.add(btnAnalystInstructions);
		
		JButton btnEpidemiologistInstructions = new JButton("Epidemiologist");
		btnEpidemiologistInstructions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				typeOfInfoRequest = 2;
				displayEpidemiologistInstructions(lblInstructions);
			}
		});
		btnEpidemiologistInstructions.setFont(new Font("Baskerville Old Face", Font.PLAIN, 22));
		btnEpidemiologistInstructions.setBounds(412, 31, 165, 55);
		contentPane.add(btnEpidemiologistInstructions);
		

	}
}
