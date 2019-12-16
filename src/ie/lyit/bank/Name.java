/**
* Class: B.Sc. in Cloud Computing
* Instructor: Maria Boyle
* Description:  
* 	Name class used to create name objects, 
* 	a component of a banking system which models The Bank of Ireland System.
* Date: 19/09/2019
* @author Dane Campbell - L00142041
* @version 1.0
*/

package ie.lyit.bank;

import java.io.Serializable;

import com.sun.xml.internal.ws.util.StringUtils;

/** 
 * Name class used to create name objects, a component of a banking system which models The Bank of Ireland System.
 */
public class Name implements Serializable{

	/**	Instance variables – title, firstName, and lastName. */
	private String title, firstName, lastName;
	
	/**	Default constructor, initializes the instance variables to the null String. */
	public Name() {
		this.title = null;
		this.firstName = null;
		this.lastName = null;
	}
	
	/** Initialization constructor, initializes the instance variables to the parameters passed in. */
	public Name(String t, String fN, String sN) {
		this.title = this.sanitise(t);
		this.firstName = this.sanitise(fN);
		this.lastName = this.sanitise(sN);
	}
	
	/* BEGIN: getters and setters */
	// title 
	/** Returns value of instance variable title */
	public String getTitle() {
		return title;
	}
	/** Sets instance variable title to the String parameters passed in. */
	public void setTitle(String title) {
		this.title = this.sanitise(title);
	}
	
	// firstName 
	/** Returns value of instance variable firstName */
	public String getFirstName() {
		return firstName;
	}
	/** Sets instance variable firstName to the String parameters passed in. */
	public void setFirstName(String firstName) {
		this.firstName = this.sanitise(firstName);
	}
	
	// lastName 
	/** Returns value of instance variable lastName */
	public String getLastName() {
		return lastName;
	}
	/** Sets instance variable lastName to the String parameters passed in. */
	public void setLastName(String lastName) {
		this.lastName = this.sanitise(lastName);
	}
	/* END: getters and setters */ 
	
	/**	Returns a String value which will be used to display a Name object. */
	@Override
	public String toString() {
		return title + " " + firstName + " " + lastName;
	}

	/** Takes an Object parameter and returns a boolean (true or false) to indicate equality. */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Name other = (Name) obj;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

	/** Returns true if the title is Miss, Ms, or Mrs, and false otherwise. */
	public boolean isFemale() {
		title = this.title.toLowerCase();
		switch(title) {
			case "miss":
			case "ms":
			case "mrs":
				return true;
			default: 
				return false;
		}
	}

	/* Returns String with leading and trailing whitespace omitted and capitalized beginning. */
	private String sanitise(String str) {
		String s = str.trim();
		s = s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase();
		return s;
	}
}
