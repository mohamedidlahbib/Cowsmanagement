package Models;

import java.sql.Date;

public class MilkSalesModel {
	String name;
	String CINClient;
	int Quantity;
	int Price;
	int Total;
	Date date;
	public MilkSalesModel(String name, String CINClient, int Quantity, int Price, int Total, Date date) {
		super();
		this.name = name;
		this.CINClient = CINClient;
		this.Quantity = Quantity;
		this.Price = Price;
		this.Total = Total;
		this.date = date;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getCINClient() {
		return CINClient;
	}
	public void setCINClient(String cINClient) {
		CINClient = cINClient;
	}
	public int getQuantity() {
		return Quantity;
	}
	public void setQuantity(int quantity) {
		this.Quantity = quantity;
	}
	public int getPrice() {
		return Price;
	}
	public void setPrice(int price) {
		this.Price = price;
	}
	public int getTotal() {
		return Price*Quantity;
	}
	public void setTotal(int Total) {
		this.Total = Total;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	

	
	

}