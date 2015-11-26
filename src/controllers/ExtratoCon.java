package controllers;

import java.util.List;

import dao.ExtratoDAO;
import dao.ItemDeExtratoDAO;
import entities.Extrato;
import entities.ItemDeExtrato;
import entities.TipoItemDeExtrato;

/**
 * @author Danilo
 */
public class ExtratoCon {
	private ExtratoDAO extratoDAO;
	private ItemDeExtratoDAO itemDeExtratoDAO;
	private float valorExtrato;

	public ExtratoCon(int idConta) {
		extratoDAO = new ExtratoDAO(idConta);
		valorExtrato = 0.0f;
	}

	private boolean atualizarValorFinal(Extrato extrato, float valor) {
		extrato.setValorFinal(extrato.getValorFinal() + valor);
		valorExtrato = extrato.getValorFinal();
		return extratoDAO.atualizar(extrato);
	}

	public boolean cadastrar(Extrato extrato, ItemDeExtrato itemDeExtrato, String categoria) {
		itemDeExtratoDAO = new ItemDeExtratoDAO(extrato.getId());
		extratoDAO.cadastrar(extrato);
		if (itemDeExtratoDAO.cadastrar(itemDeExtrato, categoria)) {
			float valor = 0.0f;
			if (itemDeExtrato.getTipo() == TipoItemDeExtrato.receita)
				valor += itemDeExtrato.getValor();
			if (itemDeExtrato.getTipo() == TipoItemDeExtrato.despesa)
				valor -= itemDeExtrato.getValor();
			return atualizarValorFinal(extrato, valor);
		}
		return false;
	}

	public List<Extrato> buscar() {
		return extratoDAO.buscar();
	}

	public boolean atualizarItemDeExtrato(int idAntExtrato, Extrato extrato, int idAntItemDeExtrato, float valorAnt,
			ItemDeExtrato itemDeExtrato, String idCategoria) {
		itemDeExtratoDAO.setIdExtrato(idAntExtrato);
		if (extrato.getId() != idAntExtrato) {
			extratoDAO.cadastrar(extrato);
		}
		if (itemDeExtratoDAO.atualizar(idAntItemDeExtrato, itemDeExtrato, idAntExtrato, idCategoria)) {
			if (itemDeExtratoDAO.buscar().size() == 0) {
				extratoDAO.remover(idAntExtrato);
			}
			if (itemDeExtrato.getValor() != valorAnt) {
				itemDeExtrato.setValor(itemDeExtrato.getValor() - valorAnt);
				return atualizarValorFinal(extrato, itemDeExtrato.getValor());
			}
		}
		return false;
	}

	public boolean remover(Extrato extrato, ItemDeExtrato itemDeExtrato) {
		itemDeExtratoDAO.setIdExtrato(extrato.getId());
		if (itemDeExtratoDAO.remover(itemDeExtrato.getId())) {
			if (itemDeExtratoDAO.buscar().size() == 0) {
				extratoDAO.remover(extrato.getId());
			}
			float valor = 0.0f;
			if (itemDeExtrato.getTipo() == TipoItemDeExtrato.receita)
				valor -= itemDeExtrato.getValor();
			if (itemDeExtrato.getTipo() == TipoItemDeExtrato.despesa)
				valor += itemDeExtrato.getValor();
			return atualizarValorFinal(extrato, valor);
		}
		return false;
	}

	public boolean atualizarValores(Extrato extrato) {
		float valorDiferenca = extrato.getValorFinal() - extrato.getValorInicial();
		extrato.setValorInicial(valorExtrato);
		extrato.setValorFinal(valorExtrato + valorDiferenca);
		valorExtrato = extrato.getValorFinal();
		return extratoDAO.atualizar(extrato);
	}
}
