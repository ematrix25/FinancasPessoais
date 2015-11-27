package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entities.ItemDeExtrato;
import entities.TipoItemDeExtrato;
import utilities.ConexaoSQL;

/**
 * @author Emanuel
 *
 */
public class ItemDeExtratoDAO {
	private Connection conexao;
	private PreparedStatement declaracao;
	private ResultSet resultado;
	private long idExtrato;

	public ItemDeExtratoDAO(long idExtrato) {
		this.idExtrato = idExtrato;
	}

	/**
	 * @param idExtrato
	 *            the idExtrato to set
	 */
	public void setIdExtrato(long idExtrato) {
		this.idExtrato = idExtrato;
	}

	private boolean existe(long id) {
		String sql = "Select 1 from \"ItemDeExtrato\" where \"idItemDeExtrato\" = ? and \"idExtrato\" = ?";
		boolean existe = false;
		try {
			conexao = ConexaoSQL.getConnection();
			declaracao = conexao.prepareStatement(sql);
			declaracao.setLong(1, id);
			declaracao.setLong(2, idExtrato);
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

	public boolean cadastrar(ItemDeExtrato itemDeExtrato, String idCategoria) {
		if (existe(itemDeExtrato.getId()))
			return false;
		String sql = "Insert into \"ItemDeExtrato\" (titulo, valor, observacao, dia, ocorrencia, tipo, \"idItemDeExtrato\", \"idExtrato\", \"idCategoria\") values (?, ?, ?, ?, ?, CAST(? as tipoitemdeextrato), ?, ?, ?)";
		try {
			conexao = ConexaoSQL.getConnection();
			declaracao = conexao.prepareStatement(sql);
			declaracao.setString(1, itemDeExtrato.getTitulo());
			declaracao.setFloat(2, itemDeExtrato.getValor());
			declaracao.setString(3, itemDeExtrato.getObservacao());
			declaracao.setInt(4, itemDeExtrato.getDia());
			declaracao.setInt(5, itemDeExtrato.getOcorrencia());
			declaracao.setObject(6, itemDeExtrato.getTipo().toString());
			declaracao.setLong(7, itemDeExtrato.getId());
			declaracao.setLong(8, idExtrato);
			declaracao.setString(9, idCategoria);
			declaracao.executeUpdate();
			declaracao.close();
			conexao.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}

	public List<ItemDeExtrato> buscar() {
		String sql = "Select * from \"ItemDeExtrato\" where \"idExtrato\" = ? order by dia";
		List<ItemDeExtrato> itemDeExtratos = new ArrayList<ItemDeExtrato>();
		try {
			conexao = ConexaoSQL.getConnection();
			declaracao = conexao.prepareStatement(sql);
			declaracao.setLong(1, idExtrato);
			resultado = declaracao.executeQuery();
			while (resultado.next())
				itemDeExtratos.add(new ItemDeExtrato(resultado.getString("titulo"), resultado.getFloat("valor"),
						resultado.getString("observacao"), resultado.getInt("dia"), resultado.getInt("ocorrencia"),
						TipoItemDeExtrato.valueOf(resultado.getString("tipo")), resultado.getLong("idExtrato")));
			resultado.close();
			declaracao.close();
			conexao.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return itemDeExtratos;
	}

	public boolean atualizar(long idAntigo, ItemDeExtrato itemDeExtrato, String idCategoria) {
		if (!existe(idAntigo))
			return false;
		String sql = "Update \"ItemDeExtrato\" set \"idItemDeExtrato\" = ?, titulo = ?, valor = ?, observacao = ?, dia = ?, ocorrencia = ?, tipo = CAST(? as tipoitemdeextrato), \"idExtrato\" = ?, \"idCategoria\" = ? where \"idItemDeExtrato\" = ? and \"idExtrato\" = ?";
		try {
			conexao = ConexaoSQL.getConnection();
			declaracao = conexao.prepareStatement(sql);
			declaracao.setLong(1, itemDeExtrato.getId());
			declaracao.setString(2, itemDeExtrato.getTitulo());
			declaracao.setFloat(3, itemDeExtrato.getValor());
			declaracao.setString(4, itemDeExtrato.getObservacao());
			declaracao.setInt(5, itemDeExtrato.getDia());
			declaracao.setInt(6, itemDeExtrato.getOcorrencia());
			declaracao.setObject(7, itemDeExtrato.getTipo().toString());
			declaracao.setLong(8, itemDeExtrato.getIdExtrato());
			declaracao.setString(9, idCategoria);
			declaracao.setLong(10, idAntigo);
			declaracao.setLong(11, idExtrato);
			declaracao.executeUpdate();
			declaracao.close();
			conexao.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}

	public boolean remover(long id) {
		if (!existe(id))
			return false;
		String sql = "Delete from \"ItemDeExtrato\" where \"idItemDeExtrato\" = ? and \"idExtrato\" = ?";
		try {
			conexao = ConexaoSQL.getConnection();
			declaracao = conexao.prepareStatement(sql);
			declaracao.setLong(1, id);
			declaracao.setLong(2, idExtrato);
			declaracao.executeUpdate();
			declaracao.close();
			conexao.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}
}
