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

import com.sun.xml.internal.ws.util.StringUtils;

/** 
 * Name class used to create name objects, a component of a banking system which models The Bank of Ireland System.
 */
public class Date {

	/*	Instance variables � day, month, and year. */
	private int day, month, year;
	
	/**	Default constructor, initializes the instance variables to zero. */
	public Date() {
		this.day = 0;
		this.month = 0;
		this.year = 0;
	}
	
	/** Initialization constructor, initializes the instance variables to the parameters passed in. */
	public Date(int d, int m, int y) {
		setDay(d);
		setMonth(m);
		setYear(y);
	}
	
	/* BEGIN: getters and setters */
	// day 
	/** Returns value of instance variable day */
	public int getDay() {
		return day;
	}

	/** Sets instance variable day to the String parameters passed in. */
	public void setDay(int day) throws IllegalArgumentException {
		if(day <= 31 && day > 0)
			this.day = day;
		else {
			throw new IllegalArgumentException("Day value out of bounds! [1->31]");
		}
	}

	// month 
	/** Returns value of instance variable month */
	public int getMonth() {
		return month;
	}
	
	/** Sets instance variable month to the String parameters passed in. */
	public void setMonth(int month) throws IllegalArgumentException {
		if(month <= 31 && month > 0)
			this.month = month;
		else {
			throw new IllegalArgumentException("Month value out of bounds! [1->12]");
		}
	}
	
	// year 
	/** Returns value of instance variable year */
	public int getYear() {
		return year;
	}

	/** Sets instance variable year to the String parameters passed in. */
	public void setYear(int year)  throws IllegalArgumentException {
		if(year <= 2020 && year > 1900)
			this.year = year;
		else {
			throw new IllegalArgumentException("Year value out of bounds! [1900->Present]");
		}
	}
	/* END: getters and setters */ 
	
	/** returns a String value which will be used to display a Date object. */
	@Override
	public String toString() {
		return "Date [day:" + day + ", month:" + month + ", year:" + year + "]";
	}

	/** Takes a Date parameter and returns a boolean (true or false) to	indicate equality. */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Date other = (Date) obj;
		if (day != other.day)
			return false;
		if (month != other.month)
			return false;
		if (year != other.year)
			return false;
		return true;
	}
	
	
	
}
