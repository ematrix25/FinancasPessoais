package controllers;

import java.util.ArrayList;
import java.util.List;

import dao.ExtratoDAO;
import dao.ItemDeExtratoDAO;
import entities.Extrato;
import entities.ItemDeExtrato;
import utilities.TipoItemDeExtrato;
import utilities.ItemDoRelatorio;

/**
 * @author Danilo
 */
public class ExtratoCon {
	private ExtratoDAO extratoDAO;
	private ItemDeExtratoDAO itemDeExtratoDAO;

	public ExtratoCon(long idConta) {
		extratoDAO = new ExtratoDAO(idConta);
		itemDeExtratoDAO = new ItemDeExtratoDAO(0);
	}
	
	public void setIdConta(long idConta){
		extratoDAO.setIdConta(idConta);
	}

	public boolean cadastrar(Extrato extrato, ItemDeExtrato itemDeExtrato) {
		itemDeExtratoDAO.setIdExtrato(extrato.getId());
		extratoDAO.cadastrar(extrato);
		return itemDeExtratoDAO.cadastrar(itemDeExtrato);
	}

	public ArrayList<Extrato> buscar() {
		return extratoDAO.buscar();
	}

	public boolean atualizar(long idAntExtrato, Extrato extrato, long idAntItemDeExtrato, ItemDeExtrato itemDeExtrato) {
		itemDeExtratoDAO.setIdExtrato(idAntExtrato);
		if (extrato.getId() != idAntExtrato) {
			extratoDAO.cadastrar(extrato);
		}
		if (itemDeExtratoDAO.atualizar(idAntItemDeExtrato, itemDeExtrato)) {
			if (itemDeExtratoDAO.buscar().size() == 0) {
				extratoDAO.remover(idAntExtrato);
			}
			return true;
		}
		return false;
	}

	public boolean remover(long idExtrato, long idItemDeExtrato) {
		itemDeExtratoDAO.setIdExtrato(idExtrato);
		if (itemDeExtratoDAO.remover(idItemDeExtrato)) {
			if (itemDeExtratoDAO.buscar().size() == 0) {
				extratoDAO.remover(idExtrato);
			}
			return true;
		}
		return false;
	}

	private float valorTotalDoTipo(TipoItemDeExtrato tipo, List<ItemDeExtrato> items) {
		float valor = 0;
		for (ItemDeExtrato itemDeExtrato : items) {
			if (itemDeExtrato.getTipo().equals(tipo)) {
				valor += itemDeExtrato.getValor();
			}
		}
		return valor;
	}

	public List<ItemDoRelatorio> gerarRelatorio(TipoItemDeExtrato tipo, Extrato extrato) {
		List<ItemDoRelatorio> relatorio = new ArrayList<ItemDoRelatorio>();
		List<ItemDeExtrato> items = gerarExtrato(extrato.getId());
		float valorTotal = valorTotalDoTipo(tipo, items);
		String categoria;
		int index;
		for (ItemDeExtrato itemDeExtrato : items) {
			if (itemDeExtrato.getTipo().equals(tipo)) {
				categoria = itemDeExtrato.getCategoria();
				if (relatorio.contains(categoria)) {
					index = relatorio.indexOf(categoria);
					relatorio.get(index).setPorcentagem(
							relatorio.get(index).getPorcentagem() + (itemDeExtrato.getValor() / valorTotal) * 100);
				} else
					relatorio.add(new ItemDoRelatorio(categoria, (itemDeExtrato.getValor() / valorTotal) * 100));
			}
		}
		return relatorio;
	}

	public List<ItemDeExtrato> gerarExtrato(long idExtrato) {
		itemDeExtratoDAO.setIdExtrato(idExtrato);
		return itemDeExtratoDAO.buscar();
	}
}