package controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ResourceBundle;

import Models.DataBaseConnection;
import Models.MilkSalesModel;
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
import javafx.scene.text.Text;

public class MilkSalesController implements Initializable{
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
    private TableColumn<MilkSalesModel , String> cln_cin;

    @FXML
    private TableColumn<MilkSalesModel , Date> cln_date;

    @FXML
    private TableColumn<MilkSalesModel , String> cln_name;

    @FXML
    private TableColumn<MilkSalesModel , Integer> cln_price;

    @FXML
    private TableColumn<MilkSalesModel , Integer> cln_quantity;

    @FXML
    private TableColumn<MilkSalesModel , Integer> cln_total;

    @FXML
    private DatePicker txt_date;

    @FXML
    private TableView<MilkSalesModel> table_milkSales;

    @FXML
    private TextField txt_cin;

    @FXML
    private TextField txt_name;

    @FXML
    private TextField txt_price;

    @FXML
    private TextField txt_quantity;

    @FXML
    private Text txt_total;
    
    ObservableList<MilkSalesModel> data=FXCollections.observableArrayList();
    
    
   


 

    @FXML
    void saveMilk() {
    	String name=txt_name.getText();
    	
    	String CINClient=txt_cin.getText();
    	
    	String quantity=txt_quantity.getText();
    	int Quantity=Integer.parseInt(quantity);
    	
    	String price=txt_price.getText();
    	int Price=Integer.parseInt(price);
    	
    	int total=Quantity*Price;
    	
    
    	
    	String sql="insert into milksales(name,CINClient,Quantity,date,Price,Total) values(?,?,?,?,?,?)";
    	if(name.equals("") || CINClient.equals("") || txt_quantity.getText().equals("")|| txt_price.getText().equals("") || txt_date.getValue().equals("")) {
    		Alert alert = new Alert(AlertType.WARNING,"You should add all information",javafx.scene.control.ButtonType.OK);
			alert.showAndWait();
			
    	}
    	else {
    		try {
			st=(PreparedStatement) cnx.prepareStatement(sql);
			st.setString(1,name);
			
			st.setString(2,CINClient);
			
			st.setInt(3, Quantity);
			
			st.setInt(5, Price);
			st.setInt(6, total);
			
			
			
			java.util.Date date= java.util.Date.from(txt_date.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
			Date sqldDate=new Date(date.getTime());
			st.setDate(4, sqldDate);
			
			st.execute();
			txt_name.setText("");
			
			txt_cin.setText("");
			
			txt_quantity.setText("");
			
			txt_price.setText("");
			
			txt_total.setText("");
			
			txt_date.setValue(null);
			
			
			Alert alert = new Alert(AlertType.CONFIRMATION,"info has been added",javafx.scene.control.ButtonType.OK);
			alert.showAndWait();
			showMilksales() ; 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }
    
}
    @FXML
    void getSelected() {
    	MilkSalesModel milkSalesModel =table_milkSales.getSelectionModel().getSelectedItem();
	   String sql="select * from  milksales where CINClient=?";
	   try {
			st=(PreparedStatement) cnx.prepareStatement(sql);
			st.setString(1,milkSalesModel.getCINClient());
			result=st.executeQuery();
			if(result.next()) {
				txt_name.setText(result.getString("name"));
				txt_cin.setText (result.getString ("CINClient"));
				txt_quantity.setText (result.getString("Quantity"));
				txt_price.setText( result.getString ("Price" ));
				txt_total.setVisible(false);
				Date date=result.getDate("date");
				txt_date.setValue (date.toLocalDate());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  

    }
    
    @FXML
    void editeMilk() {
	     String name=txt_name.getText();
    	
    	String CINClient=txt_cin.getText();
    	
    	String quantity=txt_quantity.getText();
    	int Quantity=Integer.parseInt(quantity);
    	
    	String price=txt_price.getText();
    	int Price=Integer.parseInt(price);
    	
    	int total=Quantity*Price;
    
	    	String sql="update milksales set name=?,CINClient=?,Quantity=?, Price=?,Total=?,date=? where CINClient='"+CINClient+"'";
	    	
	    	if(txt_name.equals("") || txt_cin.equals("") || txt_quantity.equals("") || txt_price.equals("") ||  txt_date.getValue().equals("")) {
	    		Alert alert = new Alert(AlertType.WARNING,"You should add all information",javafx.scene.control.ButtonType.OK);
				alert.showAndWait();
	    	}
	    	else {
	    		try {
	    			st=(PreparedStatement) cnx.prepareStatement(sql);
	    			st.setString(1,name);
	    			
	    			st.setString(2,CINClient);
	    			
	    			st.setInt(3, Quantity);
	    			
	    			st.setInt(4, Price);
	    			
	    			st.setInt(5,total);
	    			
	    			java.util.Date date= java.util.Date.from(txt_date.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
	    			Date sqldDate=new Date(date.getTime());
	    			st.setDate(6, sqldDate);
	    			
	    			st.execute();
	    			txt_name.setText("");
	    			
	    			txt_cin.setText("");
	    			
	    			txt_quantity.setText("");
	    			
	    			txt_price.setText("");
	    			
	    			txt_total.setText("");
	    			
	    			txt_date.setValue(null);
	    			
	    			
	    			Alert alert = new Alert(AlertType.CONFIRMATION,"info has been added",javafx.scene.control.ButtonType.OK);
	    			alert.showAndWait();
	    			
	    			showMilksales() ; 
				} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
 	   
	    }

    }
    

    @FXML
    void deleteMilk() {
    	 String name=txt_name.getText();
     	
     	String CINClient=txt_cin.getText();
     	
     	String quantity=txt_quantity.getText();
     	int Quantity=Integer.parseInt(quantity);
     	
     	String price=txt_price.getText();
     	int Price=Integer.parseInt(price);
     	
    	int total=Quantity*Price;
	   String sql="delete from milksales where  CINClient='"+txt_cin.getText()+"'";
		if(txt_name.equals("") || txt_cin.equals("") || txt_quantity.equals("") || txt_price.equals("") || txt_total.equals("") || txt_date.getValue().equals("")) {
    		Alert alert = new Alert(AlertType.WARNING,"You should add all information",javafx.scene.control.ButtonType.OK);
			alert.showAndWait();
    	}
	  
	   else {
		   try {
		st=(PreparedStatement) cnx.prepareStatement(sql);
		st.executeUpdate();
		
		txt_name.setText("");
		
		txt_cin.setText("");
		
		txt_quantity.setText("");
		
		txt_price.setText("");
		
		txt_total.setText("");
		
		txt_date.setValue(null);
		
		
		Alert alert = new Alert(AlertType.CONFIRMATION,"info has been added",javafx.scene.control.ButtonType.OK);
		alert.showAndWait();
		
		showMilksales() ;
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

    }

    }

    
    
	
    
    
    public void showMilksales() {
    	table_milkSales.getItems().clear();
    	String sql="select * from milksales";
    	try {
    		 st=(PreparedStatement) cnx.prepareStatement(sql);
    		 result=st.executeQuery();
    		 while(result.next()) {
    			data.add(new MilkSalesModel(result.getString("name"),result.getString("CINClient"),result.getInt("Quantity"),result.getInt("Price"),result.getInt("Total"),result.getDate("date")));
    		 }
    		 cln_name.setCellValueFactory(new PropertyValueFactory<MilkSalesModel,String>("name"));
    		 cln_cin.setCellValueFactory(new PropertyValueFactory<MilkSalesModel,String>("CINClient"));
    		 cln_quantity.setCellValueFactory(new PropertyValueFactory<MilkSalesModel,Integer>("Quantity"));
    		 cln_price.setCellValueFactory(new PropertyValueFactory<MilkSalesModel,Integer>("Price"));
    		 cln_total.setCellValueFactory(new PropertyValueFactory<MilkSalesModel,Integer>("Total"));
    		 cln_date.setCellValueFactory(new PropertyValueFactory<MilkSalesModel,Date>("date"));
    		
    		 table_milkSales.setItems(data);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
    
    @FXML
    void clearMilk() {

		txt_name.setText("");
		
		txt_cin.setText("");
		
		txt_quantity.setText("");
		
		txt_price.setText("");
		
		txt_total.setText("");
		
		txt_date.setValue(null);
    	
    	

    }

    
    
    
    
    
    
    
    
    
    
	public void initialize(URL arg0, ResourceBundle arg1) {
		try {
			cnx=DataBaseConnection.connect();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 showMilksales(); 
	
	}

}