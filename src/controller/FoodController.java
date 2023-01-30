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
import Models.FoodModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ComboBoxBase;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Labeled;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class FoodController implements Initializable {
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
    private ComboBox<String> selectChoise;

    @FXML
    private TableColumn<FoodModel,Float> cln_Price;

    @FXML
    private TableColumn<FoodModel,Float> cln_Quantity;

    @FXML
    private TableColumn<FoodModel,Date> cln_date;

    @FXML
    private TableColumn<FoodModel,String> cln_type;
    
    @FXML
    private TableColumn<FoodModel,Integer> cln_id;

    @FXML
    private TableView<FoodModel> table_food;

    @FXML
    private TextField txt_Price;
    
    @FXML
    private TextField txt_id;

    @FXML
    private TextField txt_Quantity;

    @FXML
    private DatePicker txt_date;
    
    ObservableList<FoodModel> data=FXCollections.observableArrayList();


   // function add information of food 
    @FXML
    void saveFood() {
    	
    	String iD=txt_id.getText();
    	int id=Integer.parseInt(iD);
    	String type=selectChoise.getValue();
    	String quant=txt_Quantity.getText();
    	float quantity=Float.parseFloat(quant);
    	String PRICE=txt_Price.getText();
    	float price=Float.parseFloat(PRICE);
    	
    	
    	String sql="insert into FoodModel(id,type,date,quantity,price) values(?,?,?,?,?)";
    	  if(txt_id.equals("")||txt_Price.equals("") ||selectChoise.equals("")|| txt_Quantity.equals("") || txt_date.getValue().equals("")) {
      		Alert alert = new Alert(AlertType.WARNING,"You should add all information",javafx.scene.control.ButtonType.OK);
  			alert.showAndWait();
      	}
    	  else {
    		  try {
			st=(PreparedStatement) cnx.prepareStatement(sql);
			st.setInt(1,id);
			st.setString(2,(String) selectChoise.getSelectionModel().getSelectedItem());
			java.util.Date date= java.util.Date.from(txt_date.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
			Date sqldDate=new Date(date.getTime());
			st.setDate(3,sqldDate);
			st.setFloat(4,quantity);
			st.setFloat(5, price);
			st.execute();
			txt_id.setText("");
			selectChoise.setItems (FXCollections.observableArrayList (""));
			selectType();
			txt_id.setText("");
			txt_date.setValue(null);
			txt_Price.setText("");
			txt_Quantity.setText("");
			Alert alert = new Alert(AlertType.CONFIRMATION,"cows has been added",javafx.scene.control.ButtonType.OK);
			alert.showAndWait();
			showTableFood(); 
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	  }
    		

    }
 // function getInfo for select
    @FXML
    void getInformation() {
    	 FoodModel Foodmodel =table_food.getSelectionModel().getSelectedItem();
    	   String sql="select * from  Foodmodel where id=?";
    	   try {
  			st=(PreparedStatement) cnx.prepareStatement(sql);
  			st.setInt(1,Foodmodel.getId());
  			result=st.executeQuery();
  			if(result.next()) {
  				txt_id.setText(result.getString("id"));
  				txt_Price.setText(result.getString("price"));
  				selectChoise.setValue(result.getString("type"));
  				txt_Quantity.setText (result.getString ("quantity"));
  				
  				Date date=result.getDate("date");
  				txt_date.setValue (date.toLocalDate());
  			}
  		
  		} catch (SQLException e) {
  			// TODO Auto-generated catch block
  			e.printStackTrace();
  		}  

    }
    @FXML
    void editeFood() {

    	String iD=txt_id.getText();
    	int id=Integer.parseInt(iD);
    	String type=selectChoise.getValue();
    	String quant=txt_Quantity.getText();
    	float quantity=Float.parseFloat(quant);
    	String PRICE=txt_Price.getText();
    	float price=Float.parseFloat(PRICE);
    	
    	
    	String sql="update FoodModel set id=?,type=?,date=?,quantity=?,price=? where id='"+id+"'";
    	  if(txt_id.equals("")||txt_Price.equals("") ||selectChoise.equals("")|| txt_Quantity.equals("") || txt_date.getValue().equals("")) {
      		Alert alert = new Alert(AlertType.WARNING,"You should add all information",javafx.scene.control.ButtonType.OK);
  			alert.showAndWait();
      	}
    	  else {
    		  try {
			st=(PreparedStatement) cnx.prepareStatement(sql);
			st.setInt(1, id);
			st.setString(2,(String) selectChoise.getSelectionModel().getSelectedItem());
			java.util.Date date= java.util.Date.from(txt_date.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
			Date sqldDate=new Date(date.getTime());
			st.setDate(3,sqldDate);
			st.setFloat(4,quantity);
			st.setFloat(5, price);
			st.execute();
			txt_id.setText("");
			selectChoise.setItems (FXCollections.observableArrayList (""));
			selectType();
			txt_date.setValue(null);
			txt_Price.setText("");
			txt_Quantity.setText("");
			Alert alert = new Alert(AlertType.CONFIRMATION,"cows has been added",javafx.scene.control.ButtonType.OK);
			alert.showAndWait();
			showTableFood(); 
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	  }

    }
    @FXML
    void deleteFood() {
    	String iD=txt_id.getText();
    	int id=Integer.parseInt(iD);
    	String type=selectChoise.getValue();
    	String quant=txt_Quantity.getText();
    	float quantity=Float.parseFloat(quant);
    	String PRICE=txt_Price.getText();
    	float price=Float.parseFloat(PRICE);
    	
    	
    	String sql="delete from FoodModel where id='"+txt_id.getText()+"'";
    	  if(txt_id.equals("")||txt_Price.equals("") ||selectChoise.equals("")|| txt_Quantity.equals("") || txt_date.getValue().equals("")) {
      		Alert alert = new Alert(AlertType.WARNING,"You should add all information",javafx.scene.control.ButtonType.OK);
  			alert.showAndWait();
      	}
    	  else {
    		try {
			st=(PreparedStatement) cnx.prepareStatement(sql);
			st.executeUpdate();  			
			txt_id.setText("");
  			selectChoise.setItems (FXCollections.observableArrayList (""));
  			selectType();
  			txt_id.setText("");
  			txt_date.setValue(null);
  			txt_Price.setText("");
  			txt_Quantity.setText("");
  			Alert alert = new Alert(AlertType.CONFIRMATION,"cows has been added",javafx.scene.control.ButtonType.OK);
			alert.showAndWait();
  			showTableFood(); 
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		
    		  
    	  }

    }
    
    @FXML
    void clearFood() {
    	
    	txt_id.setText("");
		selectChoise.setItems (FXCollections.observableArrayList (""));
		selectType();
		txt_id.setText("");
		txt_date.setValue(null);
		txt_Price.setText("");
		txt_Quantity.setText("");
    	

    }



    
    
    @FXML
    void selectType() {
    	selectChoise.setItems (FXCollections.observableArrayList ("Feed","Straw"));
    	selectChoise.getValue();
    	
    	

    }
    public void showTableFood() {
    	table_food.getItems().clear();
    	String sql="select * from FoodModel";
    	try {
    		 st=(PreparedStatement) cnx.prepareStatement(sql);
    		 result=st.executeQuery();
    		 while(result.next()) {
    			data.add(new FoodModel(result.getInt("id"),result.getDate("date"),result.getString("type"),result.getFloat("quantity"),result.getFloat("price")));
    		 }
    		 cln_id.setCellValueFactory(new PropertyValueFactory<FoodModel,Integer>("id"));
    		 cln_date.setCellValueFactory(new PropertyValueFactory<FoodModel,Date>("date"));
    		 cln_type.setCellValueFactory(new PropertyValueFactory<FoodModel,String>("type"));
    		 cln_Quantity.setCellValueFactory(new PropertyValueFactory<FoodModel,Float>("quantity"));
    		 cln_Price.setCellValueFactory(new PropertyValueFactory<FoodModel,Float>("price"));
    		 table_food.setItems(data);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
	

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		 
		try {
			cnx=DataBaseConnection.connect();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		showTableFood();
         selectType();
    	
		
	}
	

}
