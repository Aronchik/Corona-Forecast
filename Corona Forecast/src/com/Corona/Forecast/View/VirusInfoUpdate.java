package com.Corona.Forecast.View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import com.Corona.Forecast.Controller.*;

public class VirusInfoUpdate extends JFrame {

	private JPanel contentPane;
	private JTextField basicReproductiveNumberFIeld;
	private JTextField illnessDurationField;
	private JRadioButton rdbtnVaccineAvailable;
	private JRadioButton rdbtnVaccineNotAvailable;
	private VirusInfoUpdateProcessor virusInfoController = new VirusInfoUpdateProcessor();
	private Instruction epidemiologistInstructions = new Instruction();
	
	//Methods
	void updateReproductiveNumber() {}
	void updateVaccineInfo() {}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VirusInfoUpdate frame = new VirusInfoUpdate();
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
	public VirusInfoUpdate() {
		this.setTitle("Corona Forecast Virus Info");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(500, 300, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblBasicReproductiveNumber = new JLabel("Basic Reproductive Number:");
		lblBasicReproductiveNumber.setFont(new Font("Baskerville Old Face", Font.PLAIN, 16));
		lblBasicReproductiveNumber.setBounds(29, 36, 191, 24);
		contentPane.add(lblBasicReproductiveNumber);
		
		JLabel lblVaccineAvailability = new JLabel("Vaccine Availability:");
		lblVaccineAvailability.setFont(new Font("Baskerville Old Face", Font.PLAIN, 16));
		lblVaccineAvailability.setBounds(29, 115, 142, 24);
		contentPane.add(lblVaccineAvailability);
		
		JLabel lblIllnessDuration = new JLabel("Illness Duration (Days):");
		lblIllnessDuration.setFont(new Font("Baskerville Old Face", Font.PLAIN, 16));
		lblIllnessDuration.setBounds(29, 193, 191, 24);
		contentPane.add(lblIllnessDuration);
		
		basicReproductiveNumberFIeld = new JTextField();
		basicReproductiveNumberFIeld.setBounds(230, 36, 86, 20);
		contentPane.add(basicReproductiveNumberFIeld);
		basicReproductiveNumberFIeld.setColumns(10);
		
		rdbtnVaccineAvailable = new JRadioButton("Available");
		rdbtnVaccineAvailable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(rdbtnVaccineAvailable.isSelected())
					rdbtnVaccineNotAvailable.setSelected(false);
			}
		});
		rdbtnVaccineAvailable.setBounds(177, 116, 109, 23);
		contentPane.add(rdbtnVaccineAvailable);
		
		rdbtnVaccineNotAvailable = new JRadioButton("Not Available");
		rdbtnVaccineNotAvailable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rdbtnVaccineNotAvailable.isSelected())
					rdbtnVaccineAvailable.setSelected(false);
			}
		});
		rdbtnVaccineNotAvailable.setBounds(288, 116, 109, 23);
		contentPane.add(rdbtnVaccineNotAvailable);
		
		illnessDurationField = new JTextField();
		illnessDurationField.setColumns(10);
		illnessDurationField.setBounds(200, 195, 86, 20);
		contentPane.add(illnessDurationField);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(29, 87, 379, 2);
		contentPane.add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(29, 164, 379, 2);
		contentPane.add(separator_1);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				//If form is not properly filled out then do nothing
				if(basicReproductiveNumberFIeld.getText().equals("") ||
						illnessDurationField.getText().equals("") ||
						(rdbtnVaccineAvailable.isSelected() == false && 
						rdbtnVaccineNotAvailable.isSelected() == false)
						) {
					JOptionPane.showMessageDialog(null, "Please fill out the whole form","Error",JOptionPane.ERROR_MESSAGE);
					}
				else
				{
					virusInfoController.updateVirusInformation(Double.parseDouble(basicReproductiveNumberFIeld.getText()),
							rdbtnVaccineAvailable.isSelected(), 
							Integer.parseInt(illnessDurationField.getText()));
					JOptionPane.showMessageDialog(null, "Virus Info Updated", "Success", JOptionPane.INFORMATION_MESSAGE);
					setVisible(false);
				}
			}
		});
		btnSubmit.setBounds(319, 227, 89, 23);
		contentPane.add(btnSubmit);
		
		JButton btnHelpEpidemiologist = new JButton("Help");
		btnHelpEpidemiologist.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, epidemiologistInstructions.getEpidemiologistInstructions(), "Epidemiologist Instructions", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		btnHelpEpidemiologist.setBounds(361, 11, 63, 23);
		contentPane.add(btnHelpEpidemiologist);
	}
}
