package com.nutrition;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Random;

public class FoodRecommendations {
	
	private int servings;
	private String language, fileName, foodDescription, foodGroupIdentifier;
	private ArrayList<String> possibleFoods;
	private String[] recommendedFoods;
	
	public FoodRecommendations(String description, String identifier, int servings, String lang) {
		this.foodDescription = description;
		this.foodGroupIdentifier = identifier;
		this.servings = servings;
		this.language = lang;
		possibleFoods = new ArrayList<String>();
		recommendedFoods = new String[servings];
		
		this.selectFileName();
		this.readFoodFile();
		this.selectRecommendedFoods();
	}
	
	private void selectFileName() {
		if (this.language.equals("fr")) {
			this.fileName = "cfg_openData_2015-05-19_csv/fr/foods-fr_ONPP_rev.csv";
		} else {
			this.fileName = "cfg_openData_2015-05-19_csv/en/foods-en_ONPP_rev.csv";
		}
	}
	
	private void readFoodFile() {	
	    String line = null;
	    try {
	    	ClassLoader loader = Thread.currentThread().getContextClassLoader();
	    	InputStream is = loader.getResourceAsStream(fileName);
	    	
	      	InputStreamReader streamReader = new InputStreamReader(is, StandardCharsets.ISO_8859_1);
	    	BufferedReader bufferedReader = new BufferedReader(streamReader);

	    	
	    	while((line = bufferedReader.readLine()) != null) {
	    		line = line.replace('"', ' ').trim();
	    		String [] foodDetails = line.split(",");
	    		if (foodDetails[0].equals(this.foodGroupIdentifier)) {
	    			possibleFoods.add(this.parseFoodDetails(foodDetails));
	    		}
	        }   
	    	bufferedReader.close();         
	    } catch(IOException ex) {
	    	System.out.println("Unable to open file '" + fileName + "'");                
	    }
	}
	
	private String parseFoodDetails(String[] food) {
		String foodList = "- ";
		String measurements = " (";
		for (int i = 2; i < food.length; i++) {
			boolean containsDigit = false;
			for(char c: food[i].toCharArray()) {
				if (Character.isDigit(c) || (Character.getType(c)== 11) ) {
					containsDigit = true;
					continue;
				}
			}
			if (!containsDigit) {
				foodList += food[i].trim() + " ";
			} else {
				measurements += food[i].trim() + ", ";
			}
		}
		measurements = measurements.substring(0, measurements.length()-2);
		String foodAndMeasurement = foodList + measurements + ")";
		return foodAndMeasurement;
	}
	
	private void selectRecommendedFoods() {
		Random rand = new Random();
		while(this.possibleFoods.size() > servings){
			possibleFoods.remove(rand.nextInt(possibleFoods.size()));
		}
		
	}
		
	@Override
	public String toString() {
		String returnString = String.format(foodDescription + " - %d servings \n", servings);
		
		for (int i=0; i < this.possibleFoods.size(); i++) {
			returnString += this.possibleFoods.get(i) + "\n";
		}
		return returnString;
	}

}
