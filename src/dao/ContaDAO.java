package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entities.Conta;
import utilities.ConexaoSQL;

public class ContaDAO {

	private Connection conexao;
	private PreparedStatement declaracao;
	private ResultSet resultado;
	private String nomeUsuario;
	
	public ContaDAO(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}
	
	private boolean temConta(int id){
		String sql = "Select 1 from \"Conta\" where \"idConta\" = ? and \"idUsuario\" = ?";
		boolean existeConta = false;
		try {
			conexao = ConexaoSQL.getConnection();
			declaracao = conexao.prepareStatement(sql);
			declaracao.setInt(1, id);
			declaracao.setString(2, nomeUsuario);
			resultado = declaracao.executeQuery();
			if (resultado.next())
				existeConta = true;
			resultado.close();
			declaracao.close();
			conexao.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return existeConta;
	}
	
	public boolean addConta(Conta conta){
		if (temConta(conta.getId()))
			return false;
		String sql = "Insert into \"Conta\" (banco, agencia, numero, \"idConta\", \"idUsuario\") values (?, ?, ?, ?, ?)";
		try {
			conexao = ConexaoSQL.getConnection();
			declaracao = conexao.prepareStatement(sql);
			declaracao.setString(1, conta.getBanco());
			declaracao.setString(2, conta.getAgencia());
			declaracao.setString(3, conta.getNumero());
			declaracao.setInt(4, conta.getId());
			declaracao.setString(5, nomeUsuario);
			declaracao.executeUpdate();
			declaracao.close();
			conexao.close();
		} catch (SQLException e){
			e.printStackTrace();
		}
		return true;
	}
	
	/*public Conta getConta(int id){
		String sql = "Select * from \"Conta\" where \"idConta\" = ?";
		Conta conta = null;
		try {
			conexao = ConexaoSQL.getConnection();
			declaracao = conexao.prepareStatement(sql);
			declaracao.setInt(1, id);
			resultado = declaracao.executeQuery();
			if (resultado.next())
				conta = new Conta(id, resultado.getString("banco"))
			
		} catch (SQLException e){
			e.printStackTrace();
		}
		return conta;
	}*/
	
	public boolean setConta(Conta conta){
		return false;
	}
}
