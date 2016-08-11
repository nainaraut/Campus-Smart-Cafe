/**
 * @author naina
 * description: This class is a model to hold the CampusCafes information 
 */
package entity;

public class CampusCafes {
	private String campusId;
	private String type;
	private String name;
	private String location;
	private String foodItems;
	
	public CampusCafes(String campusId,String type,String name,String location,String foodItems){
		this.name = name;
		this.type = type;
		this.campusId = campusId;
		this.location = location;
		this.foodItems = foodItems;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCampusId() {
		return campusId;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setCampusId(String campusId) {
		this.campusId = campusId;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getFoodItems() {
		return foodItems;
	}
	public void setFoodItems(String foodItems) {
		this.foodItems = foodItems;
	}
}
