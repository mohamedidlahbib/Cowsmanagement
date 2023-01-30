package controller;


import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ResourceBundle;

import com.mysql.jdbc.PreparedStatement;

import Models.CowsModel;
import Models.DataBaseConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBoxBase;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Labeled;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class CowsController implements Initializable {
	
	Connection cnx;
	public PreparedStatement st;
	public ResultSet result;
    @FXML
    private Button btn_clear;

    @FXML
    private Button btn_delete;

    @FXML
    private Button btn_edite;

    @FXML
    private Button btn_save;

    @FXML
    private TableColumn<CowsModel,Integer> cln_age;

    @FXML
    private TableColumn<CowsModel,String> cln_color;

    @FXML
    private TableColumn<CowsModel, Date> cln_date;

    @FXML
    private TableColumn<CowsModel, Integer> cln_id;

    @FXML
    private TableColumn<CowsModel, Integer> cln_weight;

    @FXML
    private TableView<CowsModel> table_cows;

    @FXML
    private TextField txt_age;

    @FXML
    private TextField txt_color;

    @FXML
    private TextField txt_cows;

    @FXML
    private DatePicker txt_date;

    @FXML
    private TextField txt_weight;

  ObservableList<CowsModel> data=FXCollections.observableArrayList();
	    
    @FXML
    void saveCows() {
    	String Cowsid=txt_cows.getText();
    	
    	int cowsid=Integer.parseInt(Cowsid);
    	
    	String color=txt_color.getText();
    	
    	String Age=txt_age.getText();
    	int age=Integer.parseInt(Age);
    	
    	String Weightatbirth=txt_weight.getText();
    	int weightatbirth=Integer.parseInt(Weightatbirth);
    	
    	String sql="insert into CowsModel(cowsid,color,age,date,weightatbirth) values(?,?,?,?,?)";
    	if(txt_cows.equals("") || color.equals("") || txt_age.equals("")|| txt_weight.equals("") || txt_date.getValue().equals("")) {
    		Alert alert = new Alert(AlertType.WARNING,"You should add all information",javafx.scene.control.ButtonType.OK);
			alert.showAndWait();
    	}
    	else {
    		try {
			st=(PreparedStatement) cnx.prepareStatement(sql);
			st.setInt(1, cowsid);
			st.setString(2,color);
			st.setInt(3,age);
			java.util.Date date= java.util.Date.from(txt_date.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
			Date sqldDate=new Date(date.getTime());
			st.setDate(4, sqldDate);
			st.setInt(5, weightatbirth);
			st.execute();
			txt_cows.setText("");
			txt_color.setText("");
			txt_age.setText("");
			txt_date.setValue(null);
			txt_weight.setText("");
			Alert alert = new Alert(AlertType.CONFIRMATION,"cows has been added",javafx.scene.control.ButtonType.OK);
			alert.showAndWait();
			showCows(); 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }
    	
    	  
    	
    }
    
     @FXML
    void getSelected() {
    	 CowsModel cowsmodel =table_cows.getSelectionModel().getSelectedItem();
  	   String sql="select * from  cowsmodel where cowsid=?";
  	   try {
			st=(PreparedStatement) cnx.prepareStatement(sql);
			st.setInt(1,cowsmodel.getCowsid());
			result=st.executeQuery();
			if(result.next()) {
				txt_cows.setText(result.getString("cowsid"));
				txt_color.setText (result.getString ("color"));
				txt_age.setText (result.getString("age"));
				txt_weight.setText( result.getString ("weightatbirth" ));
				Date date=result.getDate("date");
				txt_date.setValue (date.toLocalDate());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}   

    }
    @FXML
    void editeCows() {
    	String Cowsid=txt_cows.getText();
    	int cowsid=Integer.parseInt(Cowsid);
    	
    	String color=txt_color.getText();
    	String Age=txt_age.getText();
    	int age=Integer.parseInt(Age);
    	String Weightatbirth=txt_weight.getText();
    	int weightatbirth=Integer.parseInt(Weightatbirth);
    	
	    	String sql="update CowsModel set cowsid=?,color=?,age=?, weightatbirth=?,date=? where cowsid='"+cowsid+"'";
	    	if(txt_cows.equals("") || color.equals("") || txt_age.equals("") || txt_weight.equals("") || txt_date.getValue().equals("")) {
	    		Alert alert = new Alert(AlertType.WARNING,"You should add all information",javafx.scene.control.ButtonType.OK);
				alert.showAndWait();
	    	}
	    	else {
	    		try {
				st=(PreparedStatement) cnx.prepareStatement(sql);
				st.setInt(1, cowsid);
				st.setString(2,color);
				st.setInt(3, age);
				st.setInt(4,weightatbirth);
				java.util.Date date= java.util.Date.from(txt_date.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
				Date sqldDate=new Date(date.getTime());
				st.setDate(5, sqldDate);
				
			
			    st.execute();
				txt_cows.setText("");
				txt_color.setText("");
				txt_age.setText("");
				txt_date.setValue(null);
				txt_weight.setText("");
				Alert alert = new Alert(AlertType.CONFIRMATION,"cows has been updated",javafx.scene.control.ButtonType.OK);
				alert.showAndWait();
				showCows(); 
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
 	   
	    }

    }
	  
    @FXML
    void deleteCows() {
    	String Cowsid=txt_cows.getText();
    	int cowsid=Integer.parseInt(Cowsid);
    	String color=txt_color.getText();
    	String Age=txt_age.getText();
    	int age=Integer.parseInt(Age);
    	String Weightatbirth=txt_weight.getText();
    	int weightatbirth=Integer.parseInt(Weightatbirth);
    	
	   String sql="delete from CowsModel where  cowsid='"+txt_cows.getText()+"'";
	   if(txt_cows.equals("") || color.equals("") ||txt_age.equals("") || txt_weight.equals("") || txt_date.getValue().equals("")) {
    		Alert alert = new Alert(AlertType.WARNING,"You should add all information",javafx.scene.control.ButtonType.OK);
			alert.showAndWait();
    	}
	   else {
		   try {
		st=(PreparedStatement) cnx.prepareStatement(sql);
		st.executeUpdate();
		txt_cows.setText("");
		txt_color.setText("");
		txt_age.setText("");
		txt_date.setValue(null);
		txt_weight.setText("");
		Alert alert = new Alert(AlertType.CONFIRMATION,"cows has been deleted",javafx.scene.control.ButtonType.OK);
		alert.showAndWait();
		showCows(); 
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

    }
    }
    @FXML
    void clearCows() {
    	txt_cows.setText("");
		txt_color.setText("");
		txt_age.setText("");
		txt_date.setValue(null);
		txt_weight.setText("");

    }
	   
       	
	    
	    public void showCows() {
	    	table_cows.getItems().clear();
	    	String sql="select * from CowsModel";
	    	try {
	    		 st=(PreparedStatement) cnx.prepareStatement(sql);
	    		 result=st.executeQuery();
	    		 while(result.next()) {
	    			data.add(new CowsModel(result.getInt("cowsid"),result.getString("color"),result.getInt("age"),result.getDate("date"),result.getInt("weightatbirth")));
	    		 }
	    		 cln_id.setCellValueFactory(new PropertyValueFactory<CowsModel,Integer>("cowsid"));
	    		 cln_color.setCellValueFactory(new PropertyValueFactory<CowsModel,String>("color"));
	    		 cln_age.setCellValueFactory(new PropertyValueFactory<CowsModel,Integer>("age"));
	    		 cln_date.setCellValueFactory(new PropertyValueFactory<CowsModel,Date>("date"));
	    		 cln_weight.setCellValueFactory(new PropertyValueFactory<CowsModel,Integer>("weightatbirth"));
	    		 table_cows.setItems(data);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	
	    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try {
			cnx=DataBaseConnection.connect();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		showCows();  
		
	}
	

}
