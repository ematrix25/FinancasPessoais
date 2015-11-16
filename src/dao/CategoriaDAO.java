package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import utilities.ConexaoSQL;
import entities.Categoria;

/**
 * @author Danilo
 */

public class CategoriaDAO {
	private Connection conexao;
	private PreparedStatement declaracao;
	private ResultSet resultado;
	private String nomeUsuario;

	public CategoriaDAO(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	private boolean existeCategoria(String nome) {
		String sql = "Select 1 from \"Categoria\" where nome = ? and \"idUsuario\" = ?";
		boolean existeUsuario = false;
		try {
			conexao = ConexaoSQL.getConnection();
			declaracao = conexao.prepareStatement(sql);
			declaracao.setString(1, nome);
			declaracao.setString(1, nomeUsuario);
			resultado = declaracao.executeQuery();
			if (resultado.next())
				existeUsuario = true;
			resultado.close();
			declaracao.close();
			conexao.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return existeUsuario;
	}
	
	public List<Categoria> buscarCategoria(String nome) {
		String sql = "Select * from \"Categoria\" where \"idUsuario\" = ?";
		List<Categoria> categorias = new ArrayList<Categoria>();
		try {
			conexao = ConexaoSQL.getConnection();
			declaracao = conexao.prepareStatement(sql);
			declaracao.setString(1, nomeUsuario);
			resultado = declaracao.executeQuery();
			while (resultado.next())
				categorias.add(new Categoria(resultado.getString("nome")));
			resultado.close();
			declaracao.close();
			conexao.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return categorias;
	}

	public boolean adicionarCategoria(Categoria categoria) {
		if (existeCategoria(categoria.getNome()))
			return false;
		String sql = "Insert into \"Categoria\" (nome, \"idUsuario\") values (?, ?)";
		try {
			conexao = ConexaoSQL.getConnection();
			declaracao = conexao.prepareStatement(sql);
			declaracao.setString(1, categoria.getNome());
			declaracao.setString(2, nomeUsuario);
			declaracao.executeUpdate();
			declaracao.close();
			conexao.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}
	
	public boolean atualizarCategoria(Categoria categoria) {
		if (!existeCategoria(categoria.getNome()))
			return false;
		String sql = "Update \"Categoria\" set nome = ? where nome = ? and \"idUsuario\" = ?";		
		try {
			conexao = ConexaoSQL.getConnection();
			declaracao = conexao.prepareStatement(sql);
			declaracao.setString(1, categoria.getNome());
			declaracao.setString(2, nomeUsuario);
			declaracao.executeUpdate();
			declaracao.close();
			conexao.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}

	public boolean deletarCategoria(String nome) {
		if (!existeCategoria(nome))
			return false;
		String sql = "Delete from \"Categoria\" where nome = ? and \"idUsuario\" = ?";
		try {
			conexao = ConexaoSQL.getConnection();
			declaracao = conexao.prepareStatement(sql);
			declaracao.setString(1, nome);
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
