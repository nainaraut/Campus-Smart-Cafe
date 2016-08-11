/**
 * @author naina
 * description: This class is a model for the dietary profile information 
 */
package entity;

public class DietaryProfile {

	private int reqCalories;
	private boolean peanut_allergy ;
	private boolean seafood_allergy ;
	private boolean lactose_intolerant ;
	private int caloriesConsumed;
	
	public DietaryProfile(int reqCalories,int caloric_count,boolean peanut_allergy,boolean seafood_allergy,
			              boolean lactose_intolerant){
		this.reqCalories = reqCalories;
		this.peanut_allergy = peanut_allergy;
		this.seafood_allergy = seafood_allergy;
		this.lactose_intolerant = lactose_intolerant;
		this.caloriesConsumed = caloric_count;
	}

	public int getReqCalories() {
		return reqCalories;
	}

	public void setReqCalories(int reqCalories) {
		this.reqCalories = reqCalories;
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

	public int getCaloriesConsumed() {
		return caloriesConsumed;
	}

	public void setCaloriesConsumed(int caloriesConsumed) {
		this.caloriesConsumed = caloriesConsumed;
	}
}
