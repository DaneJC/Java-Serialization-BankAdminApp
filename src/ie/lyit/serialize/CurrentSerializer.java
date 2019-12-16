/**
* Class: B.Sc. in Cloud Computing
* Instructor: Maria Boyle
* Description:  
* 	CurrentSerializer class used to serialise and deserialise for persistent BankAccount objects, 
* 	a component of a banking system which models The Bank of Ireland System.
* Date: 26/10/2019
* @author Dane Campbell - L00142041
* @version 1.0
*/

package ie.lyit.serialize;

import ie.lyit.bank.CurrentAccount;
import ie.lyit.bank.Date;
import ie.lyit.bank.Name;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class CurrentSerializer{
	
	private ArrayList<CurrentAccount> currentAccounts;
	private final String FILENAME = "./src/files/currentAccounts.ser";
	Path p = Paths.get(FILENAME);
	
	public CurrentSerializer() {
		
		currentAccounts = new ArrayList<CurrentAccount>();
	}
	
	public String add(Name name, String address, double balance, double overDarft, Date dateOpened) {
		
		// create book object
		CurrentAccount account = new CurrentAccount(name, address, balance, overDarft, dateOpened);
		
		// add book object to books ArrayList
		currentAccounts.add(account);
		
		// return the new account details for confirmation/reference indicator
		return account.toString();
	}
	/**
	 * View account details related to specified account number parameter.
	 * @param accountNo
	 * @return Associated CurrentAccount toString() details or non-existence message.
	 */
	public String view(int accountNo) {
		
		// local variables: boolean exists flag and account details container.
		boolean exists = false;
		String details = "";
		
		// check for account id in CurrentAccounts ArrayList and obtain related account details if so.
		for(CurrentAccount cAcc: currentAccounts) {
			if(cAcc.getAccountNo() == accountNo) {
				details = cAcc.toString();
				exists = true;
				break;
			}
		}
		
		// if account does not exist: provide appropriate user feedback
		if(!exists)
			details =  "ACCOUNT "+accountNo+" DOES NOT EXIST!";
		
		// return user feedback
		return details;
	}
	
	/**
	 * Obtain current account associated with specified account number parameter.
	 * @param accountNo
	 * @return current account for editing
	 */
	public CurrentAccount editGetAccount(int accountNo) {
		
		// local variables: boolean exists flag and account details container.
		CurrentAccount account = null;
		
		// check for account id in CurrentAccounts ArrayList and obtain associated account.
		for(CurrentAccount cAcc: currentAccounts) {
			if(cAcc.getAccountNo() == accountNo) {
				account = (CurrentAccount)cAcc;
				break;
			}
		}
		// return account 
		return account;
	}
	
	/**
	 * Obtain existing current account with updated account parameter with regard to account number.
	 * @param account - updated account
	 * @return update account details
	 */
    public String editUpdateAccount(CurrentAccount account) {
		// search for account with matching account no and replace existing account with updated account
    	for(int i=0; i<currentAccounts.size(); i++) {
    		if(currentAccounts.get(i).getAccountNo() == account.getAccountNo())
    			currentAccounts.set(i, account);
    	}
    	// return updated account details for verification
    	return account.toString();
	}
	
    /**
	 * 
	 * @param accountNo
	 * @return
	 */
	public String delete(int accountNo) {
		
		// local variables: boolean exists flag and account details container.
		boolean exists = false;
		String details = "";
		
		// check for account id in CurrentAccounts ArrayList and obtain related account details if so.
		for(int i=0; i<currentAccounts.size(); i++) {
			if(currentAccounts.get(i).getAccountNo() == accountNo) {
				details = "ACCOUNT: "+currentAccounts.get(i).getAccountNo()+
						"\n HOLDER: "+currentAccounts.get(i).getName()+
						"\n-------------------------------------------------"+
						"\n => DELETED! ";
				exists = true;
				currentAccounts.remove(i);
				break;
			}
		}
		
		// if account does not exist: provide appropriate user feedback
		if(!exists)
			details =  "ACCOUNT "+accountNo+" DOES NOT EXIST!";
		
		// return user feedback
		return details;
	}
	/**
	 * return all accounts for listing in GUI display
	 * @return
	 */
	public String listAll() {
		String accountsList = "=================== ACCOUNTS ====================";
		for(CurrentAccount ca: currentAccounts) {
			accountsList += ca.toString();
			accountsList += "\n-------------------------------------------------";
		}
		return accountsList;
	}
	
	public void serialize() {
		
		ObjectOutputStream os = null;
		
		if(Files.notExists(p)) {
			try {
				Files.createFile(p);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		if(currentAccounts instanceof Serializable){
			try {
				// Create a FileOutputStream object, passing the name of the binary File to be created
				FileOutputStream fs = new FileOutputStream(p.toString());
				
				// Connect it to an ObjectOutputStream object
				os = new ObjectOutputStream(fs);
				
				// Call its writeObject() method to write the entire ArrayList instance variable to the file
				os.writeObject(currentAccounts);
			
			} catch (IOException e) {
				e.printStackTrace();
			
			}finally {
				try {
					// Call its close() method to close the file
					os.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	public void deserialize() {
		
		try {
			if(Files.exists(p)) {
				
				// Create a FileOutputStream object, passing the name of the binary File to be created
				FileInputStream fs = new FileInputStream(p.toString());
				
				// Connect it to an ObjectOutputStream object				
				ObjectInputStream os = new ObjectInputStream(fs);

				// Call its writeObject() method to read the object from the file and cast as CurrentAccount into the accounts ArrayList
				try {
					currentAccounts = (ArrayList<CurrentAccount>) os.readObject();
					computeNextAccNumber();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
				
				// Call its close() method to close the file
				os.close();
			} 
		}catch (IOException e) {
			// do nothing as IOException most likely occurs with empty serialisation file on initial launch.
			// initialising the ObjectOutputStream before the ObjectInputStream in the CurrentSerializer constructor would solve this issue as
			// ObjectOutputStream constructor writes some initial bytes (header) to the file which the ObjectInputStream 
			// constructor looks for ("ObjectInputStream.readStreamHeader" in the java.io.EOFException stack trace suggests). 
		}
	}
	
	private void computeNextAccNumber() {
		
		int nextAccNumber = 0;
		
		for(CurrentAccount ca: currentAccounts) {
			if(ca.getAccountNo() > nextAccNumber) {
				nextAccNumber = ca.getAccountNo();
			}
			ca.setNextAccountNumber(++nextAccNumber);
		}
	}
}

