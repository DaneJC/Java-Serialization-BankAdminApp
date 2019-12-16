package ie.lyit.testers;

public class tester1 {

	public static void main(String[] args) {
		
		sanitiseAddress("hairy mary full of toast 3");
	}
	private static String sanitiseAddress(String str) {
		
		String [] splitStr;
//		str = str.trim();
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

}
