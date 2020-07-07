package com.Corona.Forecast.View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.CardLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.List;
import java.util.ArrayList;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import com.Corona.Forecast.Controller.*;
import javax.swing.JComboBox;

public class PopulationInfoFormDisplay extends JFrame {

	private JPanel contentPane;
	private JLayeredPane layeredPane;
	private List<JPanel> panelList = new ArrayList<JPanel>();
	private int panelNumber = 0;
	private JButton btnNext;
	private JButton btnPrevious;
	private JLabel lblInfoForm;
	private JButton btnFinish;
	private JRadioButton rdbtnIsolation;
	private JRadioButton rdbtnNoIsolation;
	private JSeparator separator;
	private JSeparator separator_1;
	private JLabel lblContactTracing;
	private JRadioButton rdbtnContactTracing;
	private JRadioButton rdbtnNoContactTracing;
	private JRadioButton rdbtnTravelRestrictions;
	private JRadioButton rdbtnNoTravelRestrictions;
	private JPanel hygienePanel;
	private JPanel centralLocationsPanel;
	private JPanel totalPopulationPanel;
	private JPanel currentNumberOfInfectedPanel;
	private JPanel testingPerDayPanel;
	private JPanel graphChoicePanel;
	private JLabel lblHygiene;
	private JRadioButton rdbtnHygiene;
	private JRadioButton rdbtnNoHygiene;
	private JLabel lblCentralLocations;
	private JRadioButton rdbtnCentralShutdown;
	private JRadioButton rdbtnNoCentralShutdown;
	private JLabel lblTotalPopulation;
	private JTextField totalPopulationField;
	private JTextField currentInfectedField;
	private JTextField testsPerDayField;
	JComboBox graphChoiceComboBox;
	private PopulationInfoProcessor populationInfoController =  new PopulationInfoProcessor();
	private Instruction analystInstructions = new Instruction();
	private JPanel daysPanel;
	private JTextField daysField;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PopulationInfoFormDisplay frame = new PopulationInfoFormDisplay();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	//Method to switch panels
	public void switchPanels(JPanel nextPanel)
	{
		layeredPane.removeAll();
		layeredPane.add(nextPanel);
		layeredPane.repaint();
		layeredPane.revalidate();
	}

	/**
	 * Create the frame.
	 */
	public PopulationInfoFormDisplay() {
		this.setTitle("Corona Forecast - Population Info Form");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(500, 200, 600, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		layeredPane = new JLayeredPane();
		layeredPane.setBounds(10, 96, 564, 211);
		contentPane.add(layeredPane);
		layeredPane.setLayout(new CardLayout(0, 0));
		
		JPanel isolationPanel = new JPanel();
		layeredPane.add(isolationPanel, "name_628443032524327");
		isolationPanel.setLayout(null);
		
		JLabel lblIsolation = new JLabel("<html><center>Are there active measures being taken to isolate the infected?</center></html>");
		lblIsolation.setHorizontalAlignment(SwingConstants.CENTER);
		lblIsolation.setFont(new Font("Baskerville Old Face", Font.PLAIN, 18));
		lblIsolation.setBounds(110, 39, 329, 60);
		isolationPanel.add(lblIsolation);
		
		JPanel contactTracingPanel = new JPanel();
		layeredPane.add(contactTracingPanel, "name_628505908377600");
		contactTracingPanel.setLayout(null);
		
		JPanel travelRestrictionsPanel = new JPanel();
		layeredPane.add(travelRestrictionsPanel, "name_628511780359437");
		travelRestrictionsPanel.setLayout(null);
		
		lblInfoForm = new JLabel("<html>Please fill out the following information:</html>");
		lblInfoForm.setFont(new Font("Baskerville Old Face", Font.BOLD, 24));
		lblInfoForm.setHorizontalAlignment(SwingConstants.CENTER);
		lblInfoForm.setBounds(10, 11, 564, 68);
		contentPane.add(lblInfoForm);
		
		

		
		rdbtnIsolation = new JRadioButton("Yes");
		rdbtnIsolation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rdbtnIsolation.isSelected())
					rdbtnNoIsolation.setSelected(false);
			}
		});
		rdbtnIsolation.setFont(new Font("Baskerville Old Face", Font.PLAIN, 18));
		rdbtnIsolation.setBounds(151, 124, 109, 23);
		isolationPanel.add(rdbtnIsolation);
		
		rdbtnNoIsolation = new JRadioButton("No");
		rdbtnNoIsolation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rdbtnNoIsolation.isSelected())
					rdbtnIsolation.setSelected(false);
			}
		});
		rdbtnNoIsolation.setFont(new Font("Baskerville Old Face", Font.PLAIN, 18));
		rdbtnNoIsolation.setBounds(330, 124, 109, 23);
		isolationPanel.add(rdbtnNoIsolation);
		
		lblContactTracing = new JLabel("<html><center>Is contact tracing implemented across the population?</center></html>");
		lblContactTracing.setHorizontalAlignment(SwingConstants.CENTER);
		lblContactTracing.setFont(new Font("Baskerville Old Face", Font.PLAIN, 18));
		lblContactTracing.setBounds(110, 39, 329, 60);
		contactTracingPanel.add(lblContactTracing);
		
		rdbtnContactTracing = new JRadioButton("Yes");
		rdbtnContactTracing.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(rdbtnContactTracing.isSelected())
					rdbtnNoContactTracing.setSelected(false);
			}
		});
		rdbtnContactTracing.setFont(new Font("Baskerville Old Face", Font.PLAIN, 18));
		rdbtnContactTracing.setBounds(151, 124, 109, 23);
		contactTracingPanel.add(rdbtnContactTracing);
		
		rdbtnNoContactTracing = new JRadioButton("No");
		rdbtnNoContactTracing.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rdbtnNoContactTracing.isSelected())
					rdbtnContactTracing.setSelected(false);
			}
		});
		rdbtnNoContactTracing.setFont(new Font("Baskerville Old Face", Font.PLAIN, 18));
		rdbtnNoContactTracing.setBounds(330, 124, 109, 23);
		contactTracingPanel.add(rdbtnNoContactTracing);
		
		JLabel lblTravelRestrictions = new JLabel("<html><center>Are there Incoming Travel restrictions in place?</center></html>");
		lblTravelRestrictions.setHorizontalAlignment(SwingConstants.CENTER);
		lblTravelRestrictions.setFont(new Font("Baskerville Old Face", Font.PLAIN, 18));
		lblTravelRestrictions.setBounds(110, 39, 329, 60);
		travelRestrictionsPanel.add(lblTravelRestrictions);
		
		rdbtnTravelRestrictions = new JRadioButton("Yes");
		rdbtnTravelRestrictions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rdbtnTravelRestrictions.isSelected())
					rdbtnNoTravelRestrictions.setSelected(false);
			}
		});
		rdbtnTravelRestrictions.setFont(new Font("Baskerville Old Face", Font.PLAIN, 18));
		rdbtnTravelRestrictions.setBounds(151, 124, 109, 23);
		travelRestrictionsPanel.add(rdbtnTravelRestrictions);
		
		rdbtnNoTravelRestrictions = new JRadioButton("No");
		rdbtnNoTravelRestrictions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rdbtnNoTravelRestrictions.isSelected())
					rdbtnTravelRestrictions.setSelected(false);
			}
		});
		rdbtnNoTravelRestrictions.setFont(new Font("Baskerville Old Face", Font.PLAIN, 18));
		rdbtnNoTravelRestrictions.setBounds(330, 124, 109, 23);
		travelRestrictionsPanel.add(rdbtnNoTravelRestrictions);
		
		hygienePanel = new JPanel();
		layeredPane.add(hygienePanel, "name_813091373523615");
		hygienePanel.setLayout(null);
		
		lblHygiene = new JLabel("<html><center>Is there an active Hygiene and Safety campaign?</center></html>");
		lblHygiene.setHorizontalAlignment(SwingConstants.CENTER);
		lblHygiene.setFont(new Font("Baskerville Old Face", Font.PLAIN, 18));
		lblHygiene.setBounds(110, 39, 329, 60);
		hygienePanel.add(lblHygiene);
		
		rdbtnHygiene = new JRadioButton("Yes");
		rdbtnHygiene.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rdbtnHygiene.isSelected())
					rdbtnNoHygiene.setSelected(false);
			}
		});
		rdbtnHygiene.setFont(new Font("Baskerville Old Face", Font.PLAIN, 18));
		rdbtnHygiene.setBounds(151, 124, 109, 23);
		hygienePanel.add(rdbtnHygiene);
		
		rdbtnNoHygiene = new JRadioButton("No");
		rdbtnNoHygiene.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rdbtnNoHygiene.isSelected())
					rdbtnHygiene.setSelected(false);
			}
		});
		rdbtnNoHygiene.setFont(new Font("Baskerville Old Face", Font.PLAIN, 18));
		rdbtnNoHygiene.setBounds(330, 124, 109, 23);
		hygienePanel.add(rdbtnNoHygiene);
		
		centralLocationsPanel = new JPanel();
		layeredPane.add(centralLocationsPanel, "name_813127277763465");
		centralLocationsPanel.setLayout(null);
		
		lblCentralLocations = new JLabel("<html><center>Are central locations around the country currently shut down (Malls,  Markets etc.)?</center></html>");
		lblCentralLocations.setHorizontalAlignment(SwingConstants.CENTER);
		lblCentralLocations.setFont(new Font("Baskerville Old Face", Font.PLAIN, 18));
		lblCentralLocations.setBounds(110, 39, 329, 60);
		centralLocationsPanel.add(lblCentralLocations);
		
		rdbtnCentralShutdown = new JRadioButton("Yes");
		rdbtnCentralShutdown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rdbtnCentralShutdown.isSelected())
					rdbtnNoCentralShutdown.setSelected(false);
			}
		});
		rdbtnCentralShutdown.setFont(new Font("Baskerville Old Face", Font.PLAIN, 18));
		rdbtnCentralShutdown.setBounds(151, 124, 109, 23);
		centralLocationsPanel.add(rdbtnCentralShutdown);
		
		rdbtnNoCentralShutdown = new JRadioButton("No");
		rdbtnNoCentralShutdown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rdbtnNoCentralShutdown.isSelected())
					rdbtnCentralShutdown.setSelected(false);
			}
		});
		rdbtnNoCentralShutdown.setFont(new Font("Baskerville Old Face", Font.PLAIN, 18));
		rdbtnNoCentralShutdown.setBounds(330, 124, 109, 23);
		centralLocationsPanel.add(rdbtnNoCentralShutdown);
		
		totalPopulationPanel = new JPanel();
		layeredPane.add(totalPopulationPanel, "name_813157006938792");
		totalPopulationPanel.setLayout(null);
		
		lblTotalPopulation = new JLabel("<html><center>What is the total population in your country?</center></html>");
		lblTotalPopulation.setHorizontalAlignment(SwingConstants.CENTER);
		lblTotalPopulation.setFont(new Font("Baskerville Old Face", Font.PLAIN, 18));
		lblTotalPopulation.setBounds(110, 39, 329, 60);
		totalPopulationPanel.add(lblTotalPopulation);
		
		totalPopulationField = new JTextField();
		totalPopulationField.setBounds(228, 138, 86, 20);
		totalPopulationPanel.add(totalPopulationField);
		totalPopulationField.setColumns(10);
		
		currentNumberOfInfectedPanel = new JPanel();
		layeredPane.add(currentNumberOfInfectedPanel, "name_813210504724510");
		currentNumberOfInfectedPanel.setLayout(null);
		
		JLabel lblCurrentInfected = new JLabel("<html><center>What is the current number of infected?</center></html>");
		lblCurrentInfected.setHorizontalAlignment(SwingConstants.CENTER);
		lblCurrentInfected.setFont(new Font("Baskerville Old Face", Font.PLAIN, 18));
		lblCurrentInfected.setBounds(111, 39, 329, 60);
		currentNumberOfInfectedPanel.add(lblCurrentInfected);
		
		currentInfectedField = new JTextField();
		currentInfectedField.setColumns(10);
		currentInfectedField.setBounds(229, 138, 86, 20);
		currentNumberOfInfectedPanel.add(currentInfectedField);
		
		testingPerDayPanel = new JPanel();
		layeredPane.add(testingPerDayPanel, "name_813244131720880");
		testingPerDayPanel.setLayout(null);
		
		JLabel lblTestingPerDay = new JLabel("<html><center>How many tests are performed per day?</center></html>");
		lblTestingPerDay.setHorizontalAlignment(SwingConstants.CENTER);
		lblTestingPerDay.setFont(new Font("Baskerville Old Face", Font.PLAIN, 18));
		lblTestingPerDay.setBounds(110, 39, 329, 60);
		testingPerDayPanel.add(lblTestingPerDay);
		
		testsPerDayField = new JTextField();
		testsPerDayField.setColumns(10);
		testsPerDayField.setBounds(228, 138, 86, 20);
		testingPerDayPanel.add(testsPerDayField);
		
		
		btnNext = new JButton("Next");
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelNumber++;
				
				//Showing the "Previous" button once we are past the first panel
				if(panelNumber > 0)
					btnPrevious.setVisible(true);
				
				try {					
					//Switching panels
					JPanel nextPanel = panelList.get(panelNumber);
					switchPanels(nextPanel);
				}
				
				//In case of form being filled out completely
				catch(Exception outOfBounds) {
					separator.setVisible(false);
					separator_1.setVisible(false);
					layeredPane.remove((panelList.get(panelNumber-1)));
					lblInfoForm.setText("<html><center>Form Complete</center>"
							+ "Please click \"Finish\" to display the graph"
							+ "<center>Or click \"Back\" to modify the form</html>");
					btnNext.setVisible(false);
					btnFinish.setVisible(true);
				}
			}
		});
		btnNext.setBounds(452, 327, 89, 23);
		contentPane.add(btnNext);
		
		btnPrevious = new JButton("Back");
		btnPrevious.setVisible(false);
		btnPrevious.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Moving back going to the previous panel after reaching the last one
				if(panelNumber >= panelList.size()) {
					separator.setVisible(true);
					separator_1.setVisible(true);
					lblInfoForm.setText("<html>Please fill out the following information:</html>");
					btnFinish.setVisible(false);
					btnNext.setVisible(true);
				}
				
				panelNumber--;
				
				//Disable the "Previous" button on the first panel
				if(panelNumber == 0)
					btnPrevious.setVisible(false);
				
				try {
					//Switching panels
					JPanel previousPanel = panelList.get(panelNumber);
					switchPanels(previousPanel);
				}
				catch(Exception outOfBounds) {}
			}
		});
		btnPrevious.setBounds(48, 327, 89, 23);
		contentPane.add(btnPrevious);
		
		btnFinish = new JButton("Finish");
		btnFinish.addActionListener(new ActionListener() {
			
			//When Finish button is clicked form is checked for validity before invoking the controller to process
			//the data entered
			public void actionPerformed(ActionEvent e) {
				if(
						(rdbtnIsolation.isSelected() == false && rdbtnNoIsolation.isSelected() == false) ||
						(rdbtnContactTracing.isSelected() == false && rdbtnNoContactTracing.isSelected() == false) ||
						(rdbtnTravelRestrictions.isSelected() == false && rdbtnNoTravelRestrictions.isSelected() == false) ||
						(rdbtnHygiene.isSelected() == false && rdbtnNoHygiene.isSelected() == false) ||
						(rdbtnCentralShutdown.isSelected() == false && rdbtnNoCentralShutdown.isSelected() == false) ||
						totalPopulationField.getText().equals("") ||
						currentInfectedField.getText().equals("") ||
						testsPerDayField.getText().equals("")|| 
						(String)(graphChoiceComboBox.getSelectedItem()) == "" ||
						daysField.getText().contentEquals("")) {
					JOptionPane.showMessageDialog(null, "Please fill out the whole form","Error",JOptionPane.ERROR_MESSAGE);
				}
				else {
					populationInfoController.setIsolationStatus(rdbtnIsolation.isSelected());
					populationInfoController.setContactTracingStatus(rdbtnContactTracing.isSelected());
					populationInfoController.setTravelRestrictionsStatus(rdbtnTravelRestrictions.isSelected());
					populationInfoController.setHygieneInformationCampaignStatus(rdbtnHygiene.isSelected());
					populationInfoController.setCentralLocationShudtownStatus(rdbtnCentralShutdown.isSelected());
					populationInfoController.setTotalPopulation(Integer.parseInt(totalPopulationField.getText()));
					populationInfoController.setCurrentlyInfected(Integer.parseInt(currentInfectedField.getText()));
					populationInfoController.setTestsPerDay(Integer.parseInt(testsPerDayField.getText()));
					
					VirusInfoUpdateProcessor virus = new VirusInfoUpdateProcessor();
					DataPointProcessor DPController = new DataPointProcessor(populationInfoController,virus,Integer.parseInt(daysField.getText()));
					
					if(((String)graphChoiceComboBox.getSelectedItem()).equals("Infected")) {						
						InfectedGraph infected =  new InfectedGraph(DPController);
						infected.createAndShowGui();
					}
					else if(((String)graphChoiceComboBox.getSelectedItem()).equals("ICU")) {
						ICUGraph icu = new ICUGraph(DPController);
						icu.createAndShowGui();
					}
					else if(((String)graphChoiceComboBox.getSelectedItem()).equals("Deceased")) {
						DeceasedGraph deceased = new DeceasedGraph(DPController);
						deceased.createAndShowGui();
					}
					else if(((String)graphChoiceComboBox.getSelectedItem()).equals("Recovered")) {
						RecoveredGraph recovered = new RecoveredGraph(DPController);
						recovered.createAndShowGui();
					}
					else if(((String)graphChoiceComboBox.getSelectedItem()).equals("Infection Rate")) {
						InfectionRateGraph infectionRate = new InfectionRateGraph(DPController);
						infectionRate.createAndShowGui();
					}
				}
			}
		});
		btnFinish.setBounds(246, 327, 89, 23);
		contentPane.add(btnFinish);
		
		separator = new JSeparator();
		separator.setBounds(10, 83, 564, 2);
		contentPane.add(separator);
		
		separator_1 = new JSeparator();
		separator_1.setBounds(10, 318, 564, 2);
		contentPane.add(separator_1);
		btnFinish.setVisible(false);
		
		daysPanel = new JPanel();
		layeredPane.add(daysPanel, "name_355130227423197");
		daysPanel.setLayout(null);
		
		JLabel lblDays = new JLabel("<html><center>How many days do you want to Forecast? (3-180)</center></html>");
		lblDays.setHorizontalAlignment(SwingConstants.CENTER);
		lblDays.setFont(new Font("Baskerville Old Face", Font.PLAIN, 18));
		lblDays.setBounds(110, 39, 329, 60);
		daysPanel.add(lblDays);
		
		daysField = new JTextField();
		daysField.setColumns(10);
		daysField.setBounds(228, 138, 86, 20);
		daysPanel.add(daysField);
		
		graphChoicePanel = new JPanel();
		layeredPane.add(graphChoicePanel, "name_265237055905723");
		
		JLabel lblGraphChoice = new JLabel("<html><center>Please choose the Graph you would like the program to display:</center></html>");
		lblGraphChoice.setHorizontalAlignment(SwingConstants.CENTER);
		lblGraphChoice.setFont(new Font("Baskerville Old Face", Font.PLAIN, 18));
		lblGraphChoice.setBounds(110, 39, 329, 60);
		graphChoicePanel.add(lblGraphChoice);
		
		graphChoiceComboBox = new JComboBox();
		graphChoiceComboBox.setBounds(204, 110, 140, 20);
		graphChoicePanel.add(graphChoiceComboBox);
		
		graphChoiceComboBox.addItem("");
		graphChoiceComboBox.addItem("Infected");
		graphChoiceComboBox.addItem("ICU");
		graphChoiceComboBox.addItem("Deceased");
		graphChoiceComboBox.addItem("Recovered");
		graphChoiceComboBox.addItem("Infection Rate");
		
		//List of panels added to the array list that contains them
		panelList.add(isolationPanel);
		panelList.add(contactTracingPanel);
		panelList.add(totalPopulationPanel);
		panelList.add(hygienePanel);
		panelList.add(centralLocationsPanel);
		panelList.add(travelRestrictionsPanel);
		panelList.add(currentNumberOfInfectedPanel);
		panelList.add(testingPerDayPanel);
		panelList.add(daysPanel);
		panelList.add(graphChoicePanel);
		graphChoicePanel.setLayout(null);
		
		JButton btnHelpAnalyst = new JButton("Help");
		btnHelpAnalyst.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null, analystInstructions.getAnalystInstructions(), "Analyst Instructions", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		btnHelpAnalyst.setBounds(511, 11, 63, 23);
		contentPane.add(btnHelpAnalyst);
	}
}
