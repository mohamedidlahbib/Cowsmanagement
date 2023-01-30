package Models;

import java.sql.Date;

public class CowsModel {

	int cowsid;
	String color;
	int age;
	int weightatbirth;
	Date date;
	public CowsModel(int cowsid, String color, int age, Date date, int weightatbirth) {
	
		this.cowsid = cowsid;
		this.color = color;
		this.age = age;
		this.date = date;
		this.weightatbirth = weightatbirth;
	}
	public int getCowsid() {
		return cowsid;
	}
	public void setCowsid(int cowsid) {
		this.cowsid = cowsid;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public int getWeightatbirth() {
		return weightatbirth;
	}
	public void setWeightatbirth(int weightatbirth) {
		this.weightatbirth = weightatbirth;
	}
}
	

	