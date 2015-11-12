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

	private boolean temUsuario(String nome) {
		String sql = "Select 1 from \"Usuario\" where nome = ?";
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

	public boolean addUsuario(Usuario usuario) {
		if (temUsuario(usuario.getNome()))
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

	// Falta desenvolver
	public Usuario getUsuario(String nome) {
		String sql = "Select * from \"Usuario\" where nome = ?";
		Usuario usuario = null;
		try {
			conexao = ConexaoSQL.getConnection();
			declaracao = conexao.prepareStatement(sql);
			declaracao.setString(1, nome);
			resultado = declaracao.executeQuery();
			if (resultado.next())
				usuario = new Usuario(nome, resultado.getString("senha"));
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

	// Falta desenvolver
	public boolean setUsuario(Usuario usuario) {
		return true;
	}
}
