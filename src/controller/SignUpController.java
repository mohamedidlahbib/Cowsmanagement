package controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import Models.DataBaseConnection;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class SignUpController implements Initializable {
	
	Connection cnx;
	public PreparedStatement st;
	public ResultSet result;
	
    @FXML
    private AnchorPane anchorpane;
    
    @FXML
    private Label registrationSuccess;
    
    @FXML
    private Label passwordCheck;

    @FXML
    private BorderPane borderpane;

    @FXML
    private Button btn_create;

    @FXML
    private Hyperlink btn_signIn;

    @FXML
    private PasswordField confirmPassword;

    @FXML
    private TextField txt_email;

    @FXML
    private TextField txt_fullname;

    @FXML
    private PasswordField txt_password;
    
    @FXML
    private Stage stage;
    @FXML
    private Scene scene;
    @FXML
    private Parent root;
    
    @FXML
    void open_SignIn() {
    	borderpane.getScene().getWindow().hide();
		Stage SignUp=new Stage();
		try {
			Parent root=FXMLLoader.load(getClass().getResource("/Views/SignIn.fxml"));
			Scene scene=new Scene(root);
			SignUp.setScene(scene);
			SignUp.show();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

    }
    
    @FXML
    void CreateAccountbtn(ActionEvent event) {
    	CreateAccount();
    }
    	
   
    public void CreateAccount() {
    	if(txt_fullname.getText().isEmpty() || txt_email.getText().isEmpty() || txt_password.getText().isEmpty()) {
    		Alert fieldsAlert = new Alert(Alert.AlertType.ERROR);
    		fieldsAlert.setHeaderText("Fill out all the fields");
            fieldsAlert.setContentText("Double check and see if all fields are completed");
            fieldsAlert.showAndWait();
    	}else {
    		if(txt_password.getText().equals(confirmPassword.getText())) {
    			
    			Alert alert = new Alert(AlertType.CONFIRMATION,"User registration successful!",javafx.scene.control.ButtonType.OK);
				alert.showAndWait();
				
				borderpane.getScene().getWindow().hide();
				Stage SignUp=new Stage();
				try {
					Parent root=FXMLLoader.load(getClass().getResource("/Views/SignIn.fxml"));
					Scene scene=new Scene(root);
					SignUp.setScene(scene);
					SignUp.show();
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
               
                
                String fullname = txt_fullname.getText();
                String emailadress = txt_email.getText();
                String password = txt_password.getText();
                
                
                String insertFields = "INSERT INTO users(user_fullName, email_address, user_password) VALUES ('";
                String insertValues = fullname + "', '"+ emailadress + "', '"+ password + "')";
                String insertToDb = insertFields + insertValues;
                try {
                    Statement statement = cnx.createStatement();
                    statement.executeUpdate(insertToDb);
                    cnx.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    e.getCause();
                }
                
                
    		}else if(!(txt_password.getText().equals(confirmPassword.getText()))){
                passwordCheck.setText("Passwords do not match!");
                passwordCheck.setStyle("-fx-font-size: 13px; -fx-text-fill: #EB3A0B");
                txt_password.setStyle("-fx-border-color: #EB3A0B; -fx-background-color: transparent;");
                confirmPassword.setStyle("-fx-border-color: #EB3A0B; -fx-background-color: transparent;");
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

