package com.nutrition;

public class MyFoodGuide {

	private NutritionalRecommendation nr;
	
	public MyFoodGuide(String gender, int age, String language){
		this.nr = new NutritionalRecommendation(gender, age, language);

	}
		
	public String PrintRecommendations() {
		String message = "";
		if (this.nr.getLanguage().equals("fr")) {
			FoodRecommendations vfRecF = new FoodRecommendations("Légumes et fruits", "vf", this.nr.getServingsVf(), "fr" );
			FoodRecommendations grRecF = new FoodRecommendations("Produits céréaliers", "gr", this.nr.getServingsGr(), "fr" );
			FoodRecommendations miRecF = new FoodRecommendations("Lait et substituts", "mi", this.nr.getServingsMi(), "fr" );
			FoodRecommendations meRecF = new FoodRecommendations("Viandes et substituts", "me", this.nr.getServingsMe(), "fr" );	
			message +=  vfRecF + "\n";
			message +=  grRecF + "\n";
			message +=  miRecF + "\n";
			message +=  meRecF + "\n";
		} else {
			FoodRecommendations vfRec = new FoodRecommendations("Vegetables and Fruit", "vf", this.nr.getServingsVf(), "en" );
			FoodRecommendations grRec = new FoodRecommendations("Grains", "gr", this.nr.getServingsGr(), "en" );
			FoodRecommendations miRec = new FoodRecommendations("Milk and Alternatives", "mi", this.nr.getServingsMi(), "en" );
			FoodRecommendations meRec = new FoodRecommendations("Meat and Alternatives", "me", this.nr.getServingsMe(), "en" );

			message +=  vfRec + "\n";
			message +=  grRec + "\n";
			message +=  miRec + "\n";
			message +=  meRec + "\n";
		}
		return message;
	}
	
	public String webPrint(){
		String message = this.PrintRecommendations();
		message = message.replace("\n", "<br>");
		return message;
	}
	
	
	 public static void main( String args[] ) {
		 String gender = "";
		 int age = 0;
		 String language = "";
		 if (args.length == 2) {
			 try {
				 gender = args[0];
				 if (gender.toLowerCase().equals("homme") || gender.toLowerCase().equals("femme")){
					 language = "fr";
				 }else{
					 language = "en";
				 }
			     age = Integer.parseInt(args[1]);
			 } catch (NumberFormatException e) {
			     System.err.println("Argument" + args[0] + " must be an integer.");
			     System.exit(1);
			 }
		 } else {
			 System.out.println("Invalid parameters.");
			 System.out.println("Use [gender] [age]");
			 System.exit(1);
		 }
		 MyFoodGuide myFG = new MyFoodGuide(gender, age, language);
		 myFG.PrintRecommendations();
		 
	 }

}
