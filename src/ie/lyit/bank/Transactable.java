/**
* Class: B.Sc. in Cloud Computing
* Instructor: Maria Boyle
* Description:  
* 	Transactable interface used for scenarios where deposits 
*   and withdraw operations are desired, 
* 	a component of a banking system which models The Bank of Ireland System.
* Date: 26/09/2019
* @author Dane Campbell - L00142041
* @version 1.0
*/

package ie.lyit.bank;

public interface Transactable {

	public abstract void deposit(double amount);
	public abstract void withdraw(double amount);

}
