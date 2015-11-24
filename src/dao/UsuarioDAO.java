package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import utilities.ConexaoSQL;
import entities.Usuario;

/**
 * @author Emanuel
 *
 */
public class UsuarioDAO {
	private Connection conexao;
	private PreparedStatement declaracao;
	private ResultSet resultado;

	private boolean existe(String nome) {
		String sql = "Select 1 from \"Usuario\" where nome = ?";
		boolean existe = false;
		try {
			conexao = ConexaoSQL.getConnection();
			declaracao = conexao.prepareStatement(sql);
			declaracao.setString(1, nome);
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

	public boolean cadastrar(Usuario usuario) {
		if (existe(usuario.getNome()))
			return false;
		String sql = "Insert into \"Usuario\" (nome, senha, email) values (?, ?, ?)";
		try {
			conexao = ConexaoSQL.getConnection();
			declaracao = conexao.prepareStatement(sql);
			declaracao.setString(1, usuario.getNome());
			declaracao.setString(2, usuario.getSenha());
			declaracao.setString(3, usuario.getEmail());
			declaracao.executeUpdate();
			declaracao.close();
			conexao.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}

	public Usuario buscar(String nome) {
		String sql = "Select * from \"Usuario\" where nome = ?";
		Usuario usuario = null;
		try {
			conexao = ConexaoSQL.getConnection();
			declaracao = conexao.prepareStatement(sql);
			declaracao.setString(1, nome);
			resultado = declaracao.executeQuery();
			if (resultado.next())
				usuario = new Usuario(nome, resultado.getString("senha"), resultado.getString("email"));
			else
				usuario = new Usuario();
			resultado.close();
			declaracao.close();
			conexao.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return usuario;
	}

	public boolean atualizar(Usuario usuario) {
		if (!existe(usuario.getNome()))
			return false;
		String sql = "Update \"Usuario\" set senha = ? where nome = ?";		
		try {
			conexao = ConexaoSQL.getConnection();
			declaracao = conexao.prepareStatement(sql);
			declaracao.setString(1, usuario.getSenha());
			declaracao.setString(2, usuario.getNome());
			declaracao.executeUpdate();
			declaracao.close();
			conexao.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}
}