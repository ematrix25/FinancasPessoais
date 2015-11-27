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

	public ExtratoCon(int idConta) {
		extratoDAO = new ExtratoDAO(idConta);
		itemDeExtratoDAO = new ItemDeExtratoDAO(0);
	}

	private boolean atualizarValorFinal(Extrato extrato, float valor) {
		extrato.setValorFinal(extrato.getValorFinal() + valor);
		return extratoDAO.atualizar(extrato);
	}

	public boolean cadastrar(Extrato extrato, ItemDeExtrato itemDeExtrato) {
		itemDeExtratoDAO.setIdExtrato(extrato.getId());
		extratoDAO.cadastrar(extrato);
		if (itemDeExtratoDAO.cadastrar(itemDeExtrato)) {
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

	public boolean atualizar(long idAntExtrato, float valorInicialAnt, Extrato extrato, long idAntItemDeExtrato,
			float valorAnt, ItemDeExtrato itemDeExtrato) {
		itemDeExtratoDAO.setIdExtrato(idAntExtrato);
		boolean temNovoExtrato = false;
		if (extrato.getId() != idAntExtrato) {
			extrato.setValorInicial(valorInicialAnt);
			extrato.setValorFinal(extrato.getValorInicial());
			if (extratoDAO.cadastrar(extrato))
				temNovoExtrato = true;
		}
		if (itemDeExtratoDAO.atualizar(idAntItemDeExtrato, itemDeExtrato)) {
			if (itemDeExtratoDAO.buscar().size() == 0) {
				extratoDAO.remover(idAntExtrato);
			}
			if (itemDeExtrato.getValor() != valorAnt) {
				if (!temNovoExtrato)
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
		
	// Teste e modelo para integração na tela
	public static void main(String[] args) {
		System.out.println("Cadastro");
		ExtratoCon extratoCon = new ExtratoCon(2119118357);
		Extrato extrato = new Extrato(1, 2010, 0.0f, 0.0f, 0);
		ItemDeExtrato itemDeExtrato = new ItemDeExtrato("Contracheque", 1500.0f, "", 10, 1, TipoItemDeExtrato.receita,
				extrato.getId(), "Salario");

		extratoCon.cadastrar(extrato, itemDeExtrato);

		extrato.setMes(12);
		extrato.setAno(2009);
		extrato.setValorFinal(0.0f);
		itemDeExtrato.setIdExtrato(extrato.getId());

		extratoCon.cadastrar(extrato, itemDeExtrato);

		System.out.println("Atualizacao");

		extrato.setMes(2);
		extrato.setAno(2010);
		extrato.setValorFinal(0.0f);
		extrato.setValorInicial(extrato.getValorFinal());
		itemDeExtrato.setValor(1300.0f);
		itemDeExtrato.setIdExtrato(extrato.getId());

		extratoCon.atualizar(147729275858L, 1500.0f, extrato, -37173075180636L, 1500.0f, itemDeExtrato);

		System.out.println("Remocao");

		extrato.setMes(12);
		extrato.setAno(2009);
		itemDeExtrato.setIdExtrato(extrato.getId());

		extratoCon.remover(extrato, itemDeExtrato);

	}
}
