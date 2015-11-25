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

	public float getValorExtrato() {
		return valorExtrato;
	}
	
	private boolean atualizarValorFinal(Extrato extrato, ItemDeExtrato itemDeExtrato) {
		if (itemDeExtrato.getTipo() == null)
			return false;
		if (itemDeExtrato.getTipo().equals(TipoItemDeExtrato.receita))
			extrato.setValorFinal(extrato.getValorFinal() + itemDeExtrato.getValor());
		if (itemDeExtrato.getTipo().equals(TipoItemDeExtrato.despesa))
			extrato.setValorFinal(extrato.getValorFinal() - itemDeExtrato.getValor());
		valorExtrato = extrato.getValorFinal();
		return true;
	}

	public boolean cadastrar(Extrato extrato, ItemDeExtrato itemDeExtrato, String categoria) {
		itemDeExtratoDAO = new ItemDeExtratoDAO(extrato.getId());
		extratoDAO.cadastrar(extrato);
		return itemDeExtratoDAO.cadastrar(itemDeExtrato, categoria) && atualizarValorFinal(extrato, itemDeExtrato);
	}

	public List<Extrato> buscar() {
		return extratoDAO.buscar();
	}

	public boolean atualizar(Extrato extratoAnt, Extrato extrato, int idAntItemDeExtrato, float valorAnt,
			ItemDeExtrato itemDeExtrato, String idCategoria) {
		itemDeExtratoDAO.setIdExtrato(extratoAnt.getId());
		if (extrato.getId() != extratoAnt.getId()) {
			extratoDAO.cadastrar(extrato);
			if (itemDeExtrato.getValor() != valorAnt)
				if (atualizarValorFinal(extrato, itemDeExtrato))
					System.out.println("Atualizou o Valor Final");
				else
					System.out.println("Não atualizou o Valor Final");
		}
		if (itemDeExtrato.getValor() != valorAnt)
			if (atualizarValorFinal(extratoAnt, itemDeExtrato))
				System.out.println("Atualizou o Valor Final");
			else
				System.out.println("Não atualizou o Valor Final");
		if (!itemDeExtratoDAO.atualizar(idAntItemDeExtrato, itemDeExtrato, extratoAnt.getId(), idCategoria))
			return false;
		if (itemDeExtratoDAO.buscar().size() == 0) {
			extratoDAO.remover(extratoAnt.getId());
		}
		return true;
	}

	public boolean remover(int id) {
		return extratoDAO.remover(id);
	}
}
