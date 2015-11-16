package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import utilities.ConexaoSQL;
import entities.Conta;

public class ContaDAO {

	private Connection conexao;
	private PreparedStatement declaracao;
	private ResultSet resultado;
	private String nomeUsuario;

	public ContaDAO(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	private boolean existeConta(int id) {
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

	public boolean adicionarConta(Conta conta) {
		if (existeConta(conta.getId()))
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
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}
	
	public List<Conta> buscarContas() {
		String sql = "Select * from \"Conta\" where \"idUsuario\" = ?";
		List<Conta> contas = new ArrayList<Conta>();
		try {
			conexao = ConexaoSQL.getConnection();
			declaracao = conexao.prepareStatement(sql);
			declaracao.setString(1, nomeUsuario);
			resultado = declaracao.executeQuery();
			while (resultado.next())
				contas.add(new Conta(resultado.getString("banco"), resultado.getString("agencia"),
						resultado.getString("numero")));
			resultado.close();
			declaracao.close();
			conexao.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return contas;
	}

	public boolean atualizarConta(int idAntigo, Conta conta) {
		if (!existeConta(idAntigo))
			return false;
		String sql = "Update \"Conta\" set \"idConta\" = ?, banco = ?, agencia = ?, numero = ?  where \"idConta\" = ? and \"idUsuario\" = ?";
		try {
			conexao = ConexaoSQL.getConnection();
			declaracao = conexao.prepareStatement(sql);
			declaracao.setInt(1, conta.getId());
			declaracao.setString(2, conta.getBanco());
			declaracao.setString(3, conta.getAgencia());
			declaracao.setString(4, conta.getNumero());
			declaracao.setInt(5, idAntigo);
			declaracao.setString(6, nomeUsuario);
			declaracao.executeUpdate();
			declaracao.close();
			conexao.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}
	
	public boolean deletarConta(int id) {
		if (!existeConta(id))
			return false;
		String sql = "Delete from \"Conta\" where \"idConta\" = ? and \"idUsuario\" = ?";
		try {
			conexao = ConexaoSQL.getConnection();
			declaracao = conexao.prepareStatement(sql);
			declaracao.setInt(1, id);
			declaracao.setString(2, nomeUsuario);
			declaracao.executeUpdate();
			declaracao.close();
			conexao.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}
}