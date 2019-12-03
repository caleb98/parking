package ui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import data.Card;
import data.LotSection;
import data.Pair;
import data.ParkingLot;
import data.TicketManager;
import data.Transaction;

public class ProgramWindow {

	private JFrame window;
	private JTabbedPane tabPane;
	
	//Lot creation panel
	private JPanel editLotsPanel;
	
	private JPanel addLotPanel;
	private JTextField newLotName;
	private JButton newLotSubmit;
	private JButton removeLotButton;
	private JComboBox<String> terminalSelectBox;
	
	private JTable activeLotsTable;
	DefaultTableModel lotsModel;
	DefaultTableModel sectionsModel;
	
	private JPanel addSectionPanel;
	private JTextField newSectionName;
	private JTextField newSectionSpots;
	private JButton newSectionSubmit;
	private JButton removeSectionButton;
	private JTable lotSectionsTable;
	private ListSelectionListener viewSectionsListener;
	
	//Customer portal panel
	private JPanel customerPanel;
	private JButton enterLotButton;
	private JButton exitLotButton;
	
	//Transaction log panel
	private JPanel transactionsPanel;
	private JTable activeTransactions;
	private JTable completedTransactions;
	
	private TicketManager ticketManager;
	
	public ProgramWindow(TicketManager ticketManager) {
		this.ticketManager = ticketManager;
		
		//Create the window frame
		window = new JFrame("Parking Plus");
		
		//Create the tab pane and all the sub-panels
		tabPane = new JTabbedPane();
		customerPanel = new JPanel();
		tabPane.addTab("Customer Terminal", customerPanel);
		editLotsPanel = new JPanel();
		tabPane.addTab("Edit Lots", editLotsPanel);
		transactionsPanel = new JPanel();
		tabPane.addTab("Transaction Log", transactionsPanel);
		window.add(tabPane);

		// Saves data on program shutdown
		Runtime.getRuntime().addShutdownHook(new Thread() {
			public void run() {
				System.out.println("Saving and exiting...");
				ticketManager.saveData();
			}
		});
		
		//Build the lot editing panel
		editLotsPanel.setLayout(new GridBagLayout());
		GridBagConstraints editConstraints = new GridBagConstraints();
		editConstraints.anchor = GridBagConstraints.CENTER;
		editConstraints.weighty = 1;
		
		Insets addPanelInsets = new Insets(20, 10, 0, 10);
		Insets tableInsets = new Insets(0, 0, 0, 0);
		
		addLotPanel = new JPanel(new GridBagLayout());
		GridBagConstraints addLotConstraints = new GridBagConstraints();
		addLotConstraints.fill = GridBagConstraints.BOTH;
		
		JLabel addLotLabel = new JLabel("Lot Management");
		addLotLabel.setHorizontalAlignment(JLabel.CENTER);
		addLotConstraints.gridwidth = 3;
		addLotConstraints.gridx = 0;
		addLotConstraints.gridy = 0;
		addLotPanel.add(addLotLabel, addLotConstraints);
		
		addLotConstraints.gridwidth = 1;
		addLotConstraints.gridx = 0;
		addLotConstraints.gridy = 1;
		addLotPanel.add(new JLabel("Name: "), addLotConstraints);
		
		newLotName = new JTextField();
		addLotConstraints.gridx = 1;
		addLotConstraints.weightx = 1;
		addLotConstraints.gridwidth = GridBagConstraints.REMAINDER;
		addLotPanel.add(newLotName, addLotConstraints);
		
		addLotConstraints.gridwidth = 3;
		addLotConstraints.gridx = 0;
		addLotConstraints.gridy = 2;
		addLotConstraints.weightx = 0;
		newLotSubmit = new JButton("Add Lot");
		addLotPanel.add(newLotSubmit, addLotConstraints);
		newLotSubmit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addLotButtonPressed(e);
			}
		});
		
		removeLotButton = new JButton("Remove Selected Lot");
		addLotConstraints.insets = new Insets(25, 0, 0, 0);
		addLotConstraints.gridy = 3;
		addLotPanel.add(removeLotButton, addLotConstraints);
		removeLotButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				removeLotButtonPressed(e);
			}
		});
		
		JPanel terminalSelectPanel = new JPanel(new GridBagLayout());
		GridBagConstraints termConstraints = new GridBagConstraints();
		
		JLabel terminalSelectLabel = new JLabel("Select Terminal: ");
		termConstraints.gridx = 0;
		termConstraints.gridy = 0;
		terminalSelectPanel.add(terminalSelectLabel, termConstraints);
		
		terminalSelectBox = new JComboBox<String>();
		updateTerminalSelectBox();
		termConstraints.gridx = 1;
		termConstraints.weightx = 1;
		termConstraints.fill = GridBagConstraints.HORIZONTAL;
		terminalSelectPanel.add(terminalSelectBox, termConstraints);
		
		addLotConstraints.gridy = 4;
		addLotConstraints.gridx = 0;
		addLotConstraints.gridwidth = 3;
		addLotConstraints.fill = GridBagConstraints.HORIZONTAL;
		addLotPanel.add(terminalSelectPanel, addLotConstraints);
		
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
		
		JLabel addSectionLabel = new JLabel("Section Management");
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
		
		newSectionSubmit = new JButton("Add Section");
		addSectionConstraints.gridwidth = 3;
		addSectionConstraints.gridx = 0;
		addSectionConstraints.gridy = 3;
		addSectionConstraints.weightx = 0;
		addSectionPanel.add(newSectionSubmit, addSectionConstraints);
		newSectionSubmit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addSectionButtonPressed(e);
			}
		});
		
		removeSectionButton = new JButton("Remove Selected Section");
		addSectionConstraints.gridy = 4;
		addSectionConstraints.insets = new Insets(25, 0, 0, 0);
		addSectionPanel.add(removeSectionButton, addSectionConstraints);
		removeSectionButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				removeLotSectionButtonPressed(e);
			}
		});
		
		editConstraints.gridx = 0;
		editConstraints.gridy = 1;
		editLotsPanel.add(addSectionPanel, editConstraints);
		
		activeLotsTable = new JTable();
		lotsModel = new DefaultTableModel();
		lotsModel.setColumnIdentifiers(new String[] {"ID", "Lot Name", "Sections", "Open Sections", "Hourly Rate"});
		
		for(int i = 0; i < ticketManager.getNumLots(); i++) {
			addLotToTableModel(lotsModel, ticketManager.getLots().get(i));
		}
		
		activeLotsTable.setModel(lotsModel);
		
		viewSectionsListener = new ListSelectionListener() {
	        public void valueChanged(ListSelectionEvent event) {
	        	showSectionsForLot(activeLotsTable.getSelectedRow());
	        }
		};
		activeLotsTable.getSelectionModel().addListSelectionListener(viewSectionsListener);
		
		activeLotsTable.setFillsViewportHeight(true);
		editConstraints.anchor = GridBagConstraints.CENTER;
		editConstraints.fill = GridBagConstraints.BOTH;
		editConstraints.insets = tableInsets;
		editConstraints.ipadx = 0;
		editConstraints.weightx = 0.8;
		editConstraints.gridx = 1;
		editConstraints.gridy = 0;
		editLotsPanel.add(new JScrollPane(activeLotsTable), editConstraints);
		
		lotSectionsTable = new JTable();
		sectionsModel = new DefaultTableModel();
		sectionsModel.setColumnIdentifiers(new String[] {"ID", "Section Name", "Total Spots", "Open Spots"});
		
		for (int i = 0; i < ticketManager.getLots().get(0).getNumSections(); i++) {
			addSectionToTableModel(sectionsModel, ticketManager.getLots().get(0).getAllSections().get(i));
		}
		
		lotSectionsTable.setModel(sectionsModel);
		
		lotSectionsTable.setFillsViewportHeight(true);
		editConstraints.gridx = 1;
		editConstraints.gridy = 1;
		editLotsPanel.add(new JScrollPane(lotSectionsTable), editConstraints);
		
		//Build the customer portal pane
		customerPanel.setLayout(new GridLayout(0, 2));
		
		enterLotButton = new JButton("<html><p style=\"font-size:50pt;\">Enter Lot</p></html>");
		customerPanel.add(enterLotButton);
		enterLotButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				enterLotButtonPressed(e);
			}
		});
		
		exitLotButton = new JButton("<html><p style=\"font-size:50pt;\">Exit Lot</p></html>");
		customerPanel.add(exitLotButton);
		exitLotButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				exitLotButtonPressed(e);
			}
		});
		
		//Build the transaction log pane
		transactionsPanel.setLayout(new GridBagLayout());
		GridBagConstraints transactionConstraints = new GridBagConstraints();
		
		Insets leftInsets = new Insets(0, 0, 0, 5);
		Insets rightInsets = new Insets(0, 5, 0, 0);
		
		JLabel activeTransactionsLabel = new JLabel("Active Transactions");
		activeTransactionsLabel.setHorizontalAlignment(JLabel.CENTER);
		transactionConstraints.gridx = 0;
		transactionConstraints.gridy = 0;
		transactionConstraints.weightx = 1;
		transactionConstraints.insets = leftInsets;
		transactionConstraints.fill = GridBagConstraints.HORIZONTAL;
		transactionsPanel.add(activeTransactionsLabel, transactionConstraints);
		
		JLabel completedTransactionsLabel = new JLabel("Completed Transactions");
		completedTransactionsLabel.setHorizontalAlignment(JLabel.CENTER);
		transactionConstraints.gridx = 1;
		transactionConstraints.gridy = 0;
		transactionConstraints.insets = rightInsets;
		transactionsPanel.add(completedTransactionsLabel, transactionConstraints);
		
		DefaultTableModel activeModel = new DefaultTableModel();
		activeModel.setColumnIdentifiers(new String[]{"Transaction ID", "Lot Used", "Section Used", "Check In"});
		activeTransactions = new JTable();
		activeTransactions.setModel(activeModel);
		transactionConstraints.gridx = 0;
		transactionConstraints.gridy = 1;
		transactionConstraints.weighty = 1;
		transactionConstraints.insets = leftInsets;
		transactionConstraints.fill = GridBagConstraints.BOTH;
		transactionsPanel.add(new JScrollPane(activeTransactions), transactionConstraints);
		
		DefaultTableModel completedModel = new DefaultTableModel();
		completedModel.setColumnIdentifiers(new String[]{"Transaction ID", "Lot Used", "Section Used", "Check In", "Check Out", "Payment"});
		completedTransactions = new JTable();
		completedTransactions.setModel(completedModel);
		transactionConstraints.gridx = 1;
		transactionConstraints.gridy = 1;
		transactionConstraints.insets = rightInsets;
		transactionsPanel.add(new JScrollPane(completedTransactions), transactionConstraints);
		
		//Show the ui
		window.setSize(800, 600);
		window.setMinimumSize(new Dimension(400, 400));
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);
		
	}

	/**
	 * This method should be called whenever the transaction log
	 * tab is selected. It will update the transaction log tables
	 * so that they accurately reflect the current transactions'
	 * statuses.
	 */
	private void updateTransactionLogs() {
		
		//Clear the currently present data
		DefaultTableModel model = (DefaultTableModel) activeTransactions.getModel();
		model.setRowCount(0);
		
		//Add in data from active transactions
		HashMap<Integer, Pair<Transaction, Card>> actives = ticketManager.getOutstandingTransactions();
		for(Integer i : actives.keySet()) {
			Transaction t = actives.get(i).a;
			model.addRow(new Object[]{t.transactionId, t.lotUsed.getLotName(), t.sectionUsed.getName(), t.timeEnteredDate});
		}
		
		//Clear the currently present data
		model = (DefaultTableModel) completedTransactions.getModel();
		model.setRowCount(0);
		
		//Add in data from completed transactions
		ArrayList<Transaction> completed = ticketManager.getCompletedTransactions();
		for(Transaction t : completed) {
			model.addRow(new Object[]{
					t.transactionId, 
					t.lotUsed.getLotName(), 
					t.sectionUsed.getName(), 
					t.timeEnteredDate, 
					t.getTimeExitedDate(), 
					String.format("$%.2f", t.getTotalCost())
			});
		}
		
	}
	
	/**
	 * This method is called whenever the "Enter Lot" button on the
	 * customer portal tab is clicked. It should begin the process
	 * of simulating entry into the parking lot. This includes getting
	 * the customer's card information, and finding an open lot/lot section
	 * where the customer can park. If there are no free spots, a message
	 * should be displayed as appropriate.
	 * @param e
	 */
	private void enterLotButtonPressed(ActionEvent event) {
		
		//Make sure that there is a lot available
		if(ticketManager.getNumLots() < 1) {
			JOptionPane.showMessageDialog(
					window,
					"No active terminals. Please contact parking lot manager.",
					"Error",
					JOptionPane.ERROR_MESSAGE
			);
			return;
		}
		
		int lotIndex = terminalSelectBox.getSelectedIndex();
		ParkingLot activeLot = ticketManager.getLots().get(lotIndex);
		
		Card card = activeLot.scanCard();
        boolean success = ticketManager.startTransaction(card, lotIndex);
        
        if(success){
        	(new Thread(()->{
                activeLot.setGateOpen();
                try{
                    Thread.sleep(5000);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
                activeLot.setGateClosed();
        	})).start();
        }else{
            System.out.println("An open spot could not be found!");
            JOptionPane.showMessageDialog(
            		window, 
            		"An open spot could not be found.", 
            		"Lot Full", 
            		JOptionPane.ERROR_MESSAGE
            );
        }
        
        refreshLotSectionTable(activeLot);
        
	}
	
	/**
	 * This method is called whenever the "Exit Lot" button on the
	 * customer portal tab is clicked. It should begin the process of
	 * simulating exit from the parking lot. This should include scanning
	 * the parking ticket and getting the transaction ID, charging the
	 * payment card, and printing the receipt.
	 * @param e
	 */
	private void exitLotButtonPressed(ActionEvent event) {
		
		//Make sure that there is a lot available
		if(ticketManager.getNumLots() < 1) {
			JOptionPane.showMessageDialog(
					window,
					"No active terminals. Please contact parking lot manager.",
					"Error",
					JOptionPane.ERROR_MESSAGE
			);
			return;
		}
		
		int lotIndex = terminalSelectBox.getSelectedIndex();
		ParkingLot activeLot = ticketManager.getLots().get(lotIndex);
		
		int ticketId = activeLot.scanTicket();
        boolean tranasctionComplete = ticketManager.completeTransaction(ticketId);
        if(tranasctionComplete) {
            System.out.println("Transaction has been completed");
            JOptionPane.showMessageDialog(
            		window, 
            		"Transaction completed successfully.", 
            		"Transaction Complete",
            		JOptionPane.INFORMATION_MESSAGE
            );
        }
        else {
            System.out.println("Transaction failed, check ticked id");
            JOptionPane.showMessageDialog(
            		window,
            		"Transaction failed, check ticket ID.", 
            		"Transaction Failed",
            		JOptionPane.ERROR_MESSAGE
            );
        }
        
        refreshLotSectionTable(activeLot);
        
	}
	
	/**
	 * This method is called whenever the "Add Lot" button on the 
	 * lot editing tab is clicked. It should create a new lot with no
	 * sections, add it to the list of lots, and update the active
	 * lots display to include the new lot.
	 * @param e
	 */
	private void addLotButtonPressed(ActionEvent e) {
		
		String lotName = newLotName.getText();
		if (lotName.length() == 0) return;
		
		int lotId = ticketManager.getNumLots();
		
		ParkingLot newLot = new ParkingLot(lotId, lotName);
		
		ticketManager.addLot(newLot);
		
		addLotToTableModel(lotsModel, newLot);
		
		newLotName.setText("");
		
		updateTerminalSelectBox();
		
	}
	
	/**
	 * This method is called whenever the "Add Section" button on the
	 * lot editing tab is clicked. It should create a new section for the
	 * currently selected lot (or show an error message in the event that
	 * there are not active lots or no lot is selected.)
	 * The lot section table should also be updated to include the new lot
	 * section.
	 * @param e
	 */
	private void addSectionButtonPressed(ActionEvent e) {
		
		//Make sure that there are lots to which we can add sections
		if(ticketManager.getNumLots() < 1) {
			JOptionPane.showMessageDialog(
					window,
					"Please add a lot before adding sections.",
					"Error",
					JOptionPane.INFORMATION_MESSAGE
			);
			return;
		}
		
		String sectionName = newSectionName.getText();
		int sectionSpots;
		try {
			sectionSpots = Integer.parseInt(newSectionSpots.getText());
		} catch (NumberFormatException nfe) {
			JOptionPane.showMessageDialog(
					window,
					"Please enter a positive whole number for the number of spots in the section.",
					"Error",
					JOptionPane.INFORMATION_MESSAGE
			);
			return;
		}
		
		if (sectionName.length() == 0) return;
		
		int selectedRow = activeLotsTable.getSelectedRow() == -1 ? 0 : activeLotsTable.getSelectedRow();
		ParkingLot lotToShow = ticketManager.getLots().get(selectedRow);
		
		LotSection newSection = new LotSection(sectionName, sectionSpots);
		lotToShow.addSection(newSection);
		updateLotSectionInParkingLotTable(selectedRow);
		
		addSectionToTableModel(sectionsModel, newSection);
		
		newSectionName.setText("");
		newSectionSpots.setText("");
		
	}
	
	/**
	 * This method is called whenever the "Remove Selected Lot" button
	 * on the lot editing tab is clicked. It should remove the currently
	 * selected lot in the active lot table (or show a messasge if there
	 * are not active lots or no lot is selected.)
	 * The active lot table should be updated to reflect the removal of 
	 * the lot, and the lot section table should be updated so that it 
	 * is not showing sections for the deleted lot.
	 * @param e
	 */
	private void removeLotButtonPressed(ActionEvent e) {
		
		//Get the select index and make sure that it is not -1 (no row selected)
		int removeIndex = activeLotsTable.getSelectedRow();
		if(removeIndex == -1) {
			JOptionPane.showMessageDialog(
					window,
					"Please select a lot to remove.",
					"Error",
					JOptionPane.INFORMATION_MESSAGE
			);
			return;
		}
		
		//Make sure that no cars are parked in this lot
		for(LotSection section : ticketManager.getLots().get(removeIndex).getAllSections()) {
			if(section.getOpenSpots() != section.getTotalSpots()) {
				JOptionPane.showMessageDialog(
						window,
						"Unable to remove lot containing parked cars.\n"
						+ "Wait until all cars have exited to remove.",
						"Error",
						JOptionPane.INFORMATION_MESSAGE
				);
			}
		}
		
		ticketManager.removeLot(ticketManager.getLots().get(removeIndex));
		
		//Clear the table
		activeLotsTable.getSelectionModel().removeListSelectionListener(viewSectionsListener);
		clearTableModel(lotsModel);
		
		//Reload entries
		for (int i = 0; i < ticketManager.getLots().size(); i++) {
			addLotToTableModel(lotsModel, ticketManager.getLots().get(i));
		}
		
		ParkingLot lotToShow;
		if (ticketManager.getLots().size() > 0)
			lotToShow = ticketManager.getLots().get(0);
		else {
			clearTableModel(sectionsModel);
			activeLotsTable.getSelectionModel().addListSelectionListener(viewSectionsListener);
			updateTerminalSelectBox();
			return;
		}
		
		refreshLotSectionTable(lotToShow);
		updateTerminalSelectBox();
	}
	
	private void refreshLotSectionTable(ParkingLot lotToShow) {
		clearTableModel(sectionsModel);
		for (int i = 0; i < lotToShow.getNumSections(); i++) {
			addSectionToTableModel(sectionsModel, lotToShow.getAllSections().get(i));
		}
		activeLotsTable.getSelectionModel().addListSelectionListener(viewSectionsListener);
	}
	
	/**
	 * This method is called whenever the "Remove Selected Section" button
	 * on the lot editing tab is clicked. It should remove the currently 
	 * selected lot section in the lot section table from the current lot's
	 * sections. If the lot has no sections or no section is selected, a
	 * message should be displayed for the user.
	 * The lot sections table should be updated to reflect the removal
	 * of the lot section.
	 * @param e
	 */
	private void removeLotSectionButtonPressed(ActionEvent e) {
		
		//Make sure there is at least 1 existing lot
		if(ticketManager.getLots().size() == 0) {
			JOptionPane.showMessageDialog(
					window,
					"Please add at least one lot to manage sections.",
					"Error",
					JOptionPane.INFORMATION_MESSAGE
			);
			return;
		}
		
		ParkingLot lotToShow;
		int lotIndex = 0;
		if (activeLotsTable.getSelectedRow() > 0) {
			lotIndex = activeLotsTable.getSelectedRow();
			lotToShow = ticketManager.getLots().get(activeLotsTable.getSelectedRow());
		} else {
			lotIndex = 0;
			lotToShow = ticketManager.getLots().get(0);
		}
		
		//Get the select index and make sure that it is not -1 (no row selected)
		int removeIndex = lotSectionsTable.getSelectedRow(); 
		if(removeIndex == -1) {
			JOptionPane.showMessageDialog(
					window,
					"Please select a section to remove.",
					"Error",
					JOptionPane.INFORMATION_MESSAGE
			);
			return;
		}
		
		//Make sure that the selected lot doesn't have any parked cars
		LotSection removeSection = lotToShow.getAllSections().get(removeIndex);
		if(removeSection.getOpenSpots() != removeSection.getTotalSpots()) {
			JOptionPane.showMessageDialog(
					window,
					"Unable to remove section containing parked cars.\n"
					+ "Please wait until all cars have exited the lot section to remove.",
					"Error",
					JOptionPane.INFORMATION_MESSAGE
			);
			return;
		}
		
		lotToShow.removeSection(lotToShow.getAllSections().get(removeIndex));
		sectionsModel.removeRow(removeIndex);
		updateLotSectionInParkingLotTable(lotIndex);
		
		clearTableModel(sectionsModel);
		for (int i = 0; i < lotToShow.getNumSections(); i++) {
			addSectionToTableModel(sectionsModel, lotToShow.getAllSections().get(i));
		}
		
	}
	
	private void updateLotSectionInParkingLotTable(int id) {
		
		ParkingLot lot = ticketManager.getLots().get(id);
        String sectionArrayString = "", openSectionArrayString = "";
        for (int j = 0; j < lot.getAllSections().size(); j++) {
        	sectionArrayString += lot.getAllSections().get(j).getName() + (j < lot.getNumSections() - 1 ? ", " : "");
        }
        
        for (int j = 0; j < lot.getAllSections().size(); j++) {
        	if (lot.getAllSections().get(j).hasOpenSpots())
        		openSectionArrayString += lot.getAllSections().get(j).getName() + (j < lot.getNumOpenSections() - 1 ? ", " : "");
        }
		
		activeLotsTable.getModel().setValueAt(sectionArrayString, id, 2);
		activeLotsTable.getModel().setValueAt(openSectionArrayString, id, 3);
		
	}
	
	private void showSectionsForLot(int id) {
		
		int showIndex = activeLotsTable.getSelectedRow() == -1 ? 0 : activeLotsTable.getSelectedRow();
		ParkingLot lotToShow = ticketManager.getLots().get(showIndex);
		
		clearTableModel(sectionsModel);
		for (int i = 0; i < lotToShow.getNumSections(); i++) {
			addSectionToTableModel(sectionsModel, lotToShow.getAllSections().get(i));
		}
		
	}
	
	private void addLotToTableModel(DefaultTableModel model, ParkingLot lot) {
		
        Object rowData[] = new Object[5];
        
        String sectionArrayString = "", openSectionArrayString = "";
        for (int j = 0; j < lot.getAllSections().size(); j++) {
        	sectionArrayString += lot.getAllSections().get(j).getName() + (j < lot.getNumSections() - 1 ? ", " : "");
        }
        
        for (int j = 0; j < lot.getAllSections().size(); j++) {
        	if (lot.getAllSections().get(j).hasOpenSpots())
        		openSectionArrayString += lot.getAllSections().get(j).getName() + (j < lot.getNumOpenSections() - 1 ? ", " : "");
        }
        	
        rowData[0] = lot.getLotId();
        rowData[1] = lot.getLotName();
        rowData[2] = sectionArrayString;
        rowData[3] = openSectionArrayString;
        rowData[4] = lot.getHourlyRate();
        model.addRow(rowData);
        
    }
	
	private void addSectionToTableModel(DefaultTableModel model, LotSection section) {
		Object rowData[] = new Object[4];
        
        rowData[0] = section.getId();
        rowData[1] = section.getName();
        rowData[2] = section.getTotalSpots();
        rowData[3] = section.getOpenSpots();
        model.addRow(rowData);
	}
	
	private void clearTableModel(DefaultTableModel model) {
			model.setRowCount(0);
	}
	
	private void updateTerminalSelectBox() {
		//Check for no lots
		if(ticketManager.getNumLots() == 0) {
			terminalSelectBox.setModel(new DefaultComboBoxModel<String>(new String[]{"No terminals available."}));
			return;
		}
		
		String[] terminals = new String[ticketManager.getNumLots()];
		for(int i = 0; i < terminals.length; ++i) {
			terminals[i] = ticketManager.getLots().get(i).getLotName();
		}
		terminalSelectBox.setModel(new DefaultComboBoxModel<String>(terminals));
	}

	
	
}
