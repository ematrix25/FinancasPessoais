package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class ConexaoSQL {
	private static final String BD = "financas";
	private static final String USER = "postgres";
	private static final String PASSWORD = "postgres";
	
	public static Connection getConnection() throws SQLException{
		Connection conection = null;
		try {  
			Class.forName("org.postgresql.Driver");                               
			conection =  DriverManager.getConnection("jdbc:postgresql://localhost:5432/"+BD, 
					USER, PASSWORD);                           
		} catch (ClassNotFoundException e) {  
			JOptionPane.showMessageDialog(null, e.getMessage());              
		}
		return conection;          
	}   
}
