/**
 * @author naina
 * description: This class is a model for the expense profile information 
 */
package entity;

public class ExpenseProfile {
	private double availableFunds;
	private double spentFunds;
	
	ExpenseProfile(double availableFunds,double funds_spent){
		this.availableFunds = availableFunds;
		this.spentFunds = funds_spent;
	}

	public double getAvailableFunds() {
		return availableFunds;
	}

	public void setAvailableFunds(double availableFunds) {
		this.availableFunds = availableFunds;
	}
	
	public double getSpentFunds() {
		return spentFunds;
	}

	public void setSpentFunds(double spentFunds) {
		this.spentFunds = spentFunds;
	}
	
}
