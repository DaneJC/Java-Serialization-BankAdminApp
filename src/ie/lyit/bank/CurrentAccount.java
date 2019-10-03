/**
* Class: B.Sc. in Cloud Computing
* Instructor: Maria Boyle
* Description:  
* 	CurrentAccount class ...
* Date: 03/10/2019
* @author Dane Campbell - L00142041
* @version 1.0
*/

package ie.lyit.bank;

public class CurrentAccount extends BankAccount {
	
	private double overDarft;

	public CurrentAccount() {
		super();
		this.overDarft = 0.00;
	}

	public CurrentAccount(Name name, String address, double balance, double overDarft, Date dateOpened) {
		super(name, address, balance, dateOpened);
		this.overDarft = overDarft;
	}

	/** returns a String value which will be used to display a CurrentAccount object. */
	@Override
	public String toString() {
		return    " Account: "+ accountNo +"\t€"+ balance+ 
				"\n    Name: " + nameA + ", Address: " + addressA +
				"\nOverdaft: " + overDarft + 
				"\n  Opened: " + dateOpened;
	}
	
	/* BEGIN: getters and setters */ 
	// overDarft
	public double getOverDarft() {
		return overDarft;
	}

	public void setOverDarft(double overDarft) {
		this.overDarft = overDarft;
	}
	
	/* END: getters and setters */ 
	
	/** 
	 * Determines if the account is overdrawn or not, i.e. if the balance is negative or not,
	 * returns: true if overdrawn | false if not overdrawn.
	 **/
	public boolean isOverdrawn() {
		if(balance < 0)
			return true;
		else return false;
	}
	
	/**
	 * Overridden withdraw(); checks the amount is less than the balance 
	 * plus the overdraft amount before it is withdrawn.
	 */
	@Override
	public void withdraw(double amount) throws IllegalArgumentException {
		if(this.balance + this.overDarft >= amount)
			this.balance -= amount;
		else {
			throw new IllegalArgumentException("Withdraw amount exceeds current balance!");
		}
			
		
	}
	
	
	

	
	
	
	
	
	
}
