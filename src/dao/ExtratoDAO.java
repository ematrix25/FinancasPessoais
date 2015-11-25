package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entities.Extrato;
import utilities.ConexaoSQL;

/**
 * @author Emanuel
 *
 */
public class ExtratoDAO {
	private Connection conexao;
	private PreparedStatement declaracao;
	private ResultSet resultado;
	private int idConta;

	public ExtratoDAO(int idConta) {
		this.idConta = idConta;
	}

	private boolean existe(int id) {
		String sql = "Select 1 from \"Extrato\" where \"idExtrato\" = ? and \"idConta\" = ?";
		boolean existe = false;
		try {
			conexao = ConexaoSQL.getConnection();
			declaracao = conexao.prepareStatement(sql);
			declaracao.setInt(1, id);
			declaracao.setInt(2, idConta);
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

	public boolean cadastrar(Extrato extrato) {
		if (existe(extrato.getId()))
			return false;
		String sql = "Insert into \"Extrato\" (mes, ano, \"valorInicial\", \"valorFinal\", \"idExtrato\", \"idConta\") values (?, ?, ?, ?, ?, ?)";
		try {
			conexao = ConexaoSQL.getConnection();
			declaracao = conexao.prepareStatement(sql);
			declaracao.setInt(1, extrato.getMes());
			declaracao.setInt(2, extrato.getAno());
			declaracao.setFloat(3, extrato.getValorInicial());
			declaracao.setFloat(4, extrato.getValorFinal());
			declaracao.setInt(5, extrato.getId());
			declaracao.setInt(6, idConta);
			declaracao.executeUpdate();
			declaracao.close();
			conexao.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}

	public List<Extrato> buscar() {
		String sql = "Select * from \"Extrato\" where \"idConta\" = ? order by \"idExtrato\" desc";
		List<Extrato> extratos = new ArrayList<Extrato>();
		try {
			conexao = ConexaoSQL.getConnection();
			declaracao = conexao.prepareStatement(sql);
			declaracao.setInt(1, idConta);
			resultado = declaracao.executeQuery();
			while (resultado.next())
				extratos.add(new Extrato(resultado.getInt("mes"), resultado.getInt("ano"),
						resultado.getFloat("\"valorInicial\""), resultado.getFloat("\"valorFinal\"")));
			resultado.close();
			declaracao.close();
			conexao.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return extratos;
	}
	
	public boolean atualizar(int idAntigo, Extrato extrato) {
		if (!existe(idAntigo))
			return false;
		String sql = "Update \"Extrato\" set \"idExtrato\" = ?, mes = ?, ano = ?, \"valorInicial\" = ?, \"valorFinal\" = ? where \"idExtrato\" = ? and \"idConta\" = ?";
		try {
			conexao = ConexaoSQL.getConnection();
			declaracao = conexao.prepareStatement(sql);
			declaracao.setInt(1, extrato.getId());
			declaracao.setInt(2, extrato.getMes());
			declaracao.setInt(3, extrato.getAno());
			declaracao.setFloat(4, extrato.getValorInicial());
			declaracao.setFloat(5, extrato.getValorFinal());
			declaracao.setInt(6, idAntigo);
			declaracao.setInt(7, idConta);
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
		String sql = "Delete from \"Extrato\" where \"idExtrato\" = ? and \"idConta\" = ?";
		try {
			conexao = ConexaoSQL.getConnection();
			declaracao = conexao.prepareStatement(sql);
			declaracao.setInt(1, id);
			declaracao.setInt(2, idConta);
			declaracao.executeUpdate();
			declaracao.close();
			conexao.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}
}
