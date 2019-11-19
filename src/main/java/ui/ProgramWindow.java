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

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
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
		window = new JFrame("Parking Program");
		
		//Create the tab pane and all the sub-panels
		tabPane = new JTabbedPane();
		editLotsPanel = new JPanel();
		tabPane.addTab("Edit Lots", editLotsPanel);
		customerPanel = new JPanel();
		tabPane.addTab("Customer Terminal", customerPanel);
		transactionsPanel = new JPanel();
		tabPane.addTab("Transaction Log", transactionsPanel);
		window.add(tabPane);
		
		tabPane.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				if(e.getSource() instanceof JTabbedPane) {
					if(tabPane.getSelectedIndex() == 2) {
						updateTransactionLogs();
					}
				}
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
		
		terminalSelectBox = new JComboBox<String>(new String[]{"Parking Lot A", "Parking Lot B", "..."});
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
		
		activeLotsTable.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
	        public void valueChanged(ListSelectionEvent event) {
	        	showSectionsForLot(activeLotsTable.getSelectedRow());
	        }
	    });
		
		
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
			addSectionToTableModel(sectionsModel, ticketManager.getLots().get(0).sections.get(i));
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
		//TODO: include the duration of each transaction? this would require updating the table periodically to
		//update the values, but would be a cool feature.
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
		activeModel.setColumnIdentifiers(new String[]{"Transaction ID", "Lot Used", "Date"});
		activeTransactions = new JTable();
		activeTransactions.setModel(activeModel);
		transactionConstraints.gridx = 0;
		transactionConstraints.gridy = 1;
		transactionConstraints.weighty = 1;
		transactionConstraints.insets = leftInsets;
		transactionConstraints.fill = GridBagConstraints.BOTH;
		transactionsPanel.add(new JScrollPane(activeTransactions), transactionConstraints);
		
		DefaultTableModel completedModel = new DefaultTableModel();
		completedModel.setColumnIdentifiers(new String[]{"Transaction ID", "Lot Used", "Check In", "Check Out", "Payment"});
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
			model.addRow(new Object[]{t.transactionId, t.lotUsed.lotName, t.timeEnteredDate});
		}
		
		//Clear the currently present data
		model = (DefaultTableModel) completedTransactions.getModel();
		model.setRowCount(0);
		
		//Add in data from completed transactions
		ArrayList<Transaction> completed = ticketManager.getCompletedTransactions();
		for(Transaction t : completed) {
			model.addRow(new Object[]{t.transactionId, t.lotUsed.lotName, t.timeEnteredDate, t.timeExitedDate, String.format("$%0.2f", t.getTotalCost())});
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
		int lotIndex = terminalSelectBox.getSelectedIndex();
		ParkingLot activeLot = ticketManager.getLots().get(lotIndex);
		
		Card card = activeLot.scanCard();
        boolean success = ticketManager.startTransaction(card, terminalSelectBox.getSelectedIndex());
        
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
		
		addLotToTableModel (lotsModel, newLot);
		
		newLotName.setText("");
		
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
		
		String sectionName = newSectionName.getText();
		int sectionSpots = Integer.parseInt(newSectionSpots.getText());
		
		if (sectionName.length() == 0) return;
		
		ParkingLot lotToShow;
		if (activeLotsTable.getSelectedRow() > 0)
			lotToShow = ticketManager.getLots().get(activeLotsTable.getSelectedRow());
		else
			lotToShow = ticketManager.getLots().get(0);
		
		LotSection newSection = new LotSection(sectionName, sectionSpots);
		lotToShow.addSection(newSection);
		
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
		
		ticketManager.removeLot(ticketManager.getLots().get(activeLotsTable.getSelectedRow()));
		
		clearTableModel(lotsModel);
		for (int i = 0; i < ticketManager.getNumLots(); i++) 
			addLotToTableModel(lotsModel, ticketManager.getLots().get(i));
		
		ParkingLot lotToShow;
		if (activeLotsTable.getSelectedRow() > 0)
			lotToShow = ticketManager.getLots().get(activeLotsTable.getSelectedRow());
		else if (ticketManager.getLots().size() > 0)
			lotToShow = ticketManager.getLots().get(0);
		else {
			clearTableModel(sectionsModel);
			return;
		}
		
		
		
		
		clearTableModel(sectionsModel);
		for (int i = 0; i < lotToShow.getNumSections(); i++) 
			addSectionToTableModel(sectionsModel, lotToShow.sections.get(i));
		
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
		
		
		
		ParkingLot lotToShow;
		int lotIndex = 0;
		
		if (activeLotsTable.getSelectedRow() > 0) {
			lotIndex = activeLotsTable.getSelectedRow();
			lotToShow = ticketManager.getLots().get(activeLotsTable.getSelectedRow());
		} else {
			lotToShow = ticketManager.getLots().get(0);
		}
		
		ticketManager.getLots().get(lotIndex).removeSection(lotToShow.sections.get(lotSectionsTable.getSelectedRow()));
		sectionsModel.removeRow(lotSectionsTable.getSelectedRow());
		
		clearTableModel(sectionsModel);
		for (int i = 0; i < lotToShow.getNumSections(); i++) 
			addSectionToTableModel(sectionsModel, lotToShow.sections.get(i));
		
	}
	
	private void showSectionsForLot(int id) {
		ParkingLot lotToShow;
		lotToShow = ticketManager.getLots().get(activeLotsTable.getSelectedRow());
		
		clearTableModel(sectionsModel);
		for (int i = 0; i < lotToShow.getNumSections(); i++) 
			addSectionToTableModel(sectionsModel, lotToShow.sections.get(i));
	}
	
	
	private void addLotToTableModel (DefaultTableModel model, ParkingLot lot) {
        Object rowData[] = new Object[5];
        
        
        String sectionArrayString = "", openSectionArrayString = "";
        for (int j = 0; j < lot.sections.size(); j++) {
        	sectionArrayString += lot.sections.get(j).getName() + (j < lot.getNumSections() - 1 ? ", " : "");
        }
        
        for (int j = 0; j < lot.sections.size(); j++) {
        	if (lot.sections.get(j).hasOpenSpots())
        		openSectionArrayString += lot.sections.get(j).getName() + (j < lot.getNumOpenSections() - 1 ? ", " : "");
        }
        	
        rowData[0] = lot.lotId;
        rowData[1] = lot.lotName;
        rowData[2] = sectionArrayString;
        rowData[3] = openSectionArrayString;
        rowData[4] = lot.hourlyRate;
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
	
}
