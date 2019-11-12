package ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class ProgramWindow {

	private JFrame window;
	private JTabbedPane tabPane;
	
	//Lot creation panel
	private JPanel editLotsPanel;
	
	private JPanel addLotPanel;
	private JTextField newLotName;
	private JButton newLotSubmit;
	private JTable activeLotsTable;
	
	private JPanel addSectionPanel;
	private JTextField newSectionName;
	private JTextField newSectionSpots;
	private JButton newSectionSubmit;
	private JTable lotSectionsTable;
	
	private JPanel customerPanel;
	
	private JPanel transactionsPanel;
	
	public ProgramWindow() {
		
		//Create the window frame
		window = new JFrame("Parking Program");
		
		//Create the tab pane and all the sub-panels
		tabPane = new JTabbedPane();
		editLotsPanel = new JPanel();
		tabPane.addTab("Edit Lots", editLotsPanel);
		customerPanel = new JPanel();
		tabPane.addTab("Customer Portal", customerPanel);
		transactionsPanel = new JPanel();
		tabPane.addTab("Transaction Log", transactionsPanel);
		window.add(tabPane);
		
		//Build the lot editing panel
		editLotsPanel.setLayout(new GridBagLayout());
		GridBagConstraints editConstraints = new GridBagConstraints();
		editConstraints.anchor = GridBagConstraints.CENTER;
		editConstraints.weighty = 1;
		
		Insets addPanelInsets = new Insets(20, 10, 0, 10);
		Insets tableInsets = new Insets(0, 0, 0, 0);
		
		addLotPanel = new JPanel(new BorderLayout());
		JLabel addLotLabel = new JLabel("Add Lot");
		addLotLabel.setHorizontalAlignment(JLabel.CENTER);
		addLotPanel.add(addLotLabel, BorderLayout.PAGE_START);
		addLotPanel.add(new JLabel("Name: "), BorderLayout.LINE_START);
		newLotName = new JTextField();
		addLotPanel.add(newLotName, BorderLayout.CENTER);
		newLotSubmit = new JButton("Add");
		addLotPanel.add(newLotSubmit, BorderLayout.PAGE_END);
		editConstraints.anchor = GridBagConstraints.PAGE_START;
		editConstraints.fill = GridBagConstraints.HORIZONTAL;
		editConstraints.insets = addPanelInsets;
		editConstraints.weightx = 0.2;
		editConstraints.gridx = 0;
		editConstraints.gridy = 0;
		editLotsPanel.add(addLotPanel, editConstraints);
		
		addSectionPanel = new JPanel(new GridBagLayout());
		GridBagConstraints addSectionConstraints = new GridBagConstraints();
		addSectionConstraints.fill = GridBagConstraints.BOTH;
		
		JLabel addSectionLabel = new JLabel("Add Section");
		addSectionLabel.setHorizontalAlignment(JLabel.CENTER);
		addSectionConstraints.gridwidth = 3;
		addSectionConstraints.gridx = 0;
		addSectionConstraints.gridy = 0;
		addSectionPanel.add(addSectionLabel, addSectionConstraints);

		addSectionConstraints.gridwidth = 1;
		addSectionConstraints.gridx = 0;
		addSectionConstraints.gridy = 1;
		addSectionPanel.add(new JLabel("Name: "), addSectionConstraints);
		
		newSectionName = new JTextField();
		addSectionConstraints.gridx = 1;
		addSectionConstraints.weightx = 1;
		addSectionConstraints.gridwidth = GridBagConstraints.REMAINDER;
		addSectionPanel.add(newSectionName, addSectionConstraints);
		
		addSectionConstraints.gridwidth = 1;
		addSectionConstraints.gridx = 0;
		addSectionConstraints.gridy = 2;
		addSectionConstraints.weightx = 0;
		addSectionPanel.add(new JLabel("No. Spots: "), addSectionConstraints);
		
		newSectionSpots = new JTextField();
		addSectionConstraints.gridx = 1;
		addSectionConstraints.weightx = 1;
		addSectionConstraints.gridwidth = GridBagConstraints.REMAINDER;
		addSectionPanel.add(newSectionSpots, addSectionConstraints);
		
		newSectionSubmit = new JButton("Add");
		addSectionConstraints.gridwidth = 3;
		addSectionConstraints.gridx = 0;
		addSectionConstraints.gridy = 3;
		addSectionConstraints.weightx = 0;
		addSectionPanel.add(newSectionSubmit, addSectionConstraints);
		
		editConstraints.gridx = 0;
		editConstraints.gridy = 1;
		editLotsPanel.add(addSectionPanel, editConstraints);
		
		activeLotsTable = new JTable(new Object[][]{{0, "Lot A", 5, 5}}, new Object[]{"ID", "Lot Name", "Sections", "Open Sections"});
		activeLotsTable.setFillsViewportHeight(true);
		editConstraints.anchor = GridBagConstraints.CENTER;
		editConstraints.fill = GridBagConstraints.BOTH;
		editConstraints.insets = tableInsets;
		editConstraints.ipadx = 0;
		editConstraints.weightx = 0.8;
		editConstraints.gridx = 1;
		editConstraints.gridy = 0;
		editLotsPanel.add(new JScrollPane(activeLotsTable), editConstraints);	
		
		lotSectionsTable = new JTable(new Object[][]{{0, "Section 1", 60, 60}}, new Object[]{"ID", "Section Name", "Total Spots", "Open Spots"});
		lotSectionsTable.setFillsViewportHeight(true);
		editConstraints.gridx = 1;
		editConstraints.gridy = 1;
		editLotsPanel.add(new JScrollPane(lotSectionsTable), editConstraints);
		
		//Show the ui
		window.setSize(800, 600);
		window.setMinimumSize(new Dimension(400, 400));
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);
		
	}
	
}
