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
public class Date implements Serializable{

	/*	Instance variables – day, month, and year. */
	private int day, month, year;
	private final String[] months = {"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
	
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
	
	private String checkDate() {
		String status = "pass";
		switch(getMonth()) {
			// Feb
			case 2:
				// permitted day values 1 -> 28
				if(getDay() < 1 || getDay() > 28)
					status = "Day value out of range for "+months[getMonth()-1]+"! [1 -> 28]";
				break;
				
			// Apr, Jun, Sep, Nov
			case 4: case 6: case 9: case 11:
				// permitted day values 1 -> 30
				if(getDay() < 1 || getDay() > 30)
					status = "Day value out of range for "+months[getMonth()-1]+"! [1 -> 30]";
				break;
				
			// Jan, Mar, May, Jul, Aug, Oct, Dec
			case 1: case 3: case 5: case 7: case 8: case 10: case 12:
				// permitted day values 1 -> 31
				if(getDay() < 1 || getDay() > 31)
					status = "Day value out of range for "+months[getMonth()-1]+"! [1 -> 31]";
				break;
		}
		return status;
	}
	
	/* BEGIN: getters and setters */
	// day 
	/** Returns value of instance variable day */
	public int getDay() {
		return this.day;
	}

	/** Sets instance variable day to the String parameters passed in. */
	public void setDay(int day) throws IllegalArgumentException {
		if(day > 0 && day <=31) {
			this.day = day;
			if(getMonth() != 0) {
				String dateFormatResult = checkDate();
				if(dateFormatResult != "pass")
					throw new IllegalArgumentException(dateFormatResult);
			}
		}else
			throw new IllegalArgumentException("Day value out of bounds! [1->31]");
	}

	// month 
	/** Returns value of instance variable month */
	public int getMonth() {
		return this.month;
	}
	
	/** Sets instance variable month to the String parameters passed in. */
	public void setMonth(int month) throws IllegalArgumentException {
		
		if(month > 0 && month <=12) {
			this.month = month;
			if(getDay() != 0) {
				String dateFormatResult = checkDate();
				if(dateFormatResult != "pass")
					throw new IllegalArgumentException(dateFormatResult);
			}
		}else
			throw new IllegalArgumentException("Month value out of bounds! [1->12]");
	}
	
	// year 
	/** Returns value of instance variable year */
	public int getYear() {
		return this.year;
	}

	/** Sets instance variable year to the String parameters passed in. */
	public void setYear(int year)  throws IllegalArgumentException {
		if(year <= 2020 && year > 1900)
			this.year = year;
		else 
			throw new IllegalArgumentException("Year value out of bounds! [1900->Present]");
	}
	/* END: getters and setters */ 
	
	/** returns a String value which will be used to display a Date object. */
	@Override
	public String toString() {
		return ""+day+"/"+month+"/"+year;
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
