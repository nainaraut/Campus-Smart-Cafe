/**
 * @author naina
 * description: This class is a model for the Food information 
 */
package entity;

public class Food {
	private String foodId;
	private String name;
	private String type;
	private double price;
	private int calories;
	private int carbohydrates;
	private int fats;
	private int cholestrol;
	private boolean peanut_allergy ;
	private boolean seafood_allergy ;
	private boolean lactose_intolerant;
	private String prepTime;
	
	public Food(String foodId,String name,String type,double price,int calories,int carbohydrates,
			int fats,int cholestrol,boolean peanut_allergy,boolean seafood_allergy,boolean lactose_intolerant,String prepTime){
		this.foodId = foodId;
		this.name = name;
		this.type = type;
		this.price = price;
		this.calories = calories;
		this.carbohydrates = carbohydrates;
		this.fats = fats;
		this.cholestrol = cholestrol;
		this.prepTime = prepTime;
	}
	public String getFoodId() {
		return foodId;
	}
	public void setFoodId(String foodId) {
		this.foodId = foodId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getCalories() {
		return calories;
	}
	public void setCalories(int calories) {
		this.calories = calories;
	}
	public int getCarbohydrates() {
		return carbohydrates;
	}
	public void setCarbohydrates(int carbohydrates) {
		this.carbohydrates = carbohydrates;
	}
	public int getFats() {
		return fats;
	}
	public void setFats(int fats) {
		this.fats = fats;
	}
	public int getCholestrol() {
		return cholestrol;
	}
	public void setCholestrol(int cholestrol) {
		this.cholestrol = cholestrol;
	}
	public String getPrepTime() {
		return prepTime;
	}
	public void setPrepTime(String prepTime) {
		this.prepTime = prepTime;
	}
	public boolean isPeanut_allergy() {
		return peanut_allergy;
	}
	public void setPeanut_allergy(boolean peanut_allergy) {
		this.peanut_allergy = peanut_allergy;
	}
	public boolean isSeafood_allergy() {
		return seafood_allergy;
	}
	public void setSeafood_allergy(boolean seafood_allergy) {
		this.seafood_allergy = seafood_allergy;
	}
	public boolean isLactose_intolerant() {
		return lactose_intolerant;
	}
	public void setLactose_intolerant(boolean lactose_intolerant) {
		this.lactose_intolerant = lactose_intolerant;
	}
	
}
