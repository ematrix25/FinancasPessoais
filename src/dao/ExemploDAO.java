package dao;
//Exemplo de DAO
/*
package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import util.ConexaoUtil;
import entity.Cliente;

public class ClienteDAO {
	Connection connection;
	PreparedStatement statement;
	
	public void addCliente(Cliente cliente) throws SQLException {
	
		String sql = "Insert into \"Clientes\" (cpf, nome, telefone, email, \"dataNascimento\", cnh, regular) values ( ?, ?, ?, ?, ?, ?, ?)";
		connection = ConexaoUtil.getConnection();
		statement = connection.prepareStatement(sql);
		statement.setString(1,cliente.getCpf());
		statement.setString(2,cliente.getNome());
		statement.setString(3,cliente.getTelefone());
		statement.setString(4,cliente.getEmail());
		statement.setDate(5,cliente.getDataCorreta());
		statement.setString(6,cliente.getCnh());
		statement.setBoolean(7,true);
		statement.executeUpdate();
		statement.close();
		connection.close();
	}	
	
	@SuppressWarnings("unused")
	private Date converteData(String data){
		return Date.valueOf(data);		
	}
	
	public boolean existCliente(String cpf){
		String sql = "Select 1 from \"Clientes\" where cpf =?";
		try {
			connection = ConexaoUtil.getConnection();
			statement = connection.prepareStatement(sql);
			statement.setString(1, cpf);
			ResultSet result = statement.executeQuery();
			if (result.next()) {
				return true;
			}
				
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage()); 
		}
		finally{
			try {
				if (connection != null)
					connection.close();
				if (statement != null)
					statement.close();
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
			}
		}
		return false;
	}
}
*/