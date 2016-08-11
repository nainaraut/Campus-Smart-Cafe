/**
 * @author naina
 * description: This class is a model to hold the CampusCardUser information 
 */
package entity;

public class CampusCardUser {

	private int studentId;
	private String firstName;
	private String lastName;
	private String password;
	private DietaryProfile dietaryProfile;
	private ExpenseProfile expenseProfile;
	
	public CampusCardUser(int studentId,String firstName,String lastName,String password,double availailableFunds,double funds_spent, 
					int caloric_restriction,int caloric_count, boolean peanut_allergy,boolean seafood_allergy,boolean lactose_intolerant){
		this.studentId = studentId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.dietaryProfile = new DietaryProfile(caloric_restriction,caloric_count,peanut_allergy,seafood_allergy,lactose_intolerant);
		this.expenseProfile = new ExpenseProfile(availailableFunds,funds_spent);
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public DietaryProfile getDietaryProfile() {
		return dietaryProfile;
	}

	public void setDietaryProfile(DietaryProfile dietaryProfile) {
		this.dietaryProfile = dietaryProfile;
	}

	public ExpenseProfile getExpenseProfile() {
		return expenseProfile;
	}

	public void setExpenseProfile(ExpenseProfile expenseProfile) {
		this.expenseProfile = expenseProfile;
	}
	
	public double viewAvailableFunds(){
		return expenseProfile.getAvailableFunds();
	}
	
	public int viewReqCalories(){
		return dietaryProfile.getReqCalories();
	}
	
}
