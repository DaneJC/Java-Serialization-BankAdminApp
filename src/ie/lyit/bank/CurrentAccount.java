/**
* Class: B.Sc. in Cloud Computing
* Instructor: Maria Boyle
* Description:  
* 	CurrentAccount class ...
* Date: 19/09/2019
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
	
	
	
	
	
}
