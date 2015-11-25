package controllers;

import java.util.List;
import dao.ItemDeExtratoDAO;
import entities.ItemDeExtrato;

/**
 * @author Danilo
 */
public class ItemDeExtratoCon {
	private ItemDeExtratoDAO itemDeExtratoDAO;

	public boolean cadastrar(ItemDeExtrato item, String idCategoria) {
		return itemDeExtratoDAO.cadastrar(item, idCategoria);
	}

	public List<ItemDeExtrato> buscar() {
		return itemDeExtratoDAO.buscar();
	}

	public boolean atualizar(int idAntigo, ItemDeExtrato item) {
		return itemDeExtratoDAO.atualizar(idAntigo, item);
	}

	public boolean atualizarExtrato(int id, int idExtrato) {
		return itemDeExtratoDAO.atualizarExtrato(id, idExtrato);
	}

	public boolean atualizarCategoria(int id, String idCategoria) {
		return itemDeExtratoDAO.atualizarCategoria(id, idCategoria);
	}

}
