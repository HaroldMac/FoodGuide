package com.nutrition;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import org.apache.tomcat.jdbc.pool.ClassLoaderUtil;

public class NutritionalRecommendation {

	private String gender, ageRange, language, fileName;
	private String vf, gr, mi, me;
	
	public NutritionalRecommendation(String gender, int age, String language){
		this.gender = this.checkGender(gender);
		this.language = language;
		this.ageRange = this.getAgeRange(age);
		this.fileName = "cfg_openData_2015-05-19_csv/en/servings_per_day-en_ONPP.csv";
		this.readRecommendations();
	}
	
	public String getLanguage() {return language;}
	public int getServingsVf() {return Integer.parseInt(vf.split(" ")[0]);}
	public int getServingsGr() {return Integer.parseInt(gr.split(" ")[0]);}
	public int getServingsMi() {return Integer.parseInt(mi.split(" ")[0]);}
	public int getServingsMe() {return Integer.parseInt(me.split(" ")[0]);}
	
	private String getAgeRange(int age) {
		if ((age >= 2) && (age <= 3))        {return "2 to 3";  }
		else if ((age >= 4) && (age <= 8))   {return "4 to 8";  }
		else if ((age >= 9) && (age <= 13))  {return "9 to 13"; }
		else if ((age >= 14) && (age <= 18)) {return "14 to 18";}
		else if ((age >= 19) && (age <= 30)) {return "19 to 30";}
		else if ((age >= 31) && (age <= 50)) {return "31 to 50";}
		else if ((age >= 51) && (age <= 70)) {return "51 to 70";}
		else if (age >= 71)					 {return "71+"; 	}		
		return "";
	}
	
	private String checkGender(String gender) {
		if (gender.toLowerCase().equals("male")) {
			return "Male";
		} else if (gender.toLowerCase().equals("female")) {
			return "Female";
		} else if (gender.toLowerCase().equals("homme")) {
			return "Male";
		} else if (gender.toLowerCase().equals("femme")) {
			return "Female";
		} else {
			return "";
		}
	}
	
	
	private void readRecommendations() {
	    String line = null;
	    try {
	    	ClassLoader loader = Thread.currentThread().getContextClassLoader();
	    	InputStream is = loader.getResourceAsStream(fileName);
	      	InputStreamReader streamReader = new InputStreamReader(is, StandardCharsets.ISO_8859_1);
	    	BufferedReader bufferedReader = new BufferedReader(streamReader);
	    	
	    	
	    	while((line = bufferedReader.readLine()) != null) {
	    		
	    		String [] recommendations = line.split(",");
	    		if ((recommendations[1].trim().equals(this.gender)) && (recommendations[2].trim()).equals(this.ageRange)) {
	    			if (recommendations[0].equals("vf")) {this.vf = recommendations[3];}
	    			if (recommendations[0].equals("gr")) {this.gr = recommendations[3];}
	    			if (recommendations[0].equals("mi")) {this.mi = recommendations[3];}
	    			if (recommendations[0].equals("me")) {this.me = recommendations[3];}
	    		}
	    		
	        }   
	    	bufferedReader.close();         
	    } catch(IOException ex) {
	    	System.out.println("Unable to open file '" + fileName + "'");                
	    }
	}
	
}
