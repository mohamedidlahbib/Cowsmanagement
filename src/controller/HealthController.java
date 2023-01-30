package controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.ZoneId;
import java.util.ResourceBundle;


import Models.DataBaseConnection;
import Models.HealthModel;
import Models.MilkProductionModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;

public class HealthController implements Initializable{
	Connection cnx;
	public PreparedStatement st;
	public ResultSet result;
	@FXML
    private TableColumn<HealthModel,String> cln_costoft;

    @FXML
    private TableColumn<HealthModel, Integer> cln_cowid;

    @FXML
    private TableColumn<HealthModel, Date> cln_date;

    @FXML
    private TableColumn<HealthModel, String> cln_diagnosis;

    @FXML
    private TableColumn<HealthModel, String> cln_event;

    @FXML
    private TableColumn<HealthModel, String> cln_traitement;

    @FXML
    private TableView<HealthModel> table_health;

    @FXML
    private TextField txt_costoft;

    @FXML
    private TextField txt_cowid;

    @FXML
    private DatePicker txt_date;

    @FXML
    private TextField txt_diagnonsis;

    @FXML
    private TextField txt_event;

    @FXML
    private TextField txt_traitement;
	
	
	

    
    
    ObservableList<HealthModel> data= FXCollections.observableArrayList();

	
	
    void ShowHealth() {
    	table_health.getItems().clear();
    	String sql = "select * from animal_health";
    	try {
			st=cnx.prepareStatement(sql);
			result=st.executeQuery();
			while(result.next()) {
				data.add(new HealthModel(result.getInt("cowid"),result.getString("event") ,result.getString("diagnosis"),result.getString("traitement") ,result.getInt("costoft") ,result.getDate("date")));        
			}
			
			cln_cowid.setCellValueFactory(new PropertyValueFactory<HealthModel,Integer>("cowid"));
			cln_event.setCellValueFactory(new PropertyValueFactory<HealthModel,String>("event"));
			cln_diagnosis.setCellValueFactory(new PropertyValueFactory<HealthModel,String>("diagnosis"));
			cln_traitement.setCellValueFactory(new PropertyValueFactory<HealthModel,String>("traitement"));
			cln_costoft.setCellValueFactory(new PropertyValueFactory<HealthModel,String>("costoft"));
			cln_date.setCellValueFactory(new PropertyValueFactory<HealthModel,Date>("date"));
			table_health.setItems(data);

			
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	
    	}
    
    @FXML
    void SaveHealth() {
    	String cowid=txt_cowid.getText();
    	String event=txt_event.getText();
    	String diagnosis=txt_diagnonsis.getText();
    	String traitement=txt_traitement.getText();
    	String costoft=txt_costoft.getText();
    	
    	String sql = "INSERT INTO animal_health(cowid,event,diagnosis,traitement,costoft,date) VALUES(?,?,?,?,?,?)";
    	if(cowid.equals("") || event.equals("") || diagnosis.equals("") || traitement.equals("")||costoft.equals("") || txt_date.getValue().equals("")) {
    		Alert alert = new Alert(AlertType.WARNING,"You should add all information",javafx.scene.control.ButtonType.OK);
			alert.showAndWait();
    	}
    	else {
    		try {
    			st= cnx.prepareStatement(sql);
    			st.setString(1,cowid);
    			st.setString(2,event );
    			st.setString(3, diagnosis);
    			st.setString(4, traitement);
    			st.setString(5, costoft);
    			java.util.Date date=java.util.Date.from(txt_date.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
    			Date sqlDate= new Date(date.getTime());
    			st.setDate(6, sqlDate);
    			st.execute();
    			txt_cowid.setText("");
    			txt_event.setText("");
    			txt_diagnonsis.setText("");
    			txt_traitement.setText("");
    			txt_costoft.setText("");
    			txt_date.setValue(null);
    			Alert alert = new Alert(AlertType.CONFIRMATION,"Health Record has been added",ButtonType.OK);
    			alert.showAndWait();
    			ShowHealth();
    			
    		} catch (SQLException e) {
    			e.printStackTrace();
    		
    		
    	}
    }
   
    }

    @FXML
    void EditHealth() {
      	String cowid=txt_cowid.getText();
    	String event=txt_event.getText();
    	String diagnosis=txt_diagnonsis.getText();
    	String traitement=txt_traitement.getText();
    	String costoft=txt_costoft.getText();
    	String sql = "update animal_health set cowid=?,event=?,diagnosis=?,traitement=?,costoft=?,date=? where cowid='"+cowid+"'";
    	if(cowid.equals("") || event.equals("") || diagnosis.equals("") || traitement.equals("")||costoft.equals("") || txt_date.getValue().equals("")) {
    		Alert alert = new Alert(AlertType.WARNING,"You should add all information",javafx.scene.control.ButtonType.OK);
			alert.showAndWait();
    	}
    	else {
    		try {
    			st= cnx.prepareStatement(sql);
    			st.setString(1,cowid);
    			st.setString(2,event );
    			st.setString(3, diagnosis);
    			st.setString(4, traitement);
    			st.setString(5, costoft);
    			java.util.Date date=java.util.Date.from(txt_date.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
    			Date sqlDate= new Date(date.getTime());
    			st.setDate(6, sqlDate);
    			st.execute();
    			txt_cowid.setText("");
    			txt_event.setText("");
    			txt_diagnonsis.setText("");
    			txt_traitement.setText("");
    			txt_costoft.setText("");
    			txt_date.setValue(null);
    			Alert alert = new Alert(AlertType.CONFIRMATION,"Health Record has been edited",ButtonType.OK);
    			alert.showAndWait();
    			ShowHealth();
    			
    		} catch (SQLException e) {
    			e.printStackTrace();
    		
    		
    	}
    }

    }
    
    
    
    @FXML
    void DeleteHealth() {
    	String cowid=txt_cowid.getText();
    	String event=txt_event.getText();
    	String diagnosis=txt_diagnonsis.getText();
    	String traitement=txt_traitement.getText();
    	String costoft=txt_costoft.getText();
    	
    	String sql="delete  from animal_health where  cowid='"+txt_cowid.getText()+"'";
    	if(cowid.equals("") || event.equals("") || diagnosis.equals("") || traitement.equals("")||costoft.equals("") || txt_date.getValue().equals("")) {
    		Alert alert = new Alert(AlertType.WARNING,"You should add all information",javafx.scene.control.ButtonType.OK);
			alert.showAndWait();
    	}
    	else {
    		try {
    			st= cnx.prepareStatement(sql);
    			st.execute();
    			txt_cowid.setText("");
    			txt_event.setText("");
    			txt_diagnonsis.setText("");
    			txt_traitement.setText("");
    			txt_costoft.setText("");
    			txt_date.setValue(null);
    			Alert alert = new Alert(AlertType.CONFIRMATION,"Health Record has been added",ButtonType.OK);
    			alert.showAndWait();
    			ShowHealth();
    			
    		} catch (SQLException e) {
    			e.printStackTrace();
    		
    		
    	}
    }

    	
    	
    	
    	
    }
    
    @FXML
    void getselected() {
      	HealthModel healthmodel =table_health.getSelectionModel().getSelectedItem();
   	   String sql="select * from  animal_health where cowid=?";
   	   try {
  			st=(PreparedStatement)cnx.prepareStatement(sql);
  			st.setInt(1,healthmodel.getCowid());
  			result=st.executeQuery();
  			if(result.next()) {
  				txt_cowid.setText(result.getString("cowid"));
  				txt_event.setText (result.getString ("event"));
  				txt_traitement.setText (result.getString("traitement"));
  				txt_diagnonsis.setText( result.getString ("diagnosis" ));
  				txt_costoft.setText( result.getString ("costoft" ));
  				Date date=result.getDate("date");
  				txt_date.setValue (date.toLocalDate());
  			}
  		} catch (SQLException e) {
  			e.printStackTrace();
  		}
    	
    	
    	
    }
    	
    @FXML
    void ClearHealth() {
    	txt_cowid.setText("");
		txt_event.setText("");
		txt_diagnonsis.setText("");
		txt_traitement.setText("");
		txt_costoft.setText("");
		txt_date.setValue(null);
    }


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try {
			cnx=DataBaseConnection.connect();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ShowHealth();
		

	}

}