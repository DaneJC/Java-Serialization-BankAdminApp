/**
* Class: B.Sc. in Cloud Computing
* Instructor: Maria Boyle
* Description:  
* 	CurrentSerializerTesterGUI class used to to test serialise and deserialise for persistent BankAccount objects with the CurrentSerialiser class, 
* 	a component of a banking system which models The Bank of Ireland System.
* Date: 26/10/2019
* @author Dane Campbell - L00142041
* @version 1.0
*/

package ie.lyit.serialize;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import javax.swing.UIManager;

import ie.lyit.bank.CurrentAccount;
import ie.lyit.bank.Date;
import ie.lyit.bank.Name;

import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JScrollPane;

import java.util.regex.*; 

public class CurrentSerializerTesterGUI {

	// GUI related
	private JFrame frame;
	private JTextField inputField;
	private JTextArea displayField;
	JButton btnCancel, btnClear, btnEnter;
	
	// functional/session related
	private boolean createAccSession, viewAccSession, editAccSession, deleteAccSession, subInputRequest,detailEditSuccess;
	private int currentInputRequest, userSelection;
	private final String [] CREATE_ACC_INPUT_REQUESTS = {"TITLE [ Mr | Ms | Mrs ]","FORENAME","SURNAME","ADDRESS","BALANCE","OVERDRAFT","OPEN DATE [ dd/mm/yyyy ]"};
	private String editEntryData;
	private String [] createAccInputData, editAccInputData;
	
	// back-end related
	private CurrentSerializer cs = new CurrentSerializer();
	
	CurrentAccount account; // *** to hold account while editing ***

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CurrentSerializerTesterGUI window = new CurrentSerializerTesterGUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public CurrentSerializerTesterGUI() {
		// GUI
		initialize();
		
		// functional/sessions
		currentInputRequest = 0;
		deActivateSessions();
		createAccInputData = new String [7]; // 0:"Title" | 1:"Forename" | 2:"Surname" | 3:"Address" | 4:"Balance" | 5:"Overdraft" | 6:"Date"
		
		// back-end
		cs.deserialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				cs.serialize();
			}
		});
		frame.setBounds(100, 100, 592, 542);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.GRAY);
		panel.setBounds(0, 0, 587, 513);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		displayField = new JTextArea();
		displayField.setWrapStyleWord(true);
		displayField.setLineWrap(true);
		displayField.setFont(new Font("Consolas", Font.PLAIN, 13));
		displayField.setForeground(new Color(154, 205, 50));
		displayField.setBackground(Color.DARK_GRAY);
		displayField.setBounds(110, 63, 366, 337);
		setTextAreaDefault();
		panel.add(displayField);
		
		JButton btnCreate = new JButton("CREATE");
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				createAccount();
				activateInputPanel();
			}
		});
		btnCreate.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnCreate.setForeground(new Color(0, 128, 128));
		btnCreate.setBackground(new Color(255, 255, 255));
		btnCreate.setBounds(10, 125, 89, 40);
		btnCreate.setFocusable(false);
		panel.add(btnCreate);
		
		JButton btnView = new JButton("VIEW");
		btnView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				viewAccount();
			}
		});
		btnView.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnView.setForeground(new Color(105, 105, 105));
		btnView.setBackground(new Color(255, 255, 255));
		btnView.setBounds(10, 294, 89, 40);
		btnView.setFocusable(false);
		panel.add(btnView);
		
		JButton btnEdit = new JButton("EDIT");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editAccount();
			}
		});
		btnEdit.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnEdit.setForeground(new Color(105, 105, 105));
		btnEdit.setBackground(new Color(255, 255, 255));
		btnEdit.setBounds(485, 294, 89, 40);
		btnEdit.setFocusable(false);
		panel.add(btnEdit);
		
		JButton btnDelete = new JButton("DELETE");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteAccount();
			}
		});
		btnDelete.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnDelete.setForeground(new Color(178, 34, 34));
		btnDelete.setBackground(new Color(255, 255, 255));
		btnDelete.setBounds(485, 125, 89, 40);
		btnDelete.setFocusable(false);
		panel.add(btnDelete);
		
		JButton btnListAll = new JButton("LIST ALL ACCOUNTS");
		btnListAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deActivateSessions();
				deActivateInputPanel();
				listAll();
			}
		});
		btnListAll.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnListAll.setForeground(new Color(105, 105, 105));
		btnListAll.setBackground(new Color(255, 255, 255));
		btnListAll.setBounds(149, 11, 283, 40);
		btnListAll.setFocusable(false);
		panel.add(btnListAll);
		
		inputField = new JTextField();
		inputField.setEnabled(false);
		inputField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				enterUserInputDetails();
			}
		});
		inputField.setForeground(Color.GREEN);
		inputField.setBackground(Color.DARK_GRAY);
		inputField.setBounds(149, 424, 283, 20);
		panel.add(inputField);
		inputField.setColumns(10);
		
		btnEnter = new JButton("ENTER");
		btnEnter.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnEnter.setEnabled(false);
		btnEnter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				enterUserInputDetails();
			}
		});
		btnEnter.setForeground(new Color(0, 139, 139));
		btnEnter.setBackground(new Color(255, 255, 255));
		btnEnter.setBounds(341, 455, 81, 40);
		btnEnter.setFocusable(false);
		panel.add(btnEnter);
		
		btnClear = new JButton("CLEAR");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inputField.setText("");
			}
		});
		btnClear.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnClear.setEnabled(false);
		btnClear.setForeground(new Color(255, 140, 0));
		btnClear.setBackground(new Color(255, 255, 255));
		btnClear.setBounds(250, 455, 81, 40);
		btnClear.setFocusable(false);
		panel.add(btnClear);
		
		btnCancel = new JButton("CANCEL");
		btnCancel.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnCancel.setEnabled(false);
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setTextAreaDefault();
				deActivateInputPanel();
				currentInputRequest = 0;
				inputField.setText("");
			}
		});
		btnCancel.setBackground(new Color(255, 255, 255));
		btnCancel.setForeground(new Color(220, 20, 60));
		btnCancel.setBounds(159, 455, 81, 40);
		btnCancel.setFocusable(false);
		panel.add(btnCancel);
		
		JScrollPane scrollPane = new JScrollPane(displayField);
		scrollPane.setBounds(109, 62, 366, 337);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		panel.add(scrollPane);
	}
	
	/* ===== OPERATION/SESSION ACTIVATIONS & DEACTIVATIONS =====*/
	// methods used to handle flow and input data regarding current operation.session
	/**
	 * Deactivate all active operations/sessions
	 */
	private void deActivateSessions() {
		createAccSession = false;
		viewAccSession = false;
		editAccSession = false;
		deleteAccSession = false;
	}
	
	// self explanatory ...
	private void activateCreateAccSession() {
		createAccSession = true;
	}
	// ...
	private void activateViewAccSession() {
		viewAccSession = true;
	}
	// ...
	private void activateEditAccSession() {
		editAccSession = true;
	}
    // ...
	private void activateDeleteAccSession() {
		deleteAccSession = true;
	}
	/**
	 * Activate user input panel to obtain user input data and give focus to inputField
	 */
	private void activateInputPanel() {
		btnCancel.setEnabled(true);
		btnClear.setEnabled(true);
		btnEnter.setEnabled(true);
		inputField.setEnabled(true);
		inputField.requestFocusInWindow();
	}
	/**
	 * Deactivate user input panel to prevent undesired user input data
	 */
	private void deActivateInputPanel() {
		btnCancel.setEnabled(false);
		btnClear.setEnabled(false);
		btnEnter.setEnabled(false);
		inputField.setEnabled(false);
	}
	// ...
	private void setTextAreaDefault() {
		displayField.setText("WELCOME FROM BOI! SELECT AN ACTIVITY.");
	}
	
	/**
	 * prepares create account operation/session flow/logic 
	 */
	private void createAccount() {
		
		currentInputRequest = 0;
		deActivateSessions();
		activateCreateAccSession();
		
		displayField.setText("CREATE ACCOUNT:\n");
		displayField.append("ENTER "+CREATE_ACC_INPUT_REQUESTS[currentInputRequest]+": ");
	}
	
	/**
	 * prepares view account operation/session flow/logic 
	 */
	private void viewAccount() {
		deActivateSessions();
		activateViewAccSession();
		activateInputPanel();
		displayField.setText("================= VIEW ACCOUNT ==================\n");
		displayField.append("ENTER ACCOUNT NO: ");
	}
	
	/**
	 * prepares edit account operation/session flow/logic 
	 */
	private void editAccount() {
		account = null;
		currentInputRequest = 0;
		subInputRequest = false;
		detailEditSuccess = false;
		userSelection = -1;
		deActivateSessions();
		activateEditAccSession();
		activateInputPanel();
		displayField.setText("=================================================\n");
		displayField.append("                   EDIT ACCOUNT                  \n");
		displayField.append("-------------------------------------------------\n");
		displayField.append("ENTER ACCOUNT NO: ");
	}
	
	/**
	 * prepares delete account operation/session flow/logic 
	 */
	private void deleteAccount() {
		deActivateSessions();
		activateDeleteAccSession();
		activateInputPanel();
		displayField.setText("================= DELETE ACCOUNT ================\n");
		displayField.append("ENTER ACCOUNT NO: ");
	}
	
	/**
	 * handles user input data and flow/logic regarding current operation/session i.e. create, edit, view or remove account operations/sessions.
	 */
	@SuppressWarnings({ "null", "unused" })
	private void enterUserInputDetails() {
		
		// if create account session active
		if(createAccSession) {
			// if create account phase(s) remain
			if(currentInputRequest < CREATE_ACC_INPUT_REQUESTS.length) {
				boolean inputValid, snooze;
				// do while user input date is not valid
				do{
					String input = ""; // hold user input data
					inputValid = false; // flag used to advance to next input request if current input request is valid 
					snooze = false; // used to prevent infinite loop if invalid user input
					
					// get input data and store in relevant location of createAccountDetails array
					if(currentInputRequest == 3) // if input data is address: sanitise address
						input = sanitiseAddress(inputField.getText()); 
					else
						input = sanitise(inputField.getText());
					
					// validate each input obtained according to current input request
					switch(currentInputRequest) {
					/*
					 *  INPUTS REQUESTS - 0:"Title" | 1:"Forename" | 2:"Surname" | 3:"Address" | 4:"Balance" | 5:"Overdraft" | 6:"Date" 
					 */
					// title, forename and surname validation 
					case 0:  	
						if(validTitle(input)) 
							inputValid = true;
						break;
					case 1: case 2: 	
						if(validNameElement(input)) 
							inputValid = true;
						break;	
					// balance and overdraft validation
					case 4: case 5: 
						if(validMonetaryElement(input)) 
							inputValid = true;
						break;
					// date format validation
					case 6: 
						if(validDateElement(input)) 
							inputValid = true;
						break;
					// address validation not required
					default: 
						inputValid = true;
						break;
					}
					// if input data is valid
					if(inputValid) {
						// store in relevant location of createAccountDetails array
						createAccInputData[currentInputRequest] = input;
						// append the input data to the input request
						displayField.append(createAccInputData[currentInputRequest]+"\n");
						// increment to next input request
						currentInputRequest ++;
						// if create account input requests 
						if(currentInputRequest < CREATE_ACC_INPUT_REQUESTS.length) 
							// remain display next input request
							displayField.append("ENTER "+CREATE_ACC_INPUT_REQUESTS[currentInputRequest]+": ");
						// if no create account input requests remain 
						else {
							// deactivate sessions
							deActivateSessions();
							// deactivate input panel
							deActivateInputPanel();
						}
					}else { // if input data not valid
						// notify user of error
						displayField.append("*************************************************");
						displayField.append("\nINVALID INPUT FOR "+CREATE_ACC_INPUT_REQUESTS[currentInputRequest]+"  => "+input+"\n");
						displayField.append("*************************************************");
						displayField.append("ENTER "+CREATE_ACC_INPUT_REQUESTS[currentInputRequest]+": ");
						// snooze to prevent infinite loop
						snooze = true;
					}
				}while(!inputValid && !snooze);
			}
			
			// if all create account input request have been obtained
			if (currentInputRequest >= CREATE_ACC_INPUT_REQUESTS.length) {
				
				// format open date for account creation
				int d,m,y;
				String [] s = createAccInputData[6].split("[/]+");
				d = Integer.valueOf(s[0]);
				m = Integer.valueOf(s[1]);
				y = Integer.valueOf(s[2]);
				
				try {
					// create/add new user
					String accountDetails = cs.add(new Name(createAccInputData[0], createAccInputData[1], createAccInputData[2]),
						createAccInputData[3],
						Double.valueOf(createAccInputData[4]),
						Double.valueOf(createAccInputData[5]),
						new Date(d,m,y)
					);
					
					// notify user input request complete
					displayField.append("=================================================");
					displayField.append("ACCOUNTCREATED FOR "+createAccInputData[0]+" "+createAccInputData[1]+" "+createAccInputData[2]+"\n");
					displayField.append("- - - - - - - - - - - - - - - - - - - - - - - - -");
					displayField.append(accountDetails);
					
					// serialise/backup accounts
					cs.serialize();
					
				}catch (Exception e){
					// notify user of error while creating account with prompt to retry
					displayField.setText("*************************************************");
					displayField.append("\nERROR CREATING ACCOUNT! PLEASE TRY AGAIN!\n");
					displayField.append("*************************************************");
				}
			}
		}
		// if view account session active
		else if(viewAccSession) {
			viewOrDelete("view");
		}
		// if edit account session active
		else if(editAccSession) {
			/*
			 *  INPUTS REQUESTS - 0:"ACCOUNT NO RETIEVAL" | 1:"MENU SELECTION" | 2:"NEW DETAIL ENTRY"
			 */
			int accNo;
			String accNoEntered ="";
			String [] options = {"DONE", "TITLE [Mr | Ms | Mrs]","SURNAME","ADDRESS","BALANCE","OVERDRAFT"};
			
			if(currentInputRequest == 0) { // INPUT REQUEST 0: "ACCOUNT NO RETIEVAL"
				try{
					// get user input data
					accNoEntered = inputField.getText();
					
					// confirm integer format
					accNo = Integer.valueOf(accNoEntered);
					
					// append the input data to the input request
					displayField.append(accNoEntered+"\n");
					displayField.append("-------------------------------------------------");
					
					// obtain associated account for editing
					account = cs.editGetAccount(accNo);
					
					// if account does not exist
					if(account == null) {
						// notify user
						displayField.append("\nACCOUNT "+accNoEntered+" DOES NOT EXIST!");
						// deactivate session
						deActivateSessions();
						// deactivate input panel
						deActivateInputPanel();
					}
					else {// if account does exist
						// increment to next input request 
						displayField.append(""+account.toString());
						displayField.append("\n-------------------------------------------------");
						displayField.append("\nEDIT DETAILS: \n 1-TITLE \n 2-SURNAME \n 3-ADDRESS \n 4-BALANCE \n 5-OVERDRAFT \n 0-DONE");
						currentInputRequest = 1;
					}
					
				}catch(Exception e) {
					// if error thrown from invalid integer account number format notify user
					displayField.append("\n*************************************************");
					displayField.append("\nINVALID ACCOUNT NO => "+accNoEntered+"\n");
					displayField.append("*************************************************");
					displayField.append("ENTER ACCOUNT NO [NUMERIC >= 100000]: ");
				}
			}
			else if (currentInputRequest == 1){ // INPUT REQUEST 1: "MENU SELECTION"
				
				String inputData = inputField.getText();
				if(Pattern.matches("[0-5]", inputData)) {
					currentInputRequest = 2;
					userSelection = Integer.valueOf(inputData);
					
				}
				else {
					displayField.append("*************************************************");
					displayField.append("\nINVALID OPTION => "+inputData+"\n");
					displayField.append("*************************************************");
					displayField.append("\nEDIT DETAILS: \n 1-TITLE \n 2-SURNAME \n 3-ADDRESS \n 4-BALANCE \n 5-OVERDRAFT \n 0-DONE");
				}	
			}
			if (currentInputRequest == 2){ // INPUT REQUEST 2: "NEW DETAIL ENTRY"
				
				if(!subInputRequest) {
				
					switch(userSelection) { // prompt for entry of selected detail for editing
					// 1-TITLE
					case 1: case 2: case 3: case 4: case 5:
						displayField.append("\n- - - - - - - - - - - - - - - - - - - - - - - - -");
						displayField.append("\n1: ENTER NEW "+options[userSelection]);
						subInputRequest = true;
					break;
					case 0:
						displayField.append("\n- - - - - - - - - - - - - - - - - - - - - - - - -");
						displayField.append("\n0: DONE! ");
						subInputRequest = false;
						// deactivate session
						deActivateSessions();
						// deactivate input panel
						deActivateInputPanel();
						currentInputRequest = 0;
						subInputRequest = false;
					break;
					}
				}
				
				else if(subInputRequest) { // get and validate new detail entry
					
					editEntryData = "";
					detailEditSuccess = false;
					
					switch(userSelection) {
					// TITLE 
					case 1:
						editEntryData = sanitise(inputField.getText());

						if(validTitle(editEntryData)) {
							displayField.append(" "+editEntryData);
							account.setTitle(editEntryData);
							detailEditSuccess = true;
						}else {
							displayField.append("\n*************************************************");
							displayField.append("\nINVALID ENTRY FOR "+options[userSelection]+" => "+editEntryData);
							displayField.append("\n*************************************************");
							displayField.append("\nENTER NEW TITLE [Mr | Ms | Mrs]: ");
						}
					break;
					// SURNAME 
					case 2:
						editEntryData = sanitise(inputField.getText());

						if(validNameElement(editEntryData)) {
							displayField.append(" "+editEntryData);
							account.setSurname(editEntryData);
							detailEditSuccess = true;
						}else {
							displayField.append("\n*************************************************");
							displayField.append("\nINVALID ENTRY FOR "+options[userSelection]+" => "+editEntryData);
							displayField.append("\n*************************************************");
							displayField.append("\nENTER NEW TITLE [Mr | Ms | Mrs]: ");
						}
					break;
					// ADDRESS 
					case 3:
						editEntryData = sanitise(inputField.getText());
						displayField.append(" "+editEntryData);
						account.setAddress(editEntryData);
						detailEditSuccess = true;
					break;
					// BALANCE 
					case 4:
						editEntryData = sanitise(inputField.getText());

						if(validMonetaryElement(editEntryData)) {
							displayField.append(" "+editEntryData);
							account.setBalance(Integer.valueOf(editEntryData));
							detailEditSuccess = true;
						}else {
							displayField.append("\n*************************************************");
							displayField.append("\nINVALID ENTRY FOR "+options[userSelection]+" => "+editEntryData);
							displayField.append("\n*************************************************");
							displayField.append("\nENTER NEW TITLE [Mr | Ms | Mrs]: ");
						}
					break;
					// OVERDRAFT 
					case 5:
						editEntryData = sanitise(inputField.getText());

						if(validMonetaryElement(editEntryData)) {
							displayField.append(" "+editEntryData);
							account.setOverDarft(Integer.valueOf(editEntryData));
							detailEditSuccess = true;
						}else {
							displayField.append("\n*************************************************");
							displayField.append("\nINVALID ENTRY FOR "+options[userSelection]+" => "+editEntryData);
							displayField.append("\n*************************************************");
							displayField.append("\nENTER NEW TITLE [Mr | Ms | Mrs]: ");
						}
					break;
					}
				}
				if(detailEditSuccess) {
					displayField.append("\n=================================================");
					displayField.append("\n"+options[userSelection]+" UPDATED!");
					displayField.append("\n- - - - - - - - - - - - - - - - - - - - - - - - -");
					displayField.append(account.toString());
					subInputRequest = false;
				}
			}
		}
		// if deleted account session active
		else if(deleteAccSession) {
			viewOrDelete("delete");
		}
		inputField.setText("");
	}
	/**
	 * Obtains and displays a list of all existing current accounts.
	 */
	private void listAll() {
		
		String accountsList = cs.listAll();
		displayField.setText(accountsList);
	}
	/**
	 * Used for view or delete operations as both operations follow the same logic.
	 * @param option - String: specifies which operation to perform. ["view" | "delete"]
	 */
	private void viewOrDelete(String option) {
		
		int accNo;
		String accNoEntered, account = "";
		
		try{
			accNoEntered = inputField.getText();
			accNo = Integer.valueOf(accNoEntered);
			displayField.append(accNoEntered+"\n");
			displayField.append("-------------------------------------------------");
			if(option.equals("view")) {
				account = cs.view(accNo);
			}
			else if(option.equals("delete")) {
				account = cs.delete(accNo);
			}
			displayField.append(account);
			deActivateSessions();
			deActivateInputPanel();
			
		}catch(Exception e) {
			displayField.append("*************************************************");
			displayField.append("\nINVALID ACCOUNT NO => "+inputField.getText()+"\n");
			displayField.append("*************************************************");
			displayField.append("ENTER ACCOUNT NO [NUMERIC >= 100000]: ");
		}
	}
	
	/* ===== INPUT SANITISERS =====*/
	/**
	 * Trims leading + trailing whitespace and capitalises the leading character of individual String str
	 * @param str - String: the string to be sanitised.
	 * @return sanitised string
	 */
	private String sanitise(String str) {
		
		String s = str.trim();
		s = s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase();
		return s;
	}
	/**
	 * Trims leading + trailing whitespace, Split multiple string into singles and capitalises the leading character of each individual String
	 * @param str - String: the string to be sanitised.
	 * @return sanitised string
	 */
	private String sanitiseAddress(String str) {
		
		String [] splitStr;
		str = str.trim();
		splitStr = str.split("[ ,]+");
		String s = "";

		for(String word: splitStr){
			if(word.length()<=1)
				s += ""+word.substring(0).toUpperCase();
			else
				s += ""+word.substring(0, 1).toUpperCase() + word.substring(1).toLowerCase()+" ";	
		}
		return s;
	}
	
	/* ===== INPUT VALIDATERS (REGEX) =====*/
	private boolean validTitle(String title) {
		boolean valid = false;
		if(title.equals("Mr") || title.equals("Ms") || title.equals("Mrs")) 
			valid = true;
		return valid;
	}
	/**
	 * Utilises regular expressions to validate individual elements of name construct.
	 * May only contain letters a-z with optional hyphens.
	 * @param element - String: name element string for validation
	 * @return boolean valid or not valid
	 */
	private boolean validNameElement(String element) {
		
		boolean valid = false;
		if(Pattern.matches("[a-zA-Z\']+", element)) 
			valid = true;
		return valid;
	}
	
	/**
	 * Utilises regular expressions to validate monetary elements i.e. balance | overdraft etc.
	 * May only contain numbers 0-9 with optional decimal with 1-2 places maximum.
	 * @param element - String: monetary string for validation
	 * @return boolean valid or not valid
	 */
	private boolean validMonetaryElement(String figure) {
		
		boolean valid = false;
		if(Pattern.matches("[0-9]+(\\.[0-9]{1,2})?", figure))
			valid = true;
		return valid;
	}
	
	/**
	 * Utilises regular expressions to validate date elements.
	 * Must be in format dd/mm/yyy.
	 * @param element - String: date string for validation
	 * @return boolean valid or not valid
	 */
	private boolean validDateElement(String date) {
		
		boolean valid = false;
		if(Pattern.matches("([0-9]{2})\\/([0-9]{2})\\/([0-9]{4})", date)) 
			valid = true;
		return valid;
	}
}
