package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entities.Categoria;
import utilities.ConexaoSQL;

/**
 * @author Danilo
 */

public class CategoriaDAO {
	private Connection conexao;
	private PreparedStatement declaracao;
	private ResultSet resultado;

	private boolean temCategoria(String nome) {
		String sql = "Select 1 from \"Categoria\" where nome = ?";
		boolean existeUsuario = false;
		try {
			conexao = ConexaoSQL.getConnection();
			declaracao = conexao.prepareStatement(sql);
			declaracao.setString(1, nome);
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

	public boolean addCategoria(Categoria categoria) {
		if (temCategoria(categoria.getNome()))
			return false;
		String sql = "Insert into \"Categoria\" (nome) values (?)";
		try {
			conexao = ConexaoSQL.getConnection();
			declaracao = conexao.prepareStatement(sql);
			declaracao.setString(1, categoria.getNome());
			declaracao.executeUpdate();
			declaracao.close();
			conexao.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}
	
	public boolean setCategoria(Categoria categoria) {
		String sql = "Update \"Categoria\" set nome = ? where nome = ?";		
		try {
			conexao = ConexaoSQL.getConnection();
			declaracao = conexao.prepareStatement(sql);
			declaracao.setString(1, categoria.getNome());
			declaracao.executeUpdate();
			declaracao.close();
			conexao.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}
	
	
}
