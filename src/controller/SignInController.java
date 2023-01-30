package controller;


import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;
import java.util.ResourceBundle;

import Models.DataBaseConnection;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Duration;



public class SignInController implements Initializable{
	
	Connection cnx;
	public PreparedStatement st;
	public ResultSet result;
	
	@FXML
    private BorderPane borderpane;
	@FXML
    private Stage stage;
    @FXML
    private Scene scene;
    @FXML
    private Parent root;

    @FXML
    private AnchorPane anchorpane;
    
    @FXML
    private Button SignIn;
    
    @FXML
    private Label loginSuccess;
    
    @FXML
    private PasswordField txt_pass;

    @FXML
    private TextField txt_username;
    
    @FXML
    void open_SignUp(ActionEvent event) {
       
       
            	try {
                    root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/Views/SignUp.fxml")));
                    stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    scene = new Scene(root);
                    stage.setScene(scene);
                    stage.centerOnScreen();
                    stage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
      
            

      
    }
    @FXML
    void openHome(ActionEvent event) {
    	
         String verifyLogin = "SELECT count(1) FROM users WHERE user_fullName = '"+txt_username.getText()+"'AND user_password ='"
                 +txt_pass.getText()+"'";
         try {
             Statement statement = cnx.createStatement();
             ResultSet resultSet = statement.executeQuery(verifyLogin);
             while(resultSet.next()){
                 if(resultSet.getInt(1) == 1){
                		Alert alert = new Alert(AlertType.CONFIRMATION,"Login Successful!",javafx.scene.control.ButtonType.OK);
        				alert.showAndWait();
                     txt_username.getStyleClass().add("loginSuccess");
                     txt_pass.getStyleClass().add("loginSuccess");
                     anchorpane.getScene().getWindow().hide();
                     Stage Home=new Stage();
                     Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/Views/Home.fxml")));
                     Scene scene = new Scene(root);
                     Home.setScene(scene);
                     Home.setResizable(false);
                  
                     Home.show();
                 }else{
                		Alert alert = new Alert(AlertType.WARNING,"Login Failed!",javafx.scene.control.ButtonType.OK);
            			alert.showAndWait();
                     txt_username.getStyleClass().add("loginFailed");
                     txt_pass.getStyleClass().add("loginFailed");
                     Alert failAlert = new Alert(Alert.AlertType.ERROR);
                     failAlert.setHeaderText("Error trying to Login");
                     failAlert.setContentText("Wrong username or password!\nCheck if User is selected");
                     failAlert.showAndWait();
                 }
             }
         } catch (SQLException | IOException e) {
             e.printStackTrace();
             e.getCause();
         }
    }

    @FXML
    void IsPasswordCorrect(ActionEvent event) {
    	if(!(txt_username.getText().isEmpty() && txt_pass.getText().isEmpty())){
         
            String verifyLogin = "SELECT count(1) FROM users WHERE user_fullName = '"+txt_username.getText()+"'AND user_password ='"
                    +txt_pass.getText()+"'";
            try {
                Statement statement = cnx.createStatement();
                ResultSet resultSet = statement.executeQuery(verifyLogin);
                while (resultSet.next()) {
                    if (resultSet.getInt(1) == 1) {
                        loginSuccess.setText("Login Successful!");
                        loginSuccess.setStyle("-fx-text-fill: #3AC666; -fx-font-size: 18px; -fx-font-family: Calibri");
                        txt_username.getStyleClass().add("loginSuccess");
                        txt_pass.getStyleClass().add("loginSuccess");
                        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/interfaces/Home.fxml")));
                    }
                    else{
                        loginSuccess.setText("Login Failed!");
                        loginSuccess.setStyle("-fx-text-fill: #EB3A0B; -fx-font-size: 18px; -fx-font-family: Calibri");
                        txt_username.getStyleClass().add("loginFailed");
                        txt_pass.getStyleClass().add("loginFailed");
                        Alert failAlert = new Alert(Alert.AlertType.ERROR);
                        failAlert.setHeaderText("Error trying to Login");
                        failAlert.setContentText("Wrong username or password!\nCheck if User is selected");
                        failAlert.showAndWait();
                    }
                }
            }catch (SQLException | IOException e){
                e.printStackTrace();
                e.getCause();
            }
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
		
	}
    
}    
