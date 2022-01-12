package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class MainApp {
	public static void main(String[] args){
		String url = "jdbc:sqlserver://DESKTOP-AS6FKGO\\SQLEXPRESS:1433;databaseName=ProiectBDMiron;integratedSecurity=true";
		try {
			Connection conn = DriverManager.getConnection(url);
			Login conectare = new Login(conn);
			conectare.setVisible(true);
           
			System.out.println("Connection Successful!");
            }
        catch (SQLException e){
           e.printStackTrace();
        }
	}
}
