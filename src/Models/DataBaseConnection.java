package Models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnection {

        static String databaseUser = "root";
        static String databasePassword = "";
        static String url = "jdbc:mysql://localhost:3306/cowsmanagement";
	    
        private DataBaseConnection() {
        	
        }
	    
	    public static Connection connect()throws SQLException {
	    	
	    	return DriverManager.getConnection(url, databaseUser, databasePassword);
	    }

	    
	    
	    
	}

