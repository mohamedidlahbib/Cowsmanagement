package Models;

import java.util.Date;

public class FoodModel {
	int id;
	Date date;
	String type;
	float Quantity;
	float price;
	public FoodModel(int id,Date date, String type, float quantity, float price) {
		
		this.date = date;
		this.type = type;
		this.Quantity = quantity;
		this.price = price;
		this.id=id;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getType() {
		return type;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setType(String type) {
		this.type = type;
	}
	public float getQuantity() {
		return Quantity;
	}
	public void setQuantity(float quantity) {
		Quantity = quantity;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}

}
