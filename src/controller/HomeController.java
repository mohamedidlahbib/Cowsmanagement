package controller;


import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

public class HomeController implements Initializable {
 
	
	
    private Parent fxml;
    @FXML
    private AnchorPane root;
	   @FXML
	    void open_MilkPro(MouseEvent event) {
		   try {
			fxml=FXMLLoader.load(getClass().getResource("/Views/MilkProduction.fxml"));
			root.getChildren().removeAll();
			root.getChildren().setAll(fxml);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	    }

	    @FXML
	    void open_Cows(MouseEvent event) {
	    	 try {
	 			fxml=FXMLLoader.load(getClass().getResource("/Views/Cows.fxml"));
	 			root.getChildren().removeAll();
	 			root.getChildren().setAll(fxml);
	 		} catch (IOException e) {
	 			// TODO Auto-generated catch block
	 			e.printStackTrace();
	 		}

	    }

	  

	    @FXML
	    void open_Finance(MouseEvent event) {
	    	 try {
	 			fxml=FXMLLoader.load(getClass().getResource("/Views/Finance.fxml"));
	 			root.getChildren().removeAll();
	 			root.getChildren().setAll(fxml);
	 		} catch (IOException e) {
	 			// TODO Auto-generated catch block
	 			e.printStackTrace();
	 		}

	    }

	    @FXML
	    void open_Food() {
	    	 try {
	 			fxml=FXMLLoader.load(getClass().getResource("/Views/Food.fxml"));
	 			root.getChildren().removeAll();
	 			root.getChildren().setAll(fxml);
	 		} catch (IOException e) {
	 			// TODO Auto-generated catch block
	 			e.printStackTrace();
	 		}

	    }

	    @FXML
	    void open_Health(MouseEvent event) {
	    	 try {
	 			fxml=FXMLLoader.load(getClass().getResource("/Views/Health.fxml"));
	 			root.getChildren().removeAll();
	 			root.getChildren().setAll(fxml);
	 		} catch (IOException e) {
	 			// TODO Auto-generated catch block
	 			e.printStackTrace();
	 		}

	    }
	    
	    @FXML
	    void open_MilkSales() {
	    	 try {
	 			fxml=FXMLLoader.load(getClass().getResource("/Views/MilkSales.fxml"));
	 			root.getChildren().removeAll();
	 			root.getChildren().setAll(fxml);
	 		} catch (IOException e) {
	 			// TODO Auto-generated catch block
	 			e.printStackTrace();
	 		}

	    }

	
	@FXML
	    void log_out() {
	    	Alert alert = new Alert(AlertType.CONFIRMATION, "LOG OUT " + " ?", ButtonType.OK, ButtonType.CANCEL);
			alert.setTitle("Log out");
			alert.setContentText("Are you sure you want to log out?");
			// option != null.
		      Optional<ButtonType> option = alert.showAndWait();
		      if (option.get() == ButtonType.OK) {
		    	  System.out.println("You successfully logged out!");
		    	  javafx.application.Platform.exit();
		     } else if(option.get() == ButtonType.CANCEL) {
		          System.out.println("Cancelled!");  
		     } else {
		    	 System.out.println("-"); 
		     }
		      
	    }
	
	
	

    


	

	   

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}

}
