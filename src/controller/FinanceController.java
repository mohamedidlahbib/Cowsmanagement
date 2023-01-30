package controller;

import java.net.URL;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;

import Models.DataBaseConnection;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;


public class FinanceController implements Initializable {
	 
	Connection cnx;
	public PreparedStatement st,st1;
	public ResultSet result,result1;
	
    @FXML
    private Label dashbord_IM;

    @FXML
    private Label dashbord_NC;

    @FXML
    private Label dashbord_SM;

    @FXML
    private BarChart<?, ?> dashbord_Stati;
    
    
    
    
    public void dashboardNC() {

        String sql = "SELECT COUNT(cowsid) FROM cowsmodel";

        int nc = 0;

        try {
			cnx=DataBaseConnection.connect();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

        try {

        	st= cnx.prepareStatement(sql);
			result=st.executeQuery();

            if (result.next()) {
                nc = result.getInt("COUNT(cowsid)");
            }

            dashbord_NC.setText(String.valueOf(nc));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    public void dashboardSM() {

        Date date = new Date();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        

        String sql = "SELECT SUM(price) FROM foodmodel WHERE date = '" + sqlDate + "'";

        try {
			cnx=DataBaseConnection.connect();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        double sm = 0;

        try {
        	st= cnx.prepareStatement(sql);
			result=st.executeQuery();
            if (result.next()) {
                sm = result.getDouble("SUM(price)");
            }

            dashbord_SM.setText("$" + String.valueOf(sm));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void dashboardIC() {

    	Date date = new Date();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());

        String sql = "SELECT SUM(Total) FROM milksales WHERE date = '" + sqlDate + "'";

        try {
			cnx=DataBaseConnection.connect();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        double sm = 0;

        try {
        	st= cnx.prepareStatement(sql);
			result=st.executeQuery();
            if (result.next()) {
                sm = result.getDouble("SUM(Total)");
            }

            dashbord_IM.setText("$" + String.valueOf(sm));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    public void dashboardStati() {

    	dashbord_Stati.getData().clear();

        String sql = "SELECT date, SUM(price) FROM foodmodel GROUP BY date ORDER BY TIMESTAMP(date) ASC LIMIT 7";
        String sql1 = "SELECT date, SUM(Total) FROM milksales GROUP BY date ORDER BY TIMESTAMP(date) ASC LIMIT 4";

        try {
			cnx=DataBaseConnection.connect();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        try {

            XYChart.Series chart = new XYChart.Series();
            chart.setName("Spending \t");
            XYChart.Series chart1 = new XYChart.Series();
            chart1.setName("Incomes ");

            st= cnx.prepareStatement(sql);
			result=st.executeQuery();
			st1= cnx.prepareStatement(sql1);
			result1=st1.executeQuery();

            while (result.next()) {

                chart.getData().add(new XYChart.Data(result.getString(1), result.getDouble(2)));

            }
            

            while (result1.next()) {

                chart1.getData().add(new XYChart.Data(result1.getString(1), result1.getDouble(2)));
                
            }


            dashbord_Stati.getData().add(chart);
            dashbord_Stati.getData().add(chart1);

        } catch (Exception e) {
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
		
		dashboardNC();
		dashboardSM();
		dashboardIC();
		dashboardStati();
	}
	
	

}