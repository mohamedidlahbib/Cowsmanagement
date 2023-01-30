package Models;

import java.sql.Date;

public class MilkProductionModel {
	int cowid;
	int amMilk;
	int pmMilk;
	int totalMilk;
	Date date;
	public MilkProductionModel(int cowid, int amMilk, int pmMilk, int totalMilk, Date date) {
		super();
		this.cowid = cowid;
		this.amMilk = amMilk;
		this.pmMilk = pmMilk;
		this.totalMilk = totalMilk;
		this.date = date;
	}
	public int getCowid() {
		return cowid;
	}
	public void setCowid(int cowid) {
		this.cowid = cowid;
	}
	public int getAmMilk() {
		return amMilk;
	}
	public void setAmMilk(int amMilk) {
		this.amMilk = amMilk;
	}
	public int getPmMilk() {
		return pmMilk;
	}
	public void setPmMilk(int pmMilk) {
		this.pmMilk = pmMilk;
	}
	public int getTotalMilk() {
		return pmMilk+amMilk;
	}
	public void setTotalMilk(int totalMilk) {
		this.totalMilk = totalMilk;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	

}