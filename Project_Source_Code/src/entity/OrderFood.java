/**
 * @author naina
 * description: This class is a model for the order details information 
 */
package entity;

import java.util.Date;

public class OrderFood {

	String campus_cafe_id;
	int user_id;
	String food_id;
	int quantity;
	double total_amount;
	Date purchase_date;
	
	public OrderFood(String campus_cafe_id,int user_id,String food_id,int quantity,double total_amount,Date purchase_date){
		this.campus_cafe_id = campus_cafe_id ;
		this.user_id = user_id;
		this.food_id = food_id ;
		this.quantity = quantity;
		this.total_amount = total_amount;
		this.purchase_date = purchase_date;
	}

	public String getCampus_cafe_id() {
		return campus_cafe_id;
	}
	public void setCampus_cafe_id(String campus_cafe_id) {
		this.campus_cafe_id = campus_cafe_id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getFood_id() {
		return food_id;
	}
	public void setFood_id(String food_id) {
		this.food_id = food_id;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getTotal_amount() {
		return total_amount;
	}
	public void setTotal_amount(double total_amount) {
		this.total_amount = total_amount;
	}
	public Date getPurchase_date() {
		return purchase_date;
	}
	public void setPurchase_date(Date purchase_date) {
		this.purchase_date = purchase_date;
	}
}
