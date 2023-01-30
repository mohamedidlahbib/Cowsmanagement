package Models;

import java.sql.Date;

public class HealthModel {
	int cowid;
	String event;
	String diagnosis;
	String traitement;
	int costoft;
	Date date;
	public HealthModel(int cowid, String event, String diagnosis, String traitement, int costoft, Date date) {
		super();
		this.cowid = cowid;
		this.event = event;
		this.diagnosis = diagnosis;
		this.traitement = traitement;
		this.costoft = costoft;
		this.date = date;
	}
	public int getCowid() {
		return cowid;
	}
	public void setCowid(int cowid) {
		this.cowid = cowid;
	}
	public String getEvent() {
		return event;
	}
	public void setEvent(String event) {
		this.event = event;
	}
	public String getDiagnosis() {
		return diagnosis;
	}
	public void setDiagnosis(String diagnosis) {
		this.diagnosis = diagnosis;
	}
	public String getTraitement() {
		return traitement;
	}
	public void setTraitement(String traitement) {
		this.traitement = traitement;
	}
	public int getCostoft() {
		return costoft;
	}
	public void setCostoft(int costoft) {
		this.costoft = costoft;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	
	

	
	

}