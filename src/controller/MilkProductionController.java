package controller;

import javafx.scene.control.TableColumn;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ResourceBundle;

import Models.CowsModel;
import Models.DataBaseConnection;
import Models.MilkProductionModel;

public class MilkProductionController implements Initializable{
	
	Connection cnx;
	public PreparedStatement st;
	public ResultSet result;
	

    @FXML
    private Button btn_clear;

    @FXML
    private Button btn_delete;

    @FXML
    private Button btn_edit;

    @FXML
    private Button btn_save;
    
    @FXML
    private TextField txt_AmMilk;

    @FXML
    private TextField txt_PmMilk;

    @FXML
    private Text txt_TotalMilk;

    @FXML
    private TextField txt_cows;

    @FXML
    private DatePicker txt_date;
    
    @FXML
    private TableView<MilkProductionModel> table_MilkProductionModel;
    
    @FXML
    private TableColumn<MilkProductionModel, Integer>cln_cows;
    
    @FXML
    private TableColumn<MilkProductionModel, Integer>cln_AmMilk;
    
    @FXML
    private TableColumn<MilkProductionModel, Integer>cln_PmMilk;
    
    @FXML
    private TableColumn<MilkProductionModel, Integer>cln_TotalMilk;
    
    @FXML
    private TableColumn<MilkProductionModel, Date>cln_date;
    
    ObservableList<MilkProductionModel> data=FXCollections.observableArrayList();
    
    
    
    void showMilkPro() {
    	table_MilkProductionModel.getItems().clear();
    	String sql="SELECT * FROM MilkPro";
    	try {
			st= cnx.prepareStatement(sql);
			result=st.executeQuery();
			while(result.next()) {
				data.add(new MilkProductionModel(result.getInt("cowid"),result.getInt("ammilk"),result.getInt("pmmilk"),result.getInt("totalmilk"), result.getDate("date")));
    		 }
			 cln_cows.setCellValueFactory(new PropertyValueFactory<MilkProductionModel,Integer>("cowid"));
			 cln_AmMilk.setCellValueFactory(new PropertyValueFactory<MilkProductionModel,Integer>("amMilk"));
			 cln_PmMilk.setCellValueFactory(new PropertyValueFactory<MilkProductionModel,Integer>("pmMilk"));
			 cln_TotalMilk.setCellValueFactory(new PropertyValueFactory<MilkProductionModel,Integer>("totalMilk"));
			 cln_date.setCellValueFactory(new PropertyValueFactory<MilkProductionModel,Date>("date"));
			 table_MilkProductionModel.setItems(data);
    		
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	
    }

  
    @FXML
    void saveMikPro() {
    	
    	String cowid= txt_cows.getText();
    	String Ammilk = txt_AmMilk.getText();
    	String Pmmilk = txt_PmMilk.getText();
    	int Totalmilk = Integer.parseInt(Ammilk)+Integer.parseInt(Pmmilk);
    	
    	String sql = "INSERT INTO milkpro(cowid,ammilk,pmmilk,totalmilk,date) VALUES(?,?,?,?,?)";
    	if(cowid.equals("") || Ammilk.equals("") || Pmmilk.equals("") ||  txt_date.getValue().equals("")) {
    		Alert alert = new Alert(AlertType.WARNING,"You should add all information",javafx.scene.control.ButtonType.OK);
			alert.showAndWait();
    	}
    	else {
    		try {
    			st= cnx.prepareStatement(sql);
    			st.setString(1,cowid);
    			st.setString(2,Ammilk );
    			st.setString(3, Pmmilk);
    			st.setInt(4, Totalmilk);
    			java.util.Date date=java.util.Date.from(txt_date.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
    			Date sqlDate= new Date(date.getTime());
    			st.setDate(5, sqlDate);
    			st.execute();
    			txt_cows.setText("");
    			txt_AmMilk.setText("");
    			txt_PmMilk.setText("");
    			txt_TotalMilk.setText("");
    			txt_date.setValue(null);
    			Alert alert = new Alert(AlertType.CONFIRMATION,"Milk Record has been added",ButtonType.OK);
    			alert.showAndWait();
    			showMilkPro();
    			
    			
    			
    		} catch (SQLException e) {
    			e.printStackTrace();
    		
    		
    	}
    }
   
    }
    
    @FXML
    void EditMilkPro() {
    	String cowid= txt_cows.getText();
    	String Ammilk = txt_AmMilk.getText();
    	String Pmmilk = txt_PmMilk.getText();
    	String Totalmilk = txt_TotalMilk.getText();
    	
    	String sql="update milkpro set cowid=?,Ammilk=?,Pmmilk=?, Totalmilk=?,date=? where cowid='"+cowid+"'";
    	if(cowid.equals("") || Ammilk.equals("") || Pmmilk.equals("") || Totalmilk.equals("") || txt_date.getValue().equals("")) {
    		Alert alert = new Alert(AlertType.WARNING,"You should add all information",javafx.scene.control.ButtonType.OK);
			alert.showAndWait();
    	}
    	else {
    		try {
    			st= cnx.prepareStatement(sql);
    			st.setString(1,cowid);
    			st.setString(2,Ammilk );
    			st.setString(3, Pmmilk);
    			st.setString(4, Totalmilk);
    			java.util.Date date=java.util.Date.from(txt_date.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
    			Date sqlDate= new Date(date.getTime());
    			st.setDate(5, sqlDate);
    			st.execute();
    			txt_cows.setText("");
    			txt_AmMilk.setText("");
    			txt_PmMilk.setText("");
    			txt_TotalMilk.setText("");
    			txt_date.setValue(null);
    			Alert alert = new Alert(AlertType.CONFIRMATION,"Milk  has been edited",ButtonType.OK);
    			alert.showAndWait();
    			showMilkPro();
    			
    			
    			
    		} catch (SQLException e) {
    			e.printStackTrace();
    		
    		
    	}
    }

    }
    
    @FXML
    void DeleteMilkPro() {
    	String cowid= txt_cows.getText();
    	String Ammilk = txt_AmMilk.getText();
    	String Pmmilk = txt_PmMilk.getText();
    	String Totalmilk = txt_TotalMilk.getText();
    	
    	String sql="delete  from milkpro where  cowid='" + txt_cows.getText()+"'";
    	
    	if(cowid.equals("") || Ammilk.equals("") || Pmmilk.equals("") || Totalmilk.equals("") || txt_date.getValue().equals("")) {
    		Alert alert = new Alert(AlertType.WARNING,"You should add all information",javafx.scene.control.ButtonType.OK);
			alert.showAndWait();
    	}
    	else {
    		try {
    			st= cnx.prepareStatement(sql);
    			st.executeUpdate();
    			txt_cows.setText("");
    			txt_AmMilk.setText("");
    			txt_PmMilk.setText("");
    			txt_TotalMilk.setText("");
    			txt_date.setValue(null);
    			Alert alert = new Alert(AlertType.CONFIRMATION,"Milk has been deleted",ButtonType.OK);
    			alert.showAndWait();
    			showMilkPro();
    		
    		} catch (SQLException e) {
    			e.printStackTrace();
    			
    		}
    }
    	
    }
  
    
    @FXML
    void getselected() {
    	MilkProductionModel milkmodel =table_MilkProductionModel.getSelectionModel().getSelectedItem();
 	   String sql="select * from  milkpro where cowid=?";
 	   try {
			st=(PreparedStatement) cnx.prepareStatement(sql);
			st.setInt(1,milkmodel.getCowid());
			result=st.executeQuery();
			if(result.next()) {
				txt_cows.setText(result.getString("cowid"));
				txt_AmMilk.setText (result.getString ("Ammilk"));
				txt_PmMilk.setText (result.getString("Pmmilk"));
				txt_TotalMilk.setVisible(false);
				txt_TotalMilk.setText( result.getString ("Totalmilk" ));
				Date date=result.getDate("date");
				txt_date.setValue (date.toLocalDate());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
 
    }
    
 
    
    @FXML
    void ClearMilkPro() {
    	txt_cows.setText("");
  		txt_AmMilk.setText("");
  		txt_PmMilk.setText("");
  		txt_TotalMilk.setText("");
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
		showMilkPro();
		
	}


}