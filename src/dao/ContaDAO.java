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

	private boolean existe(int id) {
		String sql = "Select 1 from \"Conta\" where \"idConta\" = ? and \"idUsuario\" = ?";
		boolean existe = false;
		try {
			conexao = ConexaoSQL.getConnection();
			declaracao = conexao.prepareStatement(sql);
			declaracao.setInt(1, id);
			declaracao.setString(2, nomeUsuario);
			resultado = declaracao.executeQuery();
			if (resultado.next())
				existe = true;
			resultado.close();
			declaracao.close();
			conexao.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return existe;
	}

	public boolean cadastrar(Conta conta) {
		if (existe(conta.getId()))
			return false;
		String sql = "Insert into \"Conta\" (banco, agencia, numero, saldo, \"idConta\", \"idUsuario\") values (?, ?, ?, ?, ?, ?)";
		try {
			conexao = ConexaoSQL.getConnection();
			declaracao = conexao.prepareStatement(sql);
			declaracao.setString(1, conta.getBanco());
			declaracao.setString(2, conta.getAgencia());
			declaracao.setString(3, conta.getNumero());
			declaracao.setFloat(4, conta.getSaldo());
			declaracao.setInt(5, conta.getId());
			declaracao.setString(6, nomeUsuario);
			declaracao.executeUpdate();
			declaracao.close();
			conexao.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}

	public List<Conta> buscar() {
		String sql = "Select * from \"Conta\" where \"idUsuario\" = ?";
		List<Conta> contas = new ArrayList<Conta>();
		try {
			conexao = ConexaoSQL.getConnection();
			declaracao = conexao.prepareStatement(sql);
			declaracao.setString(1, nomeUsuario);
			resultado = declaracao.executeQuery();
			while (resultado.next())
				contas.add(new Conta(resultado.getString("banco"), resultado.getString("agencia"),
						resultado.getString("numero"), resultado.getFloat("saldo")));
			resultado.close();
			declaracao.close();
			conexao.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return contas;
	}

	public boolean atualizar(int idAntigo, Conta conta) {
		if (!existe(idAntigo))
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

	public boolean atualizarSaldo(Conta conta) {
		if (!existe(conta.getId()))
			return false;
		String sql = "Update \"Conta\" set saldo = ?  where \"idConta\" = ? and \"idUsuario\" = ?";
		try {
			conexao = ConexaoSQL.getConnection();
			declaracao = conexao.prepareStatement(sql);
			declaracao.setFloat(1, conta.getSaldo());
			declaracao.setInt(2, conta.getId());
			declaracao.setString(3, nomeUsuario);
			declaracao.executeUpdate();
			declaracao.close();
			conexao.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}

	public boolean remover(int id) {
		if (!existe(id))
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